package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
	
	public void seoulHitIncrement(int contentid);
	
	public SeoulVO seoulAttractionDetailData(int contentid);
	
	public List<SeoulVO> seoulFindData(Map<String, Object> map);
	
	public int seoulFindTotalPage(String address);
	
	public List<SeoulVO> seoulTop5Data();
	
	@Results({
		@Result(property = "fvo.eventstartdate", column = "eventstartdate"),
		@Result(property = "fvo.eventenddate", column = "eventenddate"),
		@Result(property = "fvo.agelimit", column = "agelimit"),
		@Result(property = "fvo.playtime", column = "playtime"),
		@Result(property = "fvo.eventplace", column = "eventplace"),
		@Result(property = "fvo.eventhomepage", column = "eventhomepage"),
		@Result(property = "fvo.usetime", column = "usetime"),
		@Result(property = "fvo.spendtime", column = "spendtime"),
		@Result(property = "fvo.msg", column = "msg")
	})
	@Select("SELECT s.no, image1, x, y, title, address, eventstartdate, "
		  + "eventenddate, agelimit, playtime, eventplace, eventhomepage, usetime, spendtime, msg "
		  + "FROM seoultravel s, festival f "
		  + "WHERE s.contentid = f.contentid "
		  + "AND s.contentid = #{contentid}")
	public SeoulVO seoulFestivalDetailData(int contentid);
	
}
