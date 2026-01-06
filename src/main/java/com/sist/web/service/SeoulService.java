package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.SeoulVO;

public interface SeoulService {
	public List<SeoulVO> seoulListData(Map<String, Object> map);
	
	public int seoulTotalPage(int contenttype);
	
	public SeoulVO seoulAttractionDetailData(int cno);
	
	public List<SeoulVO> seoulFindData(Map<String, Object> map);
	
	public int seoulFindTotalPage(String address);
	
	public List<SeoulVO> seoulTop5Data();
}
