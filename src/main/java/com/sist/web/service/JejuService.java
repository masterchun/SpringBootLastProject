package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.JejuVO;

public interface JejuService {
	public List<JejuVO> jejuListData(Map<String, Object> map);
	
	public int jejuTotalPage(int contenttype);
	
	public List<JejuVO> jejuFindData(Map<String, Object> map);
	
	public int jejuFindTotalPage(Map<String, Object> map);
	
	public List<JejuVO> jejuTop4Data();
}
