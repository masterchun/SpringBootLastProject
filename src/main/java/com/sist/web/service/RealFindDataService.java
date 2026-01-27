package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.RealFindDataVO;

public interface RealFindDataService {
	public void realFindDataInsert(RealFindDataVO vo);
	public List<RealFindDataVO> realFindDataList();
	public void realFindDataDelete();
}
