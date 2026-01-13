package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class boardController {
	private final BoardService bService;
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name= "page", required = false) String page, Model model) {
		if (page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		List<BoardVO> list = bService.boardListData((curpage - 1) * 10);
		int totalpage = bService.boardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/board/insert")
	public String board_insert(Model model) {
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("board/insert_ok")
	public String board_insert_ok(@ModelAttribute BoardVO vo) {
		bService.boardInsert(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../board/update.jsp");
		return "main/main";
	}
	
	@PostMapping(value = "/board/update_ok", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String board_update_ok(@ModelAttribute BoardVO vo) {
		String res = "";
		String s = bService.boardUpdate(vo);
		
		if (s.equals("yes")) {
			res = "<script>"
				+ "location.href = \"/board/detail?no=" + vo.getNo() + "\""
				+ "</script>";
		} else {
			res = "<script>"
				+ "alert(\"비밀번호가 틀립니다\");"
				+ "history.back()"
				+ "</script>";
		}
		
		return res;
	}
	
	@GetMapping("/board/delete")
	public String board_delete(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		return "main/main";
	}
	
	@PostMapping(value = "/board/delete_ok", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd) {
		String res = "";
		
		boolean b = bService.boardDelete(no, pwd);
		
		if (b) {
			res = "<script>"
				+ "location.href = \"/board/list\""
				+ "</script>";
		} else {
			res = "<script>"
				+ "alert(\"비밀번호가 틀립니다\");"
				+ "history.back()"
				+ "</script>";
		}
		
		return res;
	}
}
