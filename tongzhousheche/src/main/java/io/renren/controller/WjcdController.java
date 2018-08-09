package io.renren.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.common.utils.R;
import io.renren.service.Wjcd_cajService;
import io.renren.service.Wjcd_lsjService;
import io.renren.service.Wjcd_qclService;
import io.renren.service.Wjcd_yxxService;
import io.renren.service.Wjcd_zdrService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/generator/wjcd")
@Slf4j
public class WjcdController {
	@Autowired
	private Wjcd_qclService qclService;
	@Autowired
	private Wjcd_cajService cajService;
	@Autowired
	private Wjcd_lsjService lsjService;
	@Autowired
	private Wjcd_yxxService yxxService;
	@Autowired
	private Wjcd_zdrService zdrService;

	@RequestMapping("/text_wjdr")
	public R wjdr(@RequestParam MultipartFile file) throws ParseException {
		List<Object> list = new ArrayList<Object>();
		String str = "";
		String[] a=null;
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "gbk");
			BufferedReader bf = new BufferedReader(isr);
			int line = 0;
			int co=0;
			while ((str = bf.readLine()) != null) {
				line++;
				list.add(line);
				System.out.println("文件内容: " + str);
				a=str.split(",");
				int count=qclService.insert(a[0],a[1],a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10]);
				if(count==1){
					co++;
					list.add(co);
				}
				a=null;
			}
			System.out.println("\n文件行数: " + line);
			// 文本内容
		} catch (Exception e) {
			e.printStackTrace();
		}

	System.out.println(list);
		return R.ok().put("text", list);

	}
	@RequestMapping("/text_caj")
	public R caj(@RequestParam MultipartFile file) throws ParseException {
		List<Object> list = new ArrayList<Object>();
		String str = "";
		String[] a=null;
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "gbk");
			BufferedReader bf = new BufferedReader(isr);
			int line = 0;
			int co=0;
			while ((str = bf.readLine()) != null) {
				line++;
				list.add(line);
				System.out.println("文件内容: " + str);
				a=str.split(",");
				int count=cajService.insertcaj(a[0],a[1],a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12], a[13]);
				if(count==1){
					co++;
					list.add(co);
				}
				a=null;
			}
			System.out.println("\n文件行数: " + line);
			// 文本内容
		} catch (Exception e) {
			e.printStackTrace();
		}

	System.out.println(list);
		return R.ok().put("text", list);

	}
	@RequestMapping("/text_lsj")
	public R lsj(@RequestParam MultipartFile file) throws ParseException {
		List<Object> list = new ArrayList<Object>();
		String str = "";
		String[] a=null;
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "gbk");
			BufferedReader bf = new BufferedReader(isr);
			int line = 0;
			int co=0;
			while ((str = bf.readLine()) != null) {
				line++;
				list.add(line);
				System.out.println("文件内容: " + str);
				a=str.split(",");
				int count=lsjService.insertlsj(a[0],a[1],a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11],a[12], a[13]);
				if(count==1){
					co++;
					list.add(co);
				}
				a=null;
			}
			System.out.println("\n文件行数: " + line);
			// 文本内容
		} catch (Exception e) {
			e.printStackTrace();
		}

	System.out.println(list);
		return R.ok().put("text", list);

	}
	@RequestMapping("/text_yxx")
	public R yxx(@RequestParam MultipartFile file) throws ParseException {
		List<Object> list = new ArrayList<Object>();
		String str = "";
		String[] a=null;
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "gbk");
			BufferedReader bf = new BufferedReader(isr);
			int line = 0;
			int co=0;
			while ((str = bf.readLine()) != null) {
				line++;
				list.add(line);
				System.out.println("文件内容: " + str);
				a=str.split(",");
				int count=yxxService.insertyxx(a[0],a[1],a[2], a[3], a[4], a[5], a[6], a[7]);
				if(count==1){
					co++;
					list.add(co);
				}
				a=null;
			}
			System.out.println("\n文件行数: " + line);
			// 文本内容
		} catch (Exception e) {
			e.printStackTrace();
		}

	System.out.println(list);
		return R.ok().put("text", list);

	}
	@RequestMapping("/text_zdr")
	public R zdr(@RequestParam MultipartFile file) throws ParseException {
		List<Object> list = new ArrayList<Object>();
		String str = "";
		String[] a=null;
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "gbk");
			BufferedReader bf = new BufferedReader(isr);
			int line = 0;
			int co=0;
			while ((str = bf.readLine()) != null) {
				line++;
				list.add(line);
				System.out.println("文件内容: " + str);
				a=str.split(",");
				int count=zdrService.insertzdr(a[0],a[1],a[2], a[3], a[4], a[5], a[6], a[7], a[8]);
				if(count==1){
					co++;
					list.add(co);
				}
				a=null;
			}
			System.out.println("\n文件行数: " + line);
			// 文本内容
		} catch (Exception e) {
			e.printStackTrace();
		}

	System.out.println(list);
		return R.ok().put("text", list);

	}
}
