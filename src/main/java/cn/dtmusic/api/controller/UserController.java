package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.ResponseDto;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.SongListService;
import cn.dtmusic.api.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private  SongListService songListService;

    //判断界面用户是否是本人，不是本人用户是否关注
    @RequestMapping("/home/{userId}")
    public String jumpToHomePage(@PathVariable(required = false) Integer userId, Model model){
        User user = getSubjectUser();

        Integer nowUserId = 0;//nowUserId当前登录用户的Id
        if(user!=null){
            nowUserId = user.getUserId();
        }
        Integer temp = 0;//0代表未关注，1是已关注
        if(userId==0||userId==nowUserId){
            //是本人，指定没办法关注--默认关注
            user=userService.queryUserByUserId(userId);
            model.addAttribute("user",user);
            temp = 1;

        }else {//不是本人
            nowUserId = user.getUserId();//nowUserId当前登录用户的Id
            //根据前端传来的userId获取关注人的对象followUser--followUserId是关注人的关注人Id
            User followUser = userService.queryUserByUserId(userId);//followUser不是登录用户的用户
            Byte followByUserId = userService.isFollowByUserId(nowUserId, userId);//根据两个用户id查询当前用户是否关注
            if(followByUserId!=null&&followByUserId==1){
                temp=1;
            }
            model.addAttribute("user",followUser);
        }
        model.addAttribute("temp",temp);
        return "userHome";
    }

    @RequestMapping("/fans/{userId}")
    public String jumpToFansPage(@PathVariable(required = false) Integer userId, Model model){
        User user = getSubjectUser();
        Integer nowUserId = 0;//nowUserId当前登录用户的Id
        if(user!=null){
            nowUserId = user.getUserId();
        }
        Integer temp = 0;//0代表未关注，1是已关注
        if(userId==0||userId==nowUserId){
            //是本人，指定没办法关注--默认关注
            model.addAttribute("user",user);
            temp = 1;
        }else {//不是本人
            nowUserId = user.getUserId();//nowUserId当前登录用户的Id
            //根据前端传来的userId获取关注人的对象followUser--followUserId是关注人的关注人Id
            User followUser = userService.queryUserByUserId(userId);//followUser不是登录用户的用户
            Byte followByUserId = userService.isFollowByUserId(nowUserId, userId);//根据两个用户id查询当前用户是否关注
            if(followByUserId!=null&&followByUserId==1){
                temp=1;
            }
            model.addAttribute("user",followUser);
        }
        model.addAttribute("temp",temp);
        return "userFans";
    }

    @RequestMapping("isFollowed")
    public Byte isFollowByUserId(Integer userId, Integer followUserId) {
        return userService.isFollowByUserId(userId,followUserId);
    }

    //注册
    @RequestMapping("insert")
    String insert(User record,Model model) {
        User userByUserPhone = userService.getUserByUserPhone(record.getUserPhone());
        User userByUserNickname = userService.getUserByUserNickname(record.getUserNickname());
        if(userByUserPhone!=null){
            model.addAttribute("phoneError","手机号已存在！");
            return "userRegister";
        }else if(userByUserNickname!=null){
            model.addAttribute("nameError","用户昵称已存在！");
            return "userRegister";
        }else {
            int insert = userService.insert(record);
            if(insert==1){
                SongList songList=new SongList();
                songList.setUserId( record.getUserId());
                songList.setUserNickname( record.getUserNickname());
                songList.setSongListName("我喜欢的音乐");
                songListService.addSongList(songList);

                model.addAttribute("errorStatus","注册成功！");
                return "redirect:/user/login";
            }else {
                model.addAttribute("errorStatus","注册失败！");
                return "redirect:/user/login";
            }
        }
    }

    @RequestMapping("userLogin")
    String selectByPrimaryKey(String userPhone, String userPassword, String rememberMe, HttpServletRequest request) {
        if(userPhone!=null&&!userPhone.equals("")){
            Byte status = userService.getUserStatusByUserPhone(userPhone);
            if (status!=null&&status!=1){
                request.setAttribute("errorStatus", "您的账号涉嫌违规操作！已被封禁！");
                return "userLogin";
            }
        }
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装登陆的数据--把userName，userPass封装成一个token
        UsernamePasswordToken token = new UsernamePasswordToken(userPhone, userPassword);
        if (rememberMe != null && rememberMe.equals("on")) {
            token.setRememberMe(true);
        }
        try {
            subject.login(token);//执行登录方法，如果没有异常就说明OK
            //新增session
            request.getSession().setAttribute("loginUser", (User) subject.getPrincipal());

            return "redirect:/index";
        } catch (UnknownAccountException e) {//用户名不存在
            request.setAttribute("errorPhone", "手机号错误");
            return "userLogin";
        } catch (IncorrectCredentialsException e) {//密码不存在
            request.setAttribute("errorPass", "密码错误");
            return "userLogin";
        }
    }
