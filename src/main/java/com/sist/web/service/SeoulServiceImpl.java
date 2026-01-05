package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.sist.web.controller.SeoulController;
import com.sist.web.mapper.SeoulMapper;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeoulServiceImpl implements SeoulService {

	private final SeoulMapper mapper;

	@Override
	public List<SeoulVO> seoulListData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.seoulListData(map);
	}

	@Override
	public int seoulTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return mapper.seoulTotalPage(contenttype);
	}

	@Override
	public SeoulVO seoulAttractionDetailData(int cno) {
		// TODO Auto-generated method stub
		SeoulVO vo = null;
		try {

			mapper.seoulHitIncrement(cno);
			vo = mapper.seoulAttractionDetailData(cno);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

}
