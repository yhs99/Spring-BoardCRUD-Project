package com.mini.project.infra;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class FileProcess {
	
	public void saveFileToRealPath(byte[] upfile, String realPath, String contentType, String originalFileName, long fileSize) {
		String[] ymd = makeCalendarPath(realPath);
		String saveFilePath = realPath + ymd[ymd.length-1];
		String newFileName = null;
		makeDirectory(realPath, ymd);
		if(checkFileExist(saveFilePath, originalFileName)) {
			newFileName = renameFileName(originalFileName);
		}else {
			newFileName = originalFileName;
		}
		System.out.println(newFileName.split("\\.")[1]);
		saveFile(upfile, saveFilePath, newFileName.split("\\.")[1], newFileName);
	}
	
	private void saveFile(byte[] upfile, String saveFilePath, String ext, String newFileName) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(upfile);
			BufferedImage image = ImageIO.read(inputStream);
			File output = new File(saveFilePath+File.separator+newFileName);
			ImageIO.write(image, ext, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String renameFileName(String originalFileName) {
		String uuid = UUID.randomUUID().toString();
		return uuid+"_"+originalFileName;
	}

	private boolean checkFileExist(String saveFilePath, String originalFileName) {
		boolean isExist = false;
		File tmp = new File(saveFilePath);
		String[] fileList = tmp.list();
		for(String name : fileList) {
			if(name.equals(originalFileName)) {
				System.out.println("중복 발견");
				isExist = true;
				break;
			}
		}
		
		if(!isExist) {
			System.out.println("중복 없음");
		}
		return isExist;
	}

	private void makeDirectory(String realPath, String[] ymd) {
		for(String date : ymd) {
			String createPath = realPath + date;
			File file = new File(createPath);
			if(!file.exists()) {
				file.mkdir();
				System.out.println("파일생성");
			}
		}
	}

	private String[] makeCalendarPath(String realPath) {
		Calendar now = Calendar.getInstance();
		String year = File.separator + now.get(Calendar.YEAR);
		String month = year + File.separator + new DecimalFormat("00").format((now.get(Calendar.MONTH)+1));
		String date = month + File.separator + new DecimalFormat("00").format(now.get(Calendar.DATE));
		String[] ymd = {year, month, date};
		return ymd;
	}
}
