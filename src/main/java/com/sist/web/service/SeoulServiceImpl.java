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
	public SeoulVO seoulAttractionDetailData(int contentid) {
		// TODO Auto-generated method stub
		mapper.seoulHitIncrement(contentid);
		return mapper.seoulAttractionDetailData(contentid);
	}

	@Override
	public List<SeoulVO> seoulFindData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.seoulFindData(map);
	}

	@Override
	public int seoulFindTotalPage(String address) {
		// TODO Auto-generated method stub
		return mapper.seoulFindTotalPage(address);
	}

	@Override
	public List<SeoulVO> seoulTop5Data() {
		// TODO Auto-generated method stub
		return mapper.seoulTop5Data();
	}

}
