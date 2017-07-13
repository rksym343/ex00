package com.dgit.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	// file의 폴더와 이름을 리턴한다.
	public static String uplaodFile(String uploadPath, String originalName, byte[] fileData) throws IOException{
		UUID uuid = UUID.randomUUID();
		String datePath = calcPath(uploadPath);
		String upFileName = datePath +"/"+ uuid.toString()+"_"+originalName;
		File target = new File(uploadPath, upFileName);
		FileCopyUtils.copy(fileData, target);
		
		
		
		// 썸네일 이미지 만들기
		String thumbName = makeThumbnail(uploadPath, datePath, uuid.toString()+"_"+originalName);
		
		
		
		//return upFileName; // 2017/06/30/nnnnnn_a.jpg
		
		System.out.println("thumbName : "+thumbName);
		return thumbName; // 2017/06/30/s_nnnnnn_a.jpg
	}
	
	private static String calcPath(String uploadPath){
		Calendar cal = Calendar.getInstance();
		String yearPath = "/"+cal.get(Calendar.YEAR); 
		// /2017
		
		String monthPath = String.format("%s/%02d", yearPath, cal.get(Calendar.MONTH)+1); 
		// 2017/06
		
		String datePath = String.format("%s/%02d/%02d", yearPath, cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE)); 
		// 2017/06/30
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String...paths){
		for(String path: paths){
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()){
				dirPath.mkdirs();
			}
		}
		
	}
	
	private static String makeThumbnail(String uploadpath, String datePath, String filename) throws IOException{
		String orignalFilename = uploadpath + "/" + datePath + "/" + filename;
		BufferedImage sourceImg = ImageIO.read(new File(orignalFilename)); // 원본 이미지 가져온다
		
		// 썸네일 이미지의 높이를 뒤의 100px로 동일하게 만듬.
		BufferedImage destImg = Scalr.resize(sourceImg, 
											Scalr.Method.AUTOMATIC, //자기가 알아서 100 높이로 맞춘다
											Scalr.Mode.FIT_TO_HEIGHT, // height 기준으로 맞춰준다
											100);
		
		String thumbFileName = uploadpath + "/" + datePath + "/s_" + filename;
		File newFile = new File(thumbFileName);
		String formatName = filename.substring(filename.lastIndexOf(".")+1); // 확장자 찾기
		
		//Thumbnail 경로/파일 이름에 resizing된 이미지를 넣는다.
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return datePath + "/s_"+filename;
	}
	
}
