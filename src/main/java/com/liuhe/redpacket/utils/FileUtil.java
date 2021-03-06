package com.liuhe.redpacket.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/**
	 * 
	 * @param file
	 *            要保存的图片
	 * @param imageType
	 *            保存的文件夹名字
	 * @return 文件保存路径
	 * @throws IOException
	 */
	public static String saveImage(MultipartFile file, String imageType)
			throws IOException {

		// 获取上传文件的名字
		String imgName = file.getOriginalFilename();
		// 获取上传文件的后缀名
		String extension = FilenameUtils.getExtension(imgName);
		// 以UUID的方式作为保存的文件名
		String uuid = UUID.randomUUID().toString();
		// 保存的文件名
		String saveNmae = uuid + "." + extension;

		// 当前工程绝对路径
		String realPath = UserContext.getRealPath();
		// 保存的路径
		String uploadPath = realPath + "images/" + imageType + "/";
		// 保存的文件
		File saveFile = new File(uploadPath + saveNmae);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		if (!saveFile.exists()) {
			saveFile.createNewFile();
		}
		file.transferTo(saveFile);
		return "images/" + imageType + "/" + saveFile.getName();
	}
	/**
	 * 
	 * @param file
	 *            要保存的文件
	 * @param imageType
	 *            保存的文件夹名字
	 * @param name
	 *            要保存的文件名字
	 * @return 文件保存路径
	 * @throws IOException
	 */
	public static String saveFile(MultipartFile file, String fileType,String name)
			throws IOException {
		
		// 获取上传文件的名字
		String fileName = file.getOriginalFilename();
		// 当前工程绝对路径
		String realPath = UserContext.getRealPath();
		// 保存的路径
		String uploadPath = realPath +  fileType + "/";
		
		if (!StringUtils.isEmpty(name)) {
			// 获取上传文件的后缀名
			String extension = FilenameUtils.getExtension(fileName);
			// 保存的文件名
			fileName = name + "." + extension;
		}
		// 保存的文件
		File saveFile = new File(uploadPath + fileName);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		if (!saveFile.exists()) {
			saveFile.createNewFile();
		}
		file.transferTo(saveFile);
		return fileType + "/" + saveFile.getName();
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件保存路径
	 */
	public static void deleteFile(String path) {
		if (StringUtils.isBlank(path)) {
			return;
		}
		String realPath = UserContext.getRealPath();
		File file = new File(realPath, path);
		if (file.exists()) {
			file.delete();// 删除文件
		}
	}
	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件保存路径
	 */
	public static void deleteParent(String path) {
		if (StringUtils.isBlank(path)) {
			return;
		}
		String realPath = UserContext.getRealPath();
		File file = new File(realPath, path);
		File parentFile = file.getParentFile();
		if (parentFile.exists()) {
			parentFile.delete();// 删除文件
		}
	}
}
