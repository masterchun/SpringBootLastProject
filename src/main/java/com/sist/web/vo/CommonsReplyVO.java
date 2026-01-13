package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CommonsReplyVO {
	private Date regdate;
	private int no, cno, group_id, group_step, group_tab, root, depth, page;
	private String id, name, sex, msg, dbday;
}
