package cn.dtmusic.api.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.dtmusic.api.entity.Admin;

/**
 * Description: 简单的token管理工具
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日上午10:21:58
 * @since 1.0.0
 */
public class AdminTokenUtil {
	private static final Map<String, Integer> tokenMap = new ConcurrentHashMap<>();

	/**
	 * Constructor
	 */
	private AdminTokenUtil() {
	}

	/**
	 * @Description: 添加一个token
	 * @param token 要添加的token
	 * @param id    token对应的管理员id
	 */
	public static void addToken(String token, Integer id) {
		AdminTokenUtil.tokenMap.put(token, id);
	}

	/**
	 * @Description: 删除一个token
	 * @param token 要删除的token
	 */
	public static void removeToken(String token) {
		AdminTokenUtil.tokenMap.remove(token);
	}

	/**
	 * @Description: 获取token对应的管理员id
	 * @param token token
	 * @return id
	 */
	public static Integer getAdminId(String token) {
		return AdminTokenUtil.tokenMap.get(token);
	}

	/**
	 * @Description:
	 * 生成token
	 * @param admin 管理员对象
	 * @return token
	 */
	public static String generateToken(Admin admin) {
		return admin.getAdminId() + "_" + new Date();
	}
}
