package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.CommonsReplyVO;

public interface CommonsReplyService {
	public List<CommonsReplyVO> commonsReplyListData(Integer cno, Integer start);
	
	public int commonsReplyTotalPage(int cno);
	
	public void commonsReplyInsert(CommonsReplyVO vo);
	
	public void commonsDelete(int no);
	
	public String commonsReplyReplyInsert(CommonsReplyVO vo);
}