/*    @RequestMapping("queryDimPageUserList")
    String queryDimPageUserList(String userNickname, Integer offset, Integer limit) {
        return null;
    }*/
    //跳转未授权页面
//    @RequestMapping("/noauth")
//    @ResponseBody
//    public String Unauthorized(){
//        return "未经授权禁止访问此页面！";
//    }
    /**
     * 登出  这个方法没用到,用的是shiro默认的logout
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }

    //用户关注分页列表
    @RequestMapping("queryPageFollowList")
    @ResponseBody
    public PageInfo<User> queryPageFollowList(Integer userId, Integer pageNum, Integer pageSize) {
        Integer nowUserId= getSubjectUser().getUserId();
        PageInfo<User> pageInfo = userService.queryPageFollowList(userId, pageNum, pageSize);
        return pageInfo;
    }

    //用户粉丝分页列表
    @RequestMapping("queryPageFansList")
    @ResponseBody
    public PageInfo<User> queryPageFansList(Integer userId, Integer pageNum, Integer pageSize) {
        Integer nowUserId= getSubjectUser().getUserId();
        PageInfo<User> pageInfo = userService.queryPageFansList(userId, pageNum, pageSize);
        return pageInfo;
    }


    private User getSubjectUser(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user;
    }


    @GetMapping("list")
    @ResponseBody
    public PageInfo<User> getUserPage(Integer offset, Integer limit, String searchUserName, Byte searchStatus) {
        return userService.getUserPage(offset, limit, searchUserName, searchStatus);
    }

    @DeleteMapping("/d/{userId}")
    @ResponseBody
    public ResponseDto disableUser(@PathVariable Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        int temp = userService.disableUser(userId);
        if (temp > 0) {
            responseDto.setErrorCode(ResponseDto.SUCCESS);
        } else {
            responseDto.setErrorCode(ResponseDto.SUCCESS);
            responseDto.setErrorInfo("内部错误!");
        }
        return responseDto;
    }

    @DeleteMapping("/e/{userId}")
    @ResponseBody
    public ResponseDto enableUser(@PathVariable Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        int temp = userService.enableUser(userId);
        if (temp > 0) {
            responseDto.setErrorCode(ResponseDto.SUCCESS);
        } else {
            responseDto.setErrorCode(ResponseDto.SUCCESS);
            responseDto.setErrorInfo("内部错误!");
        }
        return responseDto;
    }


    //关注操作
    @RequestMapping("follow")
    @ResponseBody
    public String setNowUserFollow(Integer followedUserId,Integer median) {
        String status = "-1";
        User nowUser = getSubjectUser();
        if(nowUser!=null){
            Integer nowUserId = nowUser.getUserId();
            Integer setFollow = userService.setNowUserFollow(nowUserId,followedUserId,median);
            if (setFollow!=null&&setFollow==1){






                status = "1";//关注操作成功
            }else {
                status="0";
            }
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",nowUser);

        return status;
    }

    //用户编辑--获取当前用户对象
    @RequestMapping("getNowUser")
    @ResponseBody
    User getNowUser(){
        User nowUser = getSubjectUser();
        /*String userPhone = nowUser.getUserPhone();
        User user = userService.selectByPrimaryKey(userPhone);*/
        Integer userId = nowUser.getUserId();
        User user = userService.queryUserByUserId(userId);

        return user;
    }

    //修改个人信息
    @RequestMapping("setInfo")
    @ResponseBody
    Integer updateByPrimaryKey(Integer userId, String userNickname, String userBirthday,String userIntroduce, String userGender, String userProvince, String userCity, String userPhone) {
        Date parse = null;
        if(userBirthday!=null||userBirthday.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                parse = sdf.parse(userBirthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        User user = new User(userId,userNickname,userGender,parse,userIntroduce,userProvince,userCity,userPhone);

        return userService.updateByPrimaryKey(user);
    }


/*    //个人主页-1.当前登录用户-2.根据传过来的id获取对象-并和
    @RequestMapping("/home/{userId}")
    @ResponseBody
    public User getUserById(@PathVariable(required = false) Integer userId){
        User user = null;
        if(userId==0){
            //是本人，指定没办法关注
            User nowUser = getSubjectUser();
            Integer nowUserId = nowUser.getUserId();
            user = userService.queryUserByUserId(nowUserId);
        }else {//不是本人
            user = userService.queryUserByUserId(userId);//followUser不是登录用户的用户
        }
        return user;
    }
    @RequestMapping("isFollowed")
    @ResponseBody
    public String isFollowByUserId(Integer userId, Integer followUserId) {
        String status = "0";
        Byte followByUserId = userService.isFollowByUserId(userId, followUserId);
        if(followByUserId!=null&&followByUserId==1){
            status="1";
        }
        return status;
    }*/
}
