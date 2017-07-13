package com.dgit.ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.util.MediaUtils;
import com.dgit.util.UploadFileUtils;




@Controller
public class UploadController {
	private static String innerUploadPath = "resources/upload";
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//@Autowired 
	// Autowired는 클래스 기준으로 주입받는 것... String과 같은 기본클래스 사용시 주입받기 어려움
	// 그래서! ID로 주입받음~! @Resource(name="uploadPath")
	@Resource(name="uploadPath")
	private String uploadPath;

	@RequestMapping(value = "innerUpload", method = RequestMethod.GET)
	public String innerUploadForm() {
		logger.info("---innerUplaod GET -------");
		return "innerUploadForm";
	}

	@RequestMapping(value = "innerUpload", method = RequestMethod.POST)
	public String innerUploadResult(String writer, MultipartFile upFile, 
			HttpServletRequest request, Model model) throws IOException {
		// MultipartFile의 이름은 input 태그 name과 맞출것
		logger.info("writer : " + writer);
		logger.info("file.size :" + upFile.getSize());
		logger.info("file.originalName :" + upFile.getOriginalFilename());

		String root_path = request.getSession().getServletContext().getRealPath("/");
		logger.info("root_path : " + root_path);

		File dirPath = new File(root_path + innerUploadPath);
		if (dirPath.exists() == false) {
			dirPath.mkdirs();
			//resources 까지는 존재... upload를 만들어 주는 것
			
		}
		
		UUID uuid= UUID.randomUUID(); //중복되지 않도록 고유한 키값을 제공
		String savedName = uuid.toString() + "_"+upFile.getOriginalFilename(); //중복이름 파일 되지 않도록 중복처리
		File target = new File(root_path + innerUploadPath + "/" +savedName); // 해당 용량의 빈껍데기 파일을 만든당!
		FileCopyUtils.copy(upFile.getBytes(), target); // 파일 복사!

		model.addAttribute("writer",writer);
		model.addAttribute("filename", innerUploadPath + "/" + savedName); // 파일 경로 넘겨줌
		
		return "innerUploadResult";
	}
	
	@RequestMapping(value = "innerMultiUpload", method = RequestMethod.GET)
	public String innerMultiUploadForm(){
		return "innerMultiUploadForm";
	}
	
