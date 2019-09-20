package com.yingxiaotian.controller;


import com.yingxiaotian.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.FastDFSClient;

@RestController
@RequestMapping("/upload")
public class UploadController {

	private String file_server_url="http://192.168.25.133/";
	
	@RequestMapping("/uploadFile")
	public Result uploadFile(MultipartFile file){
		
		try {
			// 获得文件名:
			String fileName = file.getOriginalFilename();
			// 获得文件的扩展名:
			String extName = fileName.substring( fileName.lastIndexOf(".")+1 );
			// 创建工具类
			FastDFSClient client = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
			
			String path = client.uploadFile(file.getBytes(), extName); // group1/M00/
			
			String url = file_server_url + path;
			
			return new Result(true, url);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "上传失败！");
		}
		
		
	}
}
