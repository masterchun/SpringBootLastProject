package com.sist.web.vo;

import lombok.Data;

@Data
public class ShoppingVO {
	private int no, contentid;
	private String saleitem, saleitemcost, fairday, infocenter, restdate, parking, opentime, msg;
}
