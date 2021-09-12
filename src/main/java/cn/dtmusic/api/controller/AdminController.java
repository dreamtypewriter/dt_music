package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.HomeDataDto;
import cn.dtmusic.api.dto.ResponseDto;
import cn.dtmusic.api.entity.Admin;
import cn.dtmusic.api.service.AdminService;
import cn.dtmusic.api.service.FileService;
import cn.dtmusic.api.utils.AdminTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Description: 管理员相关服务
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日下午1:19:59
 * @since 0.0.1
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	private final FileService fileService;

	@Autowired
	public AdminController(AdminService adminService, FileService fileService) {
		this.adminService = adminService;
		this.fileService = fileService;
	}

	/**
	 * Description: 管理员登录
	 * 
	 * @return: com.cloudmusic.api.dto.VueDto
	 * @Param: admin
	 * @date: 2020/10/9
	 * @time: 11:33
	 * @author:Zhang wei
	 * @since: 1.0.0
	 */
	@PostMapping("")
	public ResponseDto adminLogin(@RequestBody Admin admin) {
		Admin realAdmin = adminService.getAdminByName(admin.getAdminName());
		ResponseDto responseDto = new ResponseDto();
		if (realAdmin == null) {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("账户不存在!");
			responseDto.setResult(null);
			return responseDto;
		}
		if (!realAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("密码错误!");
			responseDto.setResult(null);
			return responseDto;
		}
		responseDto.setErrorCode(ResponseDto.SUCCESS);
		responseDto.setResult(resetToken(realAdmin, null));
		return responseDto;
	}

	@PostMapping("/logout/{adminId}")
	public ResponseDto adminLogout(@RequestHeader("Authorization") String token, @PathVariable Integer adminId) {
		Integer id = AdminTokenUtil.getAdminId(token);
		ResponseDto responseDto = new ResponseDto();
		if (adminId != null && adminId.equals(id)) {
			AdminTokenUtil.removeToken(token);
			responseDto.setErrorCode(ResponseDto.SUCCESS);
		} else {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("请求ID错误");
		}
		return responseDto;
	}

	@RequestMapping("/listAdmin")
	public PageInfo<Admin> listAdmin(@RequestParam(value = "adminName", required = false) String adminName,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "offset", defaultValue = "1") Integer offset,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
		return adminService.findAdminsByPage(adminName, roleId, offset, limit);
	}

	@RequestMapping("/addAdmin")
	public ResponseDto addAdmin(@RequestBody Admin admin) {
		Integer addAdmin = adminService.addAdmin(admin);
		if (addAdmin < 0) {
			return new ResponseDto(null, ResponseDto.FAIL, "用户名/手机已被占用!");
		}
		if (addAdmin == 0) {
			return new ResponseDto(null, ResponseDto.FAIL, "服务器错误!");
		}
		return new ResponseDto(null, ResponseDto.SUCCESS, null);
	}

	@RequestMapping("/modifyAdmin")
	public ResponseDto modifyAdmin(@RequestBody Admin admin) {
		Integer integer = adminService.modifyAdmin(admin);
		if (integer > 0) {
			return new ResponseDto(null, ResponseDto.SUCCESS, null);
		} else if (integer == -1) {
			return new ResponseDto(null, ResponseDto.FAIL, "手机号被占用!");
		} else if (integer == -2) {
			return new ResponseDto(null, ResponseDto.FAIL, "信息未变更!");
		} else {
			return new ResponseDto(null, ResponseDto.FAIL, "未知错误!");
		}
	}

	@RequestMapping("/delSingleAdmin/{id}")
	public Integer delSingleAdmin(@PathVariable("id") Integer Id) {
		return adminService.delSingleAdmin(Id);
	}

	@GetMapping("")
	public ResponseDto getAdminInfoByToken(@RequestHeader("Authorization") String token) {
		ResponseDto responseDto = new ResponseDto();
		if (token == null) {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("未登录");
			return responseDto;
		}
		Integer adminId = AdminTokenUtil.getAdminId(token);
		if (adminId == null) {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("信息错误");
			return responseDto;
		}
		Admin adminById = adminService.findAdminById(adminId);
		responseDto.setErrorCode(ResponseDto.SUCCESS);
		responseDto.setResult(resetToken(adminById, token));
		return responseDto;
	}

	@GetMapping("/getAdmin")
	public ResponseDto getUpdateAdmin(@RequestHeader("Authorization") String token) {
		if (token == null) {
			return new ResponseDto(null, ResponseDto.FAIL, "未登录!");
		}
		Integer adminId = AdminTokenUtil.getAdminId(token);
		if (adminId == null) {
			return new ResponseDto(null, ResponseDto.FAIL, "信息错误!");
		}
		Admin adminById = adminService.findAdminById(adminId);
		return new ResponseDto(adminById, ResponseDto.SUCCESS, null);
	}

	@PostMapping("/update")
	public ResponseDto updateAdminInfo(@RequestParam("adminId") Integer adminId, @RequestParam("adminPassword") String adminPassword,
			@RequestParam("adminPhone") String adminPhone, @RequestParam("adminAvatar") MultipartFile adminAvatar) throws IOException {
		String pictureUrl = fileService.uploadPicture(adminAvatar);
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setAdminPassword(adminPassword);
		admin.setAdminPhone(adminPhone);
		admin.setAdminAvatar(pictureUrl);
		ResponseDto responseDto = new ResponseDto();
		int update = adminService.updateAdmin(admin);
		if (update > 0) {
			responseDto.setErrorCode(ResponseDto.SUCCESS);
		} else {
			responseDto.setErrorCode(ResponseDto.FAIL);
			responseDto.setErrorInfo("手机号被占用!");
		}
		return responseDto;
	}

	@PostMapping("/updateNoImg")
	public ResponseDto updateAdminNoImg(Admin admin) {
		int update = adminService.updateAdmin(admin);
		if (update > 0) {
			return new ResponseDto(null, ResponseDto.SUCCESS, null);
		} else if (update == -1) {
			return new ResponseDto(null, ResponseDto.FAIL, "手机号被占用!");
		} else if (update == -2) {
			return new ResponseDto(null, ResponseDto.FAIL, "信息未变更!");
		} else {
			return new ResponseDto(null, ResponseDto.FAIL, "未知错误!");
		}
	}

	@GetMapping("/home")
	public ResponseDto getHomeData() {
		HomeDataDto dataDto = adminService.getHomeData();
		return new ResponseDto(dataDto, ResponseDto.SUCCESS, null);
	}

	private Admin resetToken(Admin admin, String token) {
		String token1 = AdminTokenUtil.generateToken(admin);
		AdminTokenUtil.addToken(token1, admin.getAdminId());
		if (token != null && !token.isEmpty()) {
			AdminTokenUtil.removeToken(token);
		}
		admin.setAdminPassword(token1);
		return admin;
	}
}
