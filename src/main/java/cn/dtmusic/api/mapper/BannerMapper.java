package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Banner;

import java.util.List;


@Mapper
@Repository
public interface BannerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_banner
     *
     * @mbg.generated Sun Sep 27 14:42:36 CST 2020
     */
    int deleteByPrimaryKey(Integer bannerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_banner
     *
     * @mbg.generated Sun Sep 27 14:42:36 CST 2020
     */
    int insert(Banner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_banner
     *
     * @mbg.generated Sun Sep 27 14:42:36 CST 2020
     */
    Banner selectByPrimaryKey(Integer bannerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_banner
     *
     * @mbg.generated Sun Sep 27 14:42:36 CST 2020
     */
    List<Banner> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_banner
     *
     * @mbg.generated Sun Sep 27 14:42:36 CST 2020
     */
    int updateByPrimaryKey(Banner record);

    int updateBannerStatus(Integer bannerId);
}
