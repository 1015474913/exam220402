package cn.kgc.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edu/admin")
public class IndexController {

	/*@RequestMapping("/toIndex")
	public String toIndex() {
		return "admin/index";

	}*/

	@RequestMapping("/toLeft")
	public String toLeft() {
		System.out.println();
		return "admin/left";
	}

	@RequestMapping("/toNav")
	public String toNav() {
		System.out.println();
		return "admin/nav";
	}

	@RequestMapping("/toHome")
	public String toHome() {
		System.out.println();
		return "admin/home";
	}

	@RequestMapping("/toHead")
	public String toHead() {
		System.out.println();
		return "admin/head";
	}
}
