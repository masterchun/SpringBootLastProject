package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.CommonsReplyService;
import com.sist.web.vo.CommonsReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonsReplyRestController {
	private final CommonsReplyService cService;
	private final SimpMessagingTemplate template;

	private Map<String, Object> commonsData(int page, int cno) {
		Map<String, Object> map = new HashMap<>();
		List<CommonsReplyVO> list = cService.commonsReplyListData(cno, (page - 1) * 5);
		int totalpage = cService.commonsReplyTotalPage(cno);

		final int BLOCK = 5;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;

		if (endPage > totalpage) {
			endPage = totalpage;
		}

		map.put("list", list);
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("cno", cno);
		map.put("count", list.size());

		return map;
	}

	@GetMapping("/commons/list_vue/")
	public ResponseEntity<Map<String, Object>> commons_list_vue(@RequestParam("page") int page,
			@RequestParam("cno") int cno) {
		Map<String, Object> map = new HashMap<>();

		try {
			map = commonsData(page, cno);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/commons/insert_vue/")
	public ResponseEntity<Map<String, Object>> commons_insert_vue(@RequestBody CommonsReplyVO vo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();

		try {
			String id = (String) session.getAttribute("userid");
			String name = (String) session.getAttribute("username");
			String sex = (String) session.getAttribute("sex");
			
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			
			cService.commonsReplyInsert(vo);
			map = commonsData(vo.getPage(), vo.getCno());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/commons/delete_vue/")
	public ResponseEntity<Map<String, Object>> commons_delete_vue(@RequestParam("no") int no,
			@RequestParam("cno") int cno ,@RequestParam("page") int page) {
		Map<String, Object> map = new HashMap<>();

		try {
			cService.commonsDelete(no);
			map = commonsData(page, cno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	   @PostMapping("/commons/reply_reply_insert_vue/")
	   public ResponseEntity<Map> commons_reply_reply(
	     @RequestBody CommonsReplyVO vo,HttpSession session
	   )
	   {
		   Map map=new HashMap();
		   try
		   {
			   String id=(String)session.getAttribute("userid");
			   String name=(String)session.getAttribute("username");
			   String sex=(String)session.getAttribute("sex");
			   vo.setId(id);
			   vo.setName(name);
			   vo.setSex(sex);
			   String pid = cService.commonsReplyReplyInsert(vo);
			   map=commonsData(vo.getPage(), vo.getCno());
			   
			   if (!pid.equals(id)) {
				   template.convertAndSend("/sub/notice/" + pid, "[댓글 알림] 답글이 달렸습니다");
			   }
		   }catch(Exception ex)
		   {
			 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		    return new ResponseEntity<>(map,HttpStatus.OK);
		    
	   }
}
