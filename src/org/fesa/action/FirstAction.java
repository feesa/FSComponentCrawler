package org.fesa.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fesa.pojo.UserPojo;
import org.fesa.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/news")
public class FirstAction {

	@Autowired
	private MethodService methodservice;
	
	@RequestMapping(value="/getNews",method=RequestMethod.GET)
	public String test1(Model model){
		model.addAttribute("users", methodservice.method1());
		return "/news/newsDisplay";
	}
	@ResponseBody
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	public String getUserData(){
		List<UserPojo> result=methodservice.method1();
		return JSON.toJSONString(result);
	}
	@ResponseBody
	@RequestMapping(value="/updateWebDataForNews",method=RequestMethod.POST)
	public String updateWebDataForNews(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		methodservice.pushDataForNews(params);
		return JSON.toJSONString("成功");
	}
	@ResponseBody
	@RequestMapping(value="/updateWebDataForWebData",method=RequestMethod.POST)
	public String updateWebDataForWebData(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		methodservice.pushDataForWebData(params);
		return JSON.toJSONString("成功");
	}
}
