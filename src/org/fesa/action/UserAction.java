package org.fesa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fesa.pojo.UserPojo;
import org.fesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userDisplay",method=RequestMethod.GET)
	public String userDisplay(){
		return "/home/usersDisplay";
	}
	@ResponseBody
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	public void getUserData(HttpServletRequest request, HttpServletResponse response){
		List<UserPojo> result=userService.getUsers();
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public String saveUser(HttpServletRequest request, HttpServletResponse response){
		return "success";
	}
}
