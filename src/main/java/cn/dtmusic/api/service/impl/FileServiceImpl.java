/**
 * 
 */
package cn.dtmusic.api.service.impl;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.dtmusic.api.service.FileService;

/**
 * Description: 实现文件上传功能
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日下午12:55:18
 * @since 0.0.1
 */
@Service
@ConfigurationProperties("qiniuyun")
public class FileServiceImpl implements FileService {
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

	private String qiniuUrl;
	private String accessKey;
	private String secretKey;
	private String bucket;

	public void setQiniuUrl(String qiniuUrl) {
		this.qiniuUrl = qiniuUrl;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	@Override
	public String uploadPicture(MultipartFile file) throws IOException {
		return upload(file, "jpg");
	}

	@Override
	public String uploadMusic(MultipartFile file) throws IOException {
		return upload(file, "mp3");
	}

	/**
	 * @Description: 利用七牛云实现文件上传
	 * 
	 * @param file   文件
	 * @param suffix 后缀
	 * @return 地址
	 * @throws IOException
	 */
	private String upload(MultipartFile file, String suffix) throws IOException {
		// 设置服务器地区
		Configuration configuration = new Configuration(Region.region2());
		// 上传管理器
		UploadManager uploadManager = new UploadManager(configuration);
		// 文件名
		String key = UUID.randomUUID().toString().substring(0, 15) + suffix;
		// 密钥
		Auth auth = Auth.create(accessKey, secretKey);
		// 存储库名称
		String uptoken = auth.uploadToken(bucket);
		// 字节流
		byte[] localFile = file.getBytes();
		// 上传并等待响应
		Response response = uploadManager.put(localFile, key, uptoken);
		// 提取响应中的回复对象
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		// 拼接
		String responseUrl = qiniuUrl + "/" + putRet.key;
		LOG.info("Upload image response:{}", responseUrl);
		return responseUrl;
	}
}
