package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.SeoulVO;

@Mapper
@Repository
public interface SeoulMapper {
	public List<SeoulVO> seoulListData(Map<String, Object> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM seoultravel "
		  + "WHERE contenttype = #{contenttype}")
	public int seoulTotalPage(int contenttype);
	
	public void seoulHitIncrement(int cno);
	
	public SeoulVO seoulAttractionDetailData(int contentid);
}
