package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.HomeDataDto;
import cn.dtmusic.api.entity.Admin;

/**
 * @ description: @ date: 2020/9/26 @ time: 21:24 @ author: Zhang wei @ since: 1.0.0
 */

public interface AdminService {

	Integer addAdmin(Admin admin);

	Integer delSingleAdmin(Integer id);

	Integer modifyAdmin(Admin admin);

	PageInfo<Admin> findAdminsByPage(String adminName, Integer roleId, Integer offset, Integer limit);

	Admin findAdminById(Integer id);

	Admin getAdminByName(String adminName);

	int updateAdmin(Admin admin);

	HomeDataDto getHomeData();

}
