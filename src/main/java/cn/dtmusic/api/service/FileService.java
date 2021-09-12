package cn.dtmusic.api.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 文件上传服务
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日下午1:12:05
 * @since 0.0.1
 */
public interface FileService {
	/**
	 * @Description: 上传图片
	 * @param file jpg
	 * @return 地址
	 * @throws IOException
	 */
	String uploadPicture(MultipartFile file) throws IOException;

	/**
	 * @Description: 上传音乐
	 * @param file mp3
	 * @return 地址
	 * @throws IOException
	 */
	String uploadMusic(MultipartFile file) throws IOException;
}
