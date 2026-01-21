package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

@Mapper
@Repository
public interface ReserveMapper {
	public List<SeoulVO> seoulReserveData(Map<String, Object> map);
	public int seoulReserveTotalPage(Map<String, Object> map);
	
	@Insert("INSERT INTO reserve_3(no, cno, id, rday, rtime, rinwon) "
		  + "VALUES(r3_no_seq.NEXTVAL, #{cno}, #{id}, #{rday}, #{rtime}, #{rinwon})")
	public void reserveInsert(ReserveVO vo);
	
	/*@Results({
		@Result(column = "title", property = "svo.title"),
		@Result(column = "image1", property = "svo.image1"),
		@Result(column = "address", property = "svo.address"),
	})*/
	@ResultMap("resMap")
	@Select("SELECT r.no, r.id, cno, rday, rtime, rinwon, iscancel, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, "
		  + "isReserve, title, image1, address "
		  + "FROM reserve_3 r, seoultravel s "
		  + "WHERE r.cno = s.contentid "
		  + "AND id = #{id} "
		  + "ORDER BY no DESC")
	public List<ReserveVO> reserveMyData(String id);
	
	@ResultMap("resMap")
	@Select("SELECT r.no, r.id, cno, rday, rtime, rinwon, iscancel, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, "
		  + "isReserve, title, image1, address "
		  + "FROM reserve_3 r, seoultravel s "
		  + "WHERE r.cno = s.contentid "
		  + "ORDER BY no DESC")
	public List<ReserveVO> reserveAdminData();
	
	@Update("UPDATE reserve_3 SET isReserve = 1 WHERE no = #{no}")
	public void reserveOk(int no);
	
	@Update("UPDATE reserve_3 SET isCancel = 1 WHERE no = #{no}")
	public void reserveCancel(int no);
	
	@Delete("DELETE FROM reserve_3 WHERE no = #{no}")
	public void reserveDelete(int no);
	
	@ResultMap("resMap")
	@Select("SELECT r.no, r.id, cno, rtime, rday, rinwon, title, image1, address "
		  + "FROM reserve_3 r, seoultravel s "
		  + "WHERE r.cno = s.contentid "
		  + "AND r.no = #{no} ")
	public ReserveVO reserveDetailData(int no);
}
