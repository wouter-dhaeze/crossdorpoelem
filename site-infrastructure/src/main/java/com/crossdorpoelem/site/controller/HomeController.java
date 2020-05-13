package com.crossdorpoelem.site.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	private String test;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("newsItems", getStaticNewsItems());
		return "index";
	}

	private List<NewsItem> getStaticNewsItems() {
		return Arrays.asList(new NewsItem(), new NewsItem());
	}

	public class NewsItem {
		private String title;
		private String author;
	}

}
