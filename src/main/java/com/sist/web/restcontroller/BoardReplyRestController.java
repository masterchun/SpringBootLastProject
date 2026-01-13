package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BoardReplyService;
import com.sist.web.vo.BoardReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardReplyRestController {
	private final BoardReplyService bService;
	
	public Map<String, Object> commonsData(int bno) {
		Map<String, Object> map = new HashMap<>();
		List<BoardReplyVO> list = bService.boardReplyListData(bno);
		int count = bService.boardReplyCount(bno);
		
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map<String, Object>> reply_list_vue(@RequestParam("bno") int bno) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			map = commonsData(bno);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/reply/insert_vue/")
	public ResponseEntity<Map<String, Object>> reply_insert_vue(@RequestBody BoardReplyVO vo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			String id = (String) session.getAttribute("userid");
			String name = (String) session.getAttribute("username");
			String sex = (String) session.getAttribute("sex");
			
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			bService.boardReplyInsert(vo);
			
			map = commonsData(vo.getBno());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/reply/delete_vue/")
	public ResponseEntity<Map<String, Object>> reply_delete_vue(@RequestParam("no") int no, @RequestParam("bno") int bno) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			bService.boardReplyDelete(no);
			map = commonsData(bno);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("/reply/update_vue/")
	ResponseEntity<Map<String, Object>> reply_update_vue(@RequestBody BoardReplyVO vo) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			bService.boardReplyUpdate(vo);
			map = commonsData(vo.getBno());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
