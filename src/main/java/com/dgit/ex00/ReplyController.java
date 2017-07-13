package com.dgit.ex00;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.domain.ReplyVO;
import com.dgit.service.BoardService;
import com.dgit.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	
		@Autowired // 사용하려면 root-context에 등록되어야 함
		private ReplyService service; 
		
		@Autowired
		private BoardService boardService;
		
		// controller/replies 일때 post면 무조건 호출됨
		@RequestMapping(value="", method=RequestMethod.POST)
		public ResponseEntity<String> register(@RequestBody ReplyVO vo){
			logger.info("replies ADD POST");
			logger.info(vo.toString());
			ResponseEntity<String> entity = null;
			
			try{
				service.addReply(vo);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		
		@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
		public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno){
			ResponseEntity<List<ReplyVO>> entity = null;
			
			try{
				List<ReplyVO> list = service.listReply(bno);
				entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		@RequestMapping(value="/{rno}", method=RequestMethod.PUT)
		public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo){
			// GET : 주소주소주소?aa=bb
			// PUT : http body안에 데이터가 담겨 온다
			ResponseEntity<String> entity = null;
			
			logger.info("====================================PUT MODIFY");
			logger.info(rno + " : " + vo.toString());
			try{
				vo.setRno(rno);
				service.modifyReply(vo);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
		public ResponseEntity<String> remove(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo){
			
			ResponseEntity<String> entity = null;
			
			logger.info("====================================DELETE DELETE");
			logger.info(rno + " : " + vo.toString());
			try{
				vo.setRno(rno);
				service.removeReply(vo);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		
		@RequestMapping(value="/{bno}", method=RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> selectBoardByBno(@PathVariable("bno") Integer bno){
			ResponseEntity<Map<String, Object>> entity = null;
			Map<String, Object> map = new HashMap<>();
			try {
				BoardVO boardVO = boardService.read(bno);
				map.put("BoardVO", boardVO);
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
		
		@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)		//list는 get을 쓴다
		public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page ){
			ResponseEntity<Map<String, Object>> entity =null;
			logger.info("====================================listPage GET================");
			
			try{
				Criteria cri = new Criteria();
				cri.setPage(page);
				
				List<ReplyVO> list = service.listPage(bno, cri);
				
				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(cri);
				pageMaker.setTotalCount(service.count(bno));
				
				Map<String, Object> map = new HashMap<>();
				map.put("list", list);
				map.put("pageMaker", pageMaker);
				
				entity = new ResponseEntity<>(map, HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
}
