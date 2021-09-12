package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.mapper.FollowMapper;
import cn.dtmusic.api.mapper.UserMapper;
import cn.dtmusic.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FollowMapper followMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String userPhone) {
        return userMapper.selectByPrimaryKey(userPhone);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

/*    @Override
    public PageInfo<User> queryDimPageUserList(String userNickname,Integer offset,Integer limit) {
        PageHelper.offsetPage(offset,limit);
        List<User> users = userMapper.queryDimUserList(userNickname);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }*/

    @Override
    public PageInfo<User> queryPageFollowList(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = followMapper.queryFollowList(userId);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    public PageInfo<User> queryPageFansList(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = followMapper.queryFansList(userId);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }


    @Override
    public User queryUserByUserId(Integer userId) {
        return userMapper.queryUserByUserId(userId);
    }

/*    @Override
    public Byte isFollowByUserId(Integer nowUserId, Integer followUserId) {
        return followMapper.isFollowByUserId(nowUserId,followUserId);
    }*/

    @Override
    public Byte isFollowByUserId(Integer userId, Integer followUserId) {
        return followMapper.isFollowByUserId(userId,followUserId);
    }

    @Override
    public PageInfo<User> getUserPage(Integer offset, Integer limit, String searchUserName, Byte searchStatus) {
        PageHelper.offsetPage(offset, limit);
        List<User> users = userMapper.selectByConditions(searchUserName, searchStatus);
        return new PageInfo<>(users);
    }

    @Override
    public int disableUser(Integer userId) {
        return userMapper.disableUser(userId);
    }

    @Override
    public int enableUser(Integer userId) {
        return userMapper.enableUser(userId);
    }

    @Override
    public Byte getUserStatusByUserPhone(String userPhone) {
        return userMapper.getUserStatusByUserPhone(userPhone);
    }

    @Override
    public User getUserByUserPhone(String userPhone) {
        return userMapper.getUserByUserPhone(userPhone);
    }

    @Override
    public User getUserByUserNickname(String userNickname) {
        return userMapper.getUserByUserNickname(userNickname);
    }

    //修改用户关注数:1.改变当前用户关注数2.改变对应用户粉丝数
    @Transactional
    @Override
    public Integer setNowUserFollow(Integer nowUserId,Integer followedUserId,Integer temp) {
        try {
            Integer j = 0;
            Integer i=0;
            Integer setFollow = 0;
            if(temp==1){
                j = userMapper.addNowUserFollow(nowUserId);
                i = userMapper.addFollowedUserFans(followedUserId);
                setFollow = followMapper.insert(nowUserId, followedUserId);
            }else if (temp==0){
                j = userMapper.deleteNowUserFollow(nowUserId);
                i = userMapper.deleteFollowedUserFans(followedUserId);
                setFollow = followMapper.deleteFollow(nowUserId, followedUserId);
            }
            if(i==1&&j==1&&setFollow==1){
                return 1;
            }
        }catch (Exception e){
        }
        return null;
    }


}
