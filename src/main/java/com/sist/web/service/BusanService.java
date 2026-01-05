package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.BusanVO;

public interface BusanService {
public List<BusanVO> busanListData(Map<String, Object> map);
	
	public int busanTotalPage(int contenttype);
}
