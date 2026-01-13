package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BoardMapper;
import com.sist.web.mapper.BoardReplyMapper;
import com.sist.web.vo.BoardReplyVO;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService, BoardReplyService {
	private final BoardMapper mapper;
	private final BoardReplyMapper rMapper;
	
	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}

	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return mapper.boardTotalPage();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return mapper.boardDetailData(no);
	}

	@Override
	public String boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		String result = "no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		
		if(db_pwd.equals(vo.getPwd())) {
			result = "yes";
			mapper.boardUpdate(vo);
		}
		
		return result;
	}

	@Override
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		String db_pwd = mapper.boardGetPassword(no);
		
		if (db_pwd.equals(pwd)) {
			mapper.boardDelete(no);
			return true;
		}
		
		return false;
	}

	@Override
	public List<BoardReplyVO> boardReplyListData(int bno) {
		// TODO Auto-generated method stub
		return rMapper.boardReplyListData(bno);
	}

	@Override
	public int boardReplyCount(int bno) {
		// TODO Auto-generated method stub
		int count = rMapper.boardReplyCount(bno);
		mapper.boardReplyCountUpdate(count, bno); 
		return count;
	}

	@Override
	public void boardReplyInsert(BoardReplyVO vo) {
		// TODO Auto-generated method stub
		rMapper.boardReplyInsert(vo);
	}

	@Override
	public void boardReplyDelete(int no) {
		// TODO Auto-generated method stub
		rMapper.boadReplyDelete(no);
	}

	@Override
	public void boardReplyUpdate(BoardReplyVO vo) {
		// TODO Auto-generated method stub
		rMapper.boardReplyUpdate(vo);
	}
	
}
