package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.RealFindDataVO;

@Mapper
@Repository
public interface RealFindDataMapper {
	@Insert("INSERT INTO realfinddata_3 VALUES(SELECT NVL(MAX(no) + 1, 1) FROM realfinddata), "
		  + "#{word}, #{images})")
	public void realFindDataInsert(RealFindDataVO vo);
	
	@Select("SELECT * FROM realfinddata_3 ORDER BY no DESC")
	public List<RealFindDataVO> realFindDataList();
	
	@Update("TRUNCATE FROM realfinddata_3")
	public void realFindDataDelete();
}
