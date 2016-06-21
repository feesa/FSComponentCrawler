package org.fesa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fesa.pojo.NewsPojo;
import org.fesa.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/news")
public class NewsAction {

	@Autowired
	private NewsService newsService;
	
	@ResponseBody
	@RequestMapping(value="/pushNewsBywilddog",method=RequestMethod.POST)
	public String pushNewsBywilddog(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		newsService.pushNewsBywilddog(params);
		return JSON.toJSONString("成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/pushNewsByNative",method=RequestMethod.POST)
	public String pushNewsByNative(HttpServletRequest request, HttpServletResponse response){
		Map<String,String[]> params= request.getParameterMap();
		newsService.pushNewsByNative(params);
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
		List<NewsPojo> result= newsService.getNewsForPage(timestamp, type);
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
		NewsPojo result= newsService.getNewsById(pid);
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(callback+"("+JSON.toJSONString(result)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
