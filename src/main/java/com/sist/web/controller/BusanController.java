package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusanController {
	private final BusanService bService;
	
	@GetMapping("busan/list")
	public String busan_list(@RequestParam(name = "page", required = false) String page,
			@RequestParam("cno") int cno, Model model) {
		if (page == null) {
			page = "1";
		}

		int curpage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", (curpage - 1) * 12);
		map.put("contenttype", cno);

		List<BusanVO> list = bService.busanListData(map);
		for(BusanVO vo:list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[1] + " " + addrs[2]);
		}
		
		int totalpage = bService.busanTotalPage(cno);

		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;

		if (endPage > totalpage) {
			endPage = totalpage;
		}
		
		String name = "";
		if (cno == 12) {
			name = "부산 관광지";
		} else if (cno == 14) {
			name = "부산 문화 시설";
		} else if (cno == 15) {
			name = "부산 축제 & 공연";
		} else if (cno == 32) {
			name = "부산 숙박 시설";
		} else if (cno == 38) {
			name = "부산 쇼핑";
		} else if (cno == 39) {
			name = "부산 음식";
		}
		
		model.addAttribute("name", name);
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("cno", cno);

		model.addAttribute("main_jsp", "../busan/list.jsp");
		return "main/main";
	}
}
