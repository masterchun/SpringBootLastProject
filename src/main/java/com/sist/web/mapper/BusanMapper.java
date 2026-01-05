package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BusanVO;

@Mapper
@Repository
public interface BusanMapper {
	public List<BusanVO> busanListData(Map<String, Object> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM busantravel "
		  + "WHERE contenttype = #{contenttype}")
	public int busanTotalPage(int contenttype);
}
