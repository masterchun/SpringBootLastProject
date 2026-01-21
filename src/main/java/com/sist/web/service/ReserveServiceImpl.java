package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.ReserveMapper;
import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
	private final ReserveMapper mapper;

	@Override
	public List<SeoulVO> seoulReserveData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.seoulReserveData(map);
	}

	@Override
	public int seoulReserveTotalPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.seoulReserveTotalPage(map);
	}

	@Override
	public String reserveInsert(ReserveVO vo) {
		// TODO Auto-generated method stub
		String res = "";
		
		try {
			mapper.reserveInsert(vo);
			res = "YES";
		} catch (Exception e) {
			e.printStackTrace();
			res = "NO";
		}
		
		return res;
	}

	@Override
	public List<ReserveVO> reserveMyData(String id) {
		// TODO Auto-generated method stub
		return mapper.reserveMyData(id);
	}

	@Override
	public List<ReserveVO> reserveAdminData() {
		// TODO Auto-generated method stub
		return mapper.reserveAdminData();
	}

	@Override
	public void reserveOk(int no) {
		// TODO Auto-generated method stub
		mapper.reserveOk(no);
	}

	@Override
	public void reserveCancel(int no) {
		// TODO Auto-generated method stub
		mapper.reserveCancel(no);
	}

	@Override
	public void reserveDelete(int no) {
		// TODO Auto-generated method stub
		mapper.reserveDelete(no);
	}

	@Override
	public ReserveVO reserveDetailData(int no) {
		// TODO Auto-generated method stub
		return mapper.reserveDetailData(no);
	}
	
}
