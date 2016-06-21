package org.fesa.action;

import java.util.List;
import org.fesa.pojo.UserPojo;
import org.fesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	public String getUserData(Model model){
		List<UserPojo> result=userService.getUsers();
		model.addAttribute(result);
		return "/news/newsDisplay";
	}
}
