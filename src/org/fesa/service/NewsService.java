package org.fesa.service;

import java.util.List;
import java.util.Map;

import org.fesa.pojo.NewsPojo;

public interface NewsService {

	/*
	 * 野狗接口:添加新闻
	 */
	String pushNewsBywilddog(Map<String,String[]> pdata);
	
	/*
	 * 本地接口:添加新闻
	 */
	String pushNewsByNative(Map<String,String[]> pdata);
	
	/*
	 * 本地接口:查询新闻 type:loadmore,loadnew
	 */
	List<NewsPojo> getNewsForPage(Long timestamp, String type);
	
	/*
	 * 本地接口:根据ID获取新闻实体
	 */
	NewsPojo getNewsById(int id);
	
	
	
}
