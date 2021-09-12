package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Role;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    @Update("UPDATE t_admin a INNER JOIN t_admin_role ar ON a.admin_id = ar.admin_id SET ar.`role_id`=${roleId} WHERE ar.`admin_id`=${adminId}")
    Integer updateByAdminId(@Param("roleId") Integer roleId,@Param("adminId") Integer adminId);
}
