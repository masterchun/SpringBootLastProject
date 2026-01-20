package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.*;
/*           /seoul/location
 *                 | 인가 
 *    user요청 --------------- DispatcherServlet 
 *                                 | 위임 
 *                               HandlerMapping 
 *                                 | URI주소 찾기 => @GetMapping
 *                                                 @PostMapping
 *                                                 @PutMapping
 *                                                 @DeleteMapping 
 *                                    => 인증 => 권한 부여
 *                                 | 밑에 있는 메소드 호출 
 *                                         |
 *                                      Service 
 *                                         |
 *                                      Mapper => 수정시에 의존성을 약하게 
 *                                         |
 *                                       오라클 
 *                               DispatcherServlet 
 *                                 |
 *                               ViewResolver 
 *                                 |
 *                                JSP 
 */
public interface SeoulService {
	public List<SeoulVO> seoulListData(Map map);
	public int seoulTotalPage(int contenttype);
	public SeoulVO seoulAttractionDetailData(int contentid);
	public List<SeoulVO> seoulFindData(Map map);
	public int seoulFindTotalPage(String address);
	public List<SeoulVO> seoulTop5Data();
	public SeoulVO seoulFestvalDetailData(int contentid);
	public SeoulVO seoulFoodStoreDetailData(int contentid);
}