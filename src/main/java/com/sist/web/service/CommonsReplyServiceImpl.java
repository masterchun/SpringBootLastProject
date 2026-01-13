package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.CommonsReplyMapper;
import com.sist.web.vo.CommonsReplyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonsReplyServiceImpl implements CommonsReplyService {
	private final CommonsReplyMapper mapper;

	@Override
	public List<CommonsReplyVO> commonsReplyListData(Integer cno, Integer start) {
		// TODO Auto-generated method stub
		return mapper.commonsReplyListData(cno, start);
	}

	@Override
	public int commonsReplyTotalPage(int cno) {
		// TODO Auto-generated method stub
		return mapper.commonsReplyTotalPage(cno);
	}

	@Override
	public void commonsReplyInsert(CommonsReplyVO vo) {
		// TODO Auto-generated method stub
		mapper.commonsReplyInsert(vo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void commonsDelete(int no) {
		// TODO Auto-generated method stub
		CommonsReplyVO vo = mapper.commonsInfoData(no);
		
		if (vo.getDepth() == 0) {
			mapper.commonsDelete(no);
		} else {
			CommonsReplyVO rvo = new CommonsReplyVO();
			rvo.setNo(no);
			rvo.setMsg("관리자에 의해 삭제된 댓글입니다");
			mapper.commonsMsgUpdate(rvo);
		}
		
		mapper.commonsDepthDecrement(vo.getRoot());
	}
}
