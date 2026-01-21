package com.sist.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.NoticeService;
import com.sist.web.vo.NoticeVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeService nService;
	
	@GetMapping("/admin/notice_list")
	public String admin_notice_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if (page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<NoticeVO> list = nService.noticeListData((curpage - 1) * 10);
		int totalpage = nService.noticeTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		model.addAttribute("admin_jsp", "../admin/notice_list.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/admin/notice_insert")
	public String admin_notice_insert(Model model) {
		model.addAttribute("admin_jsp", "../admin/notice_insert.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@PostMapping("/admin/notice_insert_ok")
	public String admin_notice_insert_ok(@ModelAttribute NoticeVO vo, HttpServletRequest request) throws Exception {
		String uploadDir = request.getServletContext().getRealPath("/upload");
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		List<MultipartFile> files = vo.getFiles();
		String filename = "";
		boolean bCheck = false;
		
		for(MultipartFile file : files) {
			if (file.isEmpty()) {
				bCheck = false;
			} else {
				String oname = file.getOriginalFilename();
				File f = new File(uploadDir + "/" + oname);
				if (f.exists()) {
					String name = oname.substring(0, oname.lastIndexOf("."));
					String ext = oname.substring(oname.lastIndexOf("."));
					int count = 1;
					while (f.exists()) {
						String newName = name + "(" + count + ")" + ext;
						f = new File(uploadDir + "/" + newName);
						count++;
					}
				}
				filename += f.getName() + ",";
				bCheck = true;
				// Upload
				Path path = Paths.get(uploadDir, f.getName());
				Files.copy(file.getInputStream(), path);
			}
		}
		// 데이터베이스 저장
		if (bCheck = true) {
			filename = filename.substring(0, filename.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilecount(files.size());
		} else {
			vo.setFilename("");
			vo.setFilecount(0);
		}
		nService.noticeInsert(vo);
		return "redirect:/admin/notice_list";
	}
	
	@GetMapping("/notice/list")
	public String notice_list(@RequestParam(name = "page", required = false) String page, Model model) {
		if (page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<NoticeVO> list = nService.noticeListData((curpage - 1) * 10);
		int totalpage = nService.noticeTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		model.addAttribute("main_jsp", "../notice/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/admin/notice_detail")
	public String admin_notice_detail(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("admin_jsp", "../admin/notice_detail.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/notice/detail")
	public String notice_detail(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../notice/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("/admin/notice_delete")
	public String notice_delete(@RequestParam("no") int no, HttpServletRequest request) {
		NoticeVO info = nService.noticeFileInfoData(no);
		if (info.getFilecount() != 0) {
			String path = request.getServletContext().getRealPath("/upload");
			StringTokenizer st = new StringTokenizer(info.getFilename(), ",");
			while(st.hasMoreTokens()) {
				File f = new File(path + "/" + st.nextToken());
				f.delete();
			}
		}
		nService.noticeDelete(no);
		return "redirect:/admin/notice_list";
	}
	
	@GetMapping("/admin/notice_update")
	public String admin_notice_update(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("admin_jsp", "../admin/notice_update.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@PostMapping("/admin/notice_update_ok")
	public String admin_notice_update_ok(@ModelAttribute NoticeVO vo, RedirectAttributes ra) {
		nService.noticeUpdate(vo);
		ra.addAttribute("no", vo.getNo());
		return "redirect:/admin/notice_detail";
	}
}
