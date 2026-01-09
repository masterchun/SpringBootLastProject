package com.sist.web.vo;

import lombok.Data;

@Data
public class FestivalVO {
	private int no, contentid;
	private String eventstartdate, eventenddate, agelimit, playtime, eventplace, eventhomepage, usetime, spendtime, msg;
}
