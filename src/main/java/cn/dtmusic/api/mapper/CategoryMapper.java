package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Category;

import java.util.List;
@Repository
@Mapper
public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_categories
     *
     * @mbg.generated Sat Oct 10 15:58:54 CST 2020
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_categories
     *
     * @mbg.generated Sat Oct 10 15:58:54 CST 2020
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_categories
     *
     * @mbg.generated Sat Oct 10 15:58:54 CST 2020
     */
    Category selectByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_categories
     *
     * @mbg.generated Sat Oct 10 15:58:54 CST 2020
     */
    List<Category> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_categories
     *
     * @mbg.generated Sat Oct 10 15:58:54 CST 2020
     */
    int updateByPrimaryKey(Category record);

    List<Category> queryDimCategoryList(@Param("categoryTypeName") String categoryTypeName, @Param("categoryName") String categoryName);

    List<Category> selectCategoriesBySongList(@Param("songListId")Integer songListId);


    List<Category> selectByType(@Param("typeName")String typeName);
}
