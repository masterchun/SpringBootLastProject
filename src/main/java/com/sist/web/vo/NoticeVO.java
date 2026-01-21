package com.sist.web.vo;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	private Date regdate;
	private int no, hit, filecount;
	private String type, name, subject, content, dbday, filename;
	private List<MultipartFile> files;
}