	@RequestMapping(value = "innerMultiUpload", method = RequestMethod.POST)
	public String innerMultiUploadResult(String writer, List<MultipartFile> files, 
			HttpServletRequest req, Model model) throws IOException{
		logger.info("========================   innerMultiUploadResult   ========================");
		logger.info("writer : " + writer);
		
		for(MultipartFile file : files){
			logger.info("file.size :" + file.getSize());
			logger.info("file.originalName :" + file.getOriginalFilename());
		}
		
		String root_path = req.getSession().getServletContext().getRealPath("/"); //  ex00
		
		File dir = new File(root_path+"/"+innerUploadPath);
		if(dir.exists() == false){
			dir.mkdirs();
			//resources 까지는 존재... upload를 만들어 주는 것
		}
		
		ArrayList<String> list = new ArrayList<>();
		for(MultipartFile file : files){
			UUID uuid = UUID.randomUUID();
			String savedName = uuid.toString()+"_"+file.getOriginalFilename();
			File target = new File(root_path+"/"+innerUploadPath+"/"+savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			list.add(innerUploadPath+"/"+savedName);
		}
		
		model.addAttribute("writer",writer);
		model.addAttribute("files", list);
		logger.info("========================   멀티 업로드 완료   ========================");
		
		return "innerMultiUploadResult";
	}
	
	
	@RequestMapping(value="outerUpload", method = RequestMethod.GET)
	public String outerUplaodForm(){
		return "outerUploadForm";
	}
	
	@RequestMapping(value="outerUpload", method = RequestMethod.POST)
	public String outUplaodResult(String writer, MultipartFile upFile, HttpServletRequest req, Model model) throws IOException{
		
		logger.info("================outerUplaod Result===========");
		logger.info("writer : "+writer);
		logger.info("file name : "+upFile.getName());
		logger.info("file type : "+upFile.getContentType());
		
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString() + "_" + upFile.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(upFile.getBytes(), target);
		
		model.addAttribute("writer",writer);
		model.addAttribute("filename", savedName);
		
		// 업로드된 파일이 서버 바깥에 저장되어 있음... 이를 위한 접근법이 필요
		// 이미지가 가진 Byte값을 가져와서 그것을 뿌려주면 된다.... 컨트롤러에서 byte 반환하는 걸 만들어보쟈~!
		return "outUplaodResult";
	}
	
	
	@ResponseBody
	@RequestMapping(value="displayFile") //  displayFile?filename={파일이름}
	public ResponseEntity<byte[]> displayFile(String filename) throws IOException{
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		logger.info("========================displayFile=============================");
		
		try{
			// IOUtils.toByteArray(in) : 해당데이터의 Byte를 꺼내주는 역할
						// 이미지 종류별로 형식이 다르다. byte 배열순이 다름...
						// BMP는 565... jpg는 다르게...
						// >>>>>>>>>> 이미지타입을 알려줘야 한다
			String formatName = filename.substring(filename.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(mType);
			in = new FileInputStream(uploadPath + "/" + filename);
			
			//   [ header |             body              ] 전송되는 byte 모양...
			// header는 전송하고자 하는 정보... 어디로가니... 사이즈가 얼마다... 저장됨
			// body에는 전송할 data 원본 담겨서 감
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close(); // 닫지 않으면 메모리 누수 현상
		}
		
		return entity;
	}
	
	@RequestMapping(value="dragDropUpload", method=RequestMethod.GET)
	public String dragDropUploadForm(){
		return "dragDropUploadFrom";
	}
	
	@RequestMapping(value="dragDropUpload", method=RequestMethod.POST)
	public ResponseEntity<List<String>> dragDropUploadResult(String writer, List<MultipartFile> files){
		ResponseEntity<List<String>> entity = null;
		
		logger.info("============================Result================dragdrop================");
		logger.info("writer : "+ writer);
		for(MultipartFile file : files){
			logger.info("file : "+file.getOriginalFilename());
		}
		
		try{
			// c:/zzz/upload에 업로드한다.. 
			ArrayList<String> list = new ArrayList<>();
			for(MultipartFile file : files){
				String thumb = UploadFileUtils.uplaodFile(uploadPath, file.getOriginalFilename(), file.getBytes());
				list.add(thumb);
			}
			
			
			// 윈도우의 파일갯수 제한 때문에... upload/회원아이디/년도/월/일.... 폴더로 만들어서 업로드 합니다 UploadFileUtils 참조하세요...
			// /2017/06/30/uuid_파일명.jpg 를 db에 저장해야합니다... C:부터 저장하지 마세요
			
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		//return "dragDropUploadResult";
		return entity;
	}

	@ResponseBody //해야 데이터를 넘겨준다~? 근데 이거 하기 싫다~! 메소드마다 달기 싫다~ 할 경우에는 클래스에다가 @RestController 통째로 달아주기!
	@RequestMapping(value="deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String filename){
		ResponseEntity<String> entity = null;
		
		logger.info("===========delteFile Post====================");
		logger.info("=======filenmae : " + filename);  // << console에 파일 네임이 잘 넘어오는지 확인해야 함...
		
		try{
			// 썸네일? 지금은 썸네일을 지우는 중~! 
			File file = new File(uploadPath + filename); // filename에 나오는 경로가 '/'까지 붙어오므로 바로 붙임..
			file.delete();
			
		
			
		
			 
			
			// 원본을 지웁시다~!
			//String orignalPath = filename.substring(0,filename.indexOf("s_"));
			//String originalFilename = filename.substring(filename.indexOf("s_")+2); // s_ 자르기..
			/*logger.info("=============================orignalPath : " + orignalPath); 
			logger.info("=============================originalFilename : " + originalFilename); 
			File originalFile = new File(uploadPath, orignalPath+"/"+ originalFilename );
			originalFile.delete();
			 */
			
			String front = filename.substring(0,12);
			String end = filename.substring(14);
			String originalName = front + end;
			File file2 = new File(uploadPath+originalName);
			file2.delete();
			
			
			// substring indexof 등은 많이 쓰니까 꼭 자세히 알아두세요~!
		
			
			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			entity = new ResponseEntity<String>("FAIL",HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
}
