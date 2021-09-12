package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.HomeDataDto;
import cn.dtmusic.api.entity.Admin;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    Integer deleteByPrimaryKey(Integer adminId);

    Integer insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    Integer updateBySuperAdmin(Admin record);

    Admin selectByAdminName(String adminName);

    List<Admin> selectAdminsByPage(@Param("adminName") String adminName, @Param("roleId") Integer roleId);

    @Insert("INSERT INTO t_admin_role(role_id,admin_id) VALUES(${roleId},${adminId})")
    int insertAdminRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);

    Integer updateAdminRole(@Param("roleId") Integer roleId,
                            @Param("adminId") Integer adminId);

    int updateByAdminSelf(Admin admin);

    List<Integer> selectHomeData();

    List<Admin> selectByNameOrPhone(@Param("adminName") String adminName,
                                    @Param("adminPhone") String adminPhone);
}
