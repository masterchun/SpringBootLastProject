package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/busan/")
@RequiredArgsConstructor
public class BusanRestController {

	private final BusanService bService;

	@GetMapping("find_vue/")
	public ResponseEntity<Map<String, Object>> busan_find_vue(@RequestParam("page") int page,
			@RequestParam("address") String address) {
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("start", (page - 1) * 12);
			map.put("address", address);

			List<BusanVO> list = bService.busanFindData(map);
			
			for(BusanVO vo:list) {
				String[] addrs = vo.getAddress().split(" ");
				vo.setAddress(addrs[0] + " " + addrs[1]);
			}
			
			int totalpage = bService.busanFindTotalPage(address);

			final int BLOCK = 10;

			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;

			if (endPage > totalpage) {
				endPage = totalpage;
			}
			map = new HashMap<>();
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("endPage", endPage);
			map.put("startPage", startPage);
			map.put("curpage", page);
			map.put("address", address);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
