package org.fesa.service;

import java.util.List;
import java.util.Map;

import org.fesa.pojo.NewsPojo;
import org.fesa.pojo.UserPojo;

public interface MethodService {
	
	List<UserPojo> method1();
	
	String pushDataForNews(Map<String,String[]> pdata);
	
	List<NewsPojo> getNewsForPage(Long timestamp, String type);
	
	NewsPojo getNewsById(int id);
	
	String pushDataForWebData(Map<String, String[]> pdata);
	
	void getPageData();
}
