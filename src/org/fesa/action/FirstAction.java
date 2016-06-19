package org.fesa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fesa.pojo.NewsPojo;
import org.fesa.pojo.UserPojo;
import org.fesa.service.MethodService;
import org.json.JSONObject;
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
	
	@ResponseBody
	@RequestMapping(value="/getNewsDataForPage",method=RequestMethod.GET)
	public void getNewsDataForPage(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		Long timestamp=(long) 0;
		String type="";
		String callback="";
		for (String ele : params.keySet()) {
			String[] val=params.get(ele);
			if(ele.equals("ptimestamp"))
				timestamp=Long.valueOf(val[0]);
			else if(ele.equals("ptype"))
				type=String.valueOf(val[0]);
			else if(ele.equals("callback"))
				callback=String.valueOf(val[0]);
		}
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		List<NewsPojo> result= methodservice.getNewsForPage(timestamp, type);
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(callback+"("+JSON.toJSONString(result)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getNewsById",method=RequestMethod.GET)
	public void getNewsById(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		int pid=0;
		String callback="";
		for (String ele : params.keySet()) {
			String[] val=params.get(ele);
			if(ele.equals("pid"))
				pid=Integer.valueOf(val[0]);
			else if(ele.equals("callback"))
				callback=String.valueOf(val[0]);
		}
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		NewsPojo result= methodservice.getNewsById(pid);
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(callback+"("+JSON.toJSONString(result)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
