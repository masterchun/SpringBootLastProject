package com.sist.web.vo;

import lombok.Data;

@Data
public class StayVO {
	private int no, contentid;
	private String roomtype, checkintime, checkouttime, chkcooking, subfacility, foodplace, reservationurl, infocenter, parking, msg;
}
