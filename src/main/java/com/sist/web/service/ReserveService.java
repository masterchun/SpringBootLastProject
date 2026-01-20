package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

public interface ReserveService {
	public List<SeoulVO> seoulReserveData(Map<String, Object> map);
	public int seoulReserveTotalPage(Map<String, Object> map);
	public String reserveInsert(ReserveVO vo);
	public List<ReserveVO> reserveMyData(String id);
	public List<ReserveVO> reserveAdminData();
}
