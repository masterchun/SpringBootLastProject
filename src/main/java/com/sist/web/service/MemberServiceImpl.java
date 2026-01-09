package com.sist.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.MemberMapper;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;

	@Override
	public int memberIdCheck(String userid) {
		// TODO Auto-generated method stub
		return mapper.memberIdCheck(userid);
	}

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		mapper.memberInsert(vo);
	}

	@Override
	public void memberAuthorityInsert(String userid) {
		// TODO Auto-generated method stub
		mapper.memberAuthorityInsert(userid);
	}

	@Override
	public MemberVO memberInfoData(String userid) {
		// TODO Auto-generated method stub
		return mapper.memberInfoData(userid);
	}
	
}
