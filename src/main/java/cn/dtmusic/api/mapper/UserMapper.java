package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.User;

import java.util.List;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(String userPhone);

    List<User> selectAll();

    //修改个人信息
    int updateByPrimaryKey(User record);

//    List<User> queryDimUserList(String userNickname);

//    List<Follow> queryFollowList(Integer userId);

    //根据userId获取user对象
    User queryUserByUserId(Integer userId);

    List<User> selectByConditions(@Param("searchUserName") String searchUserName,
                                  @Param("searchStatus") Byte searchStatus);

    int disableUser(Integer userId);

    int enableUser(Integer userId);

    //根据手机号查询用户状态
    Byte getUserStatusByUserPhone(@Param("userPhone") String userPhone);

    //注册:根据手机号查询用户
    User getUserByUserPhone(@Param("userPhone") String userPhone);
    User getUserByUserNickname(@Param("userNickname") String userNickname);

    //添加用户关注数:1.改变当前用户关注数2.改变对应用户粉丝数
    Integer addNowUserFollow(@Param("nowUserId")Integer nowUserId);
    Integer addFollowedUserFans(@Param("followedUserId")Integer followedUserId);

    //减少用户关注数
    Integer deleteNowUserFollow(@Param("nowUserId")Integer nowUserId);
    Integer deleteFollowedUserFans(@Param("followedUserId")Integer followedUserId);
}
