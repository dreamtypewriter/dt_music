package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Follow;
import cn.dtmusic.api.entity.User;

import java.util.List;
@Repository
public interface FollowMapper {
    //修改用户关注：数添加关注
    Integer insert(@Param("userId") Integer userId,@Param("followUserId") Integer followUserId);
    //修改用户关注：取消关注
    Integer deleteFollow (@Param("userId") Integer userId,@Param("followUserId") Integer followUserId);

    List<Follow> selectAll();

    //根据一个别的用户id判断当前登录人是否关注该用户
/*    Byte isFollowByUserId(@Param("nowUserId") Integer nowUserId,@Param("followUserId") Integer followUserId);*/

    //获取两个用户之间的关注关系
    Byte isFollowByUserId(@Param("userId") Integer userId,@Param("followUserId") Integer followUserId);

    //通过一个用户的id查询出他的关注列表
    List<User> queryFollowList(@Param("userId") Integer userId);

    List<User> queryFansList(@Param("userId") Integer userId);
}
