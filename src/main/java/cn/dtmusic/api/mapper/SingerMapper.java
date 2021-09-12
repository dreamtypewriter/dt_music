package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Singer;

import java.util.List;

@Repository
public interface SingerMapper {

    int deleteByPrimaryKey(Integer singerId);

    int insert(Singer record);

    Singer selectByPrimaryKey(Integer singerId);

    List<Singer> selectAll();

    int updateByPrimaryKey(Singer record);

    Integer disableSinger(Integer singerId);

    List<Singer> selectByPage(@Param("singerName") String singerName,
                              @Param("singerType") String singerType,
                              @Param("singerLanguage") String singerLanguage);

    /*@Select("SELECT s.* FROM t_singer s LEFT JOIN t_user_singer us ON s.singer_id=us.singer_id"
            + " WHERE us.user_id=${userId}")*/
    List<Singer> selectCollectedSingerByUser(@Param("userId") Integer userId);

    @Insert("INSERT INTO t_user_singer(user_id,singer_id) VALUES(${userId},${singerId})")
    Integer insertSingerColllection(@Param("userId") Integer userId, @Param("singerId") Integer singerId);

    @Update("UPDATE t_singer SET collection_count=collection_count+1 WHERE singer_id=${singerId}")
    Integer updateCollectionCount(@Param("singerId") Integer singerId);

    /*@Select("SELECT s.* FROM t_singer s LEFT JOIN  t_user_singer us ON s.`singer_id`=us.`singer_id`" +
            "  WHERE us.user_id=${userId} AND us.singer_id=${singerId}")
    Singer selectCollectedSingleSingerByUser(@Param("userId") Integer userId, @Param("singerId") Integer singerId);
*/
    @Select("SELECT COUNT(*) FROM t_singer s LEFT JOIN  t_user_singer us ON s.`singer_id`=us.singer_id" +
            "   WHERE us.user_id=${userId} AND us.singer_id=${singerId}")
    Integer selectCollectCountofSingleSingerByUser(@Param("userId") Integer userId, @Param("singerId") Integer singerId);

    List<Singer> selectTopSinger();
}
