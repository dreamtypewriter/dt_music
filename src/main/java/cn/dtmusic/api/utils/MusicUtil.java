package cn.dtmusic.api.utils;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 83979
 * @create 2020-10-15
 * @since 1.0.0
 */
public class MusicUtil {

	/**
	 * Constructor
	 */
	private MusicUtil() {
	}

	/**
	 * @Description: 获取音乐文件长度
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws BitstreamException
	 */
	public static int getMusicLength(String filePath) throws IOException, BitstreamException {
		URL urlFile = new URL(filePath);
		URLConnection con = urlFile.openConnection();
		int b = con.getContentLength();// 得到音乐文件的总长度
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		Bitstream bt = new Bitstream(bis);
		Header h = bt.readFrame();
		int time = (int) h.total_ms(b);
		return (time / 1000);
	}

}
