package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.HomeDataDto;
import cn.dtmusic.api.entity.Admin;
import cn.dtmusic.api.mapper.AdminMapper;
import cn.dtmusic.api.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/26
 * @ time:      21:25
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper mapper) {
        this.adminMapper = mapper;
    }

    @Override
    @Transactional
    public Integer addAdmin(Admin admin) {
        List<Admin> admins = adminMapper.selectByNameOrPhone(admin.getAdminName(), admin.getAdminPhone());
        if (admins.size() > 0) {
            return -1;
        }
        if (adminMapper.insert(admin) == 1) {
            return adminMapper.insertAdminRole(admin.getAdminId(), admin.getRole().getRoleId());
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer delSingleAdmin(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer modifyAdmin(Admin admin) {
        List<Admin> admins = adminMapper.selectByNameOrPhone(admin.getAdminName(), admin.getAdminPhone());
        if (admins.size() > 1) {
            return -1;
        }
        if (admins.get(0).getAdminId().equals(admin.getAdminId())) {
            return -2;
        }
        if (adminMapper.updateBySuperAdmin(admin) == 1) {
            return adminMapper.updateAdminRole(admin.getRole().getRoleId(), admin.getAdminId());
        } else {
            return 0;
        }
    }

    @Override
    public PageInfo<Admin> findAdminsByPage(String adminName, Integer roleId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        List<Admin> list = adminMapper.selectAdminsByPage(adminName, roleId);
        return new PageInfo<Admin>(list);
    }

    @Override
    public Admin findAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByName(String adminName) {
        return adminMapper.selectByAdminName(adminName);
    }

    @Override
    public int updateAdmin(Admin admin) {
        List<Admin> admins = adminMapper.selectByNameOrPhone(admin.getAdminName(), admin.getAdminPhone());
        if (admins.size() > 0) {
            if (admins.get(0).getAdminId().equals(admin.getAdminId())) {
                return -2;
            }
            return -1;
        }
        return adminMapper.updateByAdminSelf(admin);
    }

    @Override
    public HomeDataDto getHomeData() {
        List<Integer> list = adminMapper.selectHomeData();
        return new HomeDataDto(list);
    }


}
