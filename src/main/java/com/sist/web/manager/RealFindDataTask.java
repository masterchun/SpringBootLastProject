package com.sist.web.manager;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sist.web.service.RealFindDataService;
import com.sist.web.vo.RealFindDataVO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealFindDataTask {
	private final RealFindDataService rService;
	
	@Scheduled(fixedRate = 60 * 3 * 1000)
	public void task() {
		rService.realFindDataDelete();
		List<RealFindDataVO> list = DataCollection.dataCollect();
		for(RealFindDataVO vo : list) {
			rService.realFindDataInsert(vo);
		}
	}
}
