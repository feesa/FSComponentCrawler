package org.fesa.service;

import java.util.List;
import java.util.Map;

import org.fesa.pojo.UserPojo;

public interface MethodService {
	
	List<UserPojo> method1();
	
	String pushDataForNews(Map<String,String[]> pdata);
	
	String pushDataForWebData(Map<String, String[]> pdata);
}
