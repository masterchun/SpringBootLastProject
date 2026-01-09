package com.sist.web.vo;

import lombok.Data;

@Data
public class SeoulVO {
	private int no, contentid, contenttype, hit;
	private String title, image1, image2, address;
	private double x, y;
	private AttractionVO avo = new AttractionVO();
	private FoodStoreVO fsvo = new FoodStoreVO();
	private FestivalVO fvo = new FestivalVO();
	private StayVO svo = new StayVO();
}
