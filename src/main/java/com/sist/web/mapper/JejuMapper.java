package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.JejuVO;

@Mapper
@Repository
public interface JejuMapper {
	public List<JejuVO> jejuListData(Map<String, Object> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12.0) FROM jejutravel "
		  + "WHERE contenttype = #{contenttype}")
	public int jejuTotalPage(int contenttype);
	
	public List<JejuVO> jejuFindData(Map<String, Object> map);
	
	public int jejuFindTotalPage(Map<String, Object> map);
	
	public List<JejuVO> jejuTop4Data();
}
