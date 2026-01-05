package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.JejuService;
import com.sist.web.vo.JejuVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JejuController {
	private final JejuService jService;
	
	@GetMapping("jeju/list")
	public String jeju_location(@RequestParam(name = "page", required = false) String page,
			@RequestParam("cno") int cno, Model model) {
		if (page == null) {
			page = "1";
		}

		int curpage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", (curpage - 1) * 12);
		map.put("contenttype", cno);

		List<JejuVO> list = jService.jejuListData(map);
		for(JejuVO vo:list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[1] + " " + addrs[2]);
		}
		
		int totalpage = jService.jejuTotalPage(cno);

		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;

		if (endPage > totalpage) {
			endPage = totalpage;
		}
				
		String name = "";
		if (cno == 12) {
			name = "제주 관광지";
		} else if (cno == 14) {
			name = "제주 문화 시설";
		} else if (cno == 15) {
			name = "제주 축제 & 공연";
		} else if (cno == 32) {
			name = "제주 숙박 시설";
		} else if (cno == 38) {
			name = "제주 쇼핑";
		} else if (cno == 39) {
			name = "제주 음식";
		}
		
		model.addAttribute("name", name);
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("cno", cno);

		model.addAttribute("main_jsp", "../jeju/list.jsp");
		return "main/main";
	}
}
