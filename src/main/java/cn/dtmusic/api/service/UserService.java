package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(String userPhone);

    List<User> selectAll();

    //修改个人信息
    int updateByPrimaryKey(User record);

//    PageInfo<User> queryDimPageUserList(String userNickname,Integer offset,Integer limit);

    PageInfo<User> queryPageFollowList(Integer userId,Integer pageNum,Integer pageSize);

    PageInfo<User> queryPageFansList(Integer userId,Integer pageNum,Integer pageSize);


    User queryUserByUserId(Integer userId);

    /*Byte isFollowByUserId(Integer nowUserId,Integer followUserId);*/

    Byte isFollowByUserId(Integer userId,Integer followUserId);

    PageInfo<User> getUserPage(Integer offset, Integer limit, String searchUserName, Byte searchStatus);

    int disableUser(Integer userId);

    int enableUser(Integer userId);

    Byte getUserStatusByUserPhone(String userPhone);

    User getUserByUserPhone(String userPhone);
    User getUserByUserNickname(String userNickname);

    //修改用户关注数:1.改变当前用户关注数2.改变对应用户粉丝数
    Integer setNowUserFollow(Integer nowUserId,Integer followedUserId,Integer temp);

}
