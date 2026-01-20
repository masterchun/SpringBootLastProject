package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
@Mapper
@Repository
public interface SeoulMapper {
   /*
    *    <select id="seoulListData" resultType="com.sist.web.vo.SeoulVO"
		   parameterType="hashmap"
		  >
		    SELECT no,contentid,title,address,image1,hit
		    FROM seoultravel
		    WHERE contenttype=#{contenttype}
		    ORDER BY no ASC
		    OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		  </select>
    */
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoultravel "
		   +"WHERE contenttype=#{contenttype}")
	public int seoulTotalPage(int contenttype);
	
	/*
	 *   <select id="seoulAttractionDetailData" resultMap="attMap"
		   parameterType="int"
		  >
		    SELECT s.no,title,image1,address,x,y,
		           infocenter,restdate,usetime,parking,msg
		    FROM seoultravel s JOIN attraction a
		    ON s.contentid=a.contentid 
		    AND s.contentid=#{contentid}
		  </select>
	 */
	public SeoulVO seoulAttractionDetailData(int contentid);
	/*
	 *   <update id="seoulHitIncrement" parameterType="int">
    UPDATE seoultravel SET 
    hit=hit+1
    WHERE contentid=#{contentid}
  </update>
	 */
	public void seoulHitIncrement(int contentid);
	
	/*
	 *   <!-- 검색  -->
	  <select id="seoulFindData" resultType="com.sist.web.vo.SeoulVO"
	   parameterType="hashmap"
	  >
	    SELECT no,contentid,title,address,image1,hit,contenttype
	    FROM seoultravel
	    WHERE address LIKE '%'||#{address}||'%'
	    ORDER BY no ASC
	    OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	  </select>
	  <select id="seoulFindTotalPage" resultType="int"
	   parameterType="string"
	  >
	    SELECT CEIL(COUNT(*)/12.0)
	    FROM seoultravel
	    WHERE address LIKE '%'||#{address}||'%'
	  </select>
	 */
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	
	/*<select id="seoulTop5Data" resultType="com.sist.web.vo.SeoulVO">
    SELECT no,contentid,title,address,image1,hit,rownum
    FROM (SELECT no,contentid,title,address,image1,hit 
    FROM seoultravel WHERE contenttype=39 ORDER BY hit DESC)
    WHERE rownum&lt;=5
  </select>*/
	public List<SeoulVO> seoulTop5Data();
	
	/*
	 *   <!-- 
      private int no,contentid;
      private String eventstartdate,eventenddate,agelimit,
      playtime,eventplace,eventhomepage,usetime,spendtime,msg;
      
      image1,x,y,title,address
   -->
	 */
	@Results({
		@Result(property = "fvo.eventstartdate",column = "eventstartdate"),
		@Result(property = "fvo.eventenddate",column = "eventenddate"),
		@Result(property = "fvo.agelimit",column = "agelimit"),
		@Result(property = "fvo.playtime",column = "playtime"),
		@Result(property = "fvo.eventplace",column = "eventplace"),
		@Result(property = "fvo.eventhomepage",column = "eventhomepage"),
		@Result(property = "fvo.usetime",column = "usetime"),
		@Result(property = "fvo.spendtime",column = "spendtime"),
		@Result(property = "fvo.msg",column = "msg")
	})
	@Select("SELECT f.no,image1,x,y,title,address,"
		  +"eventstartdate,eventenddate,agelimit,"
		  +"playtime,eventplace,eventhomepage,usetime,spendtime,msg "
		  +"FROM seoultravel s, festival f "
		  +"WHERE s.contentid=f.contentid "
		  +"AND s.contentid=#{contentid}")
	public SeoulVO seoulFestvalDetailData(int contentid);
	
	/*
	 *  private int no,contentid;
        private String firstmenu,treatmenu,infocenter,parking,
        onpendate,opentime,restdate,msg;
	 */
	@Results({
		@Result(property = "fsvo.firstmenu",column = "firstmenu"),
		@Result(property = "fsvo.treatmenu",column = "treatmenu"),
		@Result(property = "fsvo.infocenter",column = "infocenter"),
		@Result(property = "fsvo.parking",column = "parking"),
		@Result(property = "fsvo.opendate",column = "opendate"),
		@Result(property = "fsvo.opentime",column = "opentime"),
		@Result(property = "fsvo.restdate",column = "restdate"),
		@Result(property = "fsvo.msg",column = "msg")
	})
	@Select("SELECT f.no,image1,x,y,title,address,f.contentid,"
		  +"firstmenu,treatmenu,infocenter,"
		  +"parking,opendate,opentime,restdate,msg "
		  +"FROM seoultravel s, foodstore f "
		  +"WHERE s.contentid=f.contentid "
		  +"AND s.contentid=#{contentid}")
	public SeoulVO seoulFoodStoreDetailData(int contentid);
}