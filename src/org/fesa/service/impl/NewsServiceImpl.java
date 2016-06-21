package org.fesa.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.fesa.dao.NewsDao;
import org.fesa.pojo.NewsPojo;
import org.fesa.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import com.wilddog.client.Wilddog;

public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsDao newsDao;

	@Override
	public String pushNewsBywilddog(Map<String, String[]> pdata) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String [] obj =pdata.get("data");
			String [] obj_url=pdata.get("url");
			if(obj!=null&&obj.length>0){
				NewsPojo pojo = JSONObject.parseObject(obj[0],NewsPojo.class);
				String stime=pojo.getTime().replace("　来源:", "");
				pojo.setTime(stime);
				pojo.setTimestamp(sdf.parse(stime).getTime());
				pojo.setUrl(obj_url[0]);
				newsDao.saveNews(pojo);
			}else
				return "";
		}catch(Exception ex){
			System.out.println("错误："+ex.getMessage());
		}
		return "success";
	}

	@Override
	public String pushNewsByNative(Map<String, String[]> pdata) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String [] obj =pdata.get("data");
			String [] obj_url=pdata.get("url");
			if(obj!=null&&obj.length>0){
				NewsPojo pojo = JSONObject.parseObject(obj[0],NewsPojo.class);
				String stime=pojo.getTime().replace("　来源:", "");
				pojo.setTime(stime);
				pojo.setTimestamp(sdf.parse(stime).getTime());
				pojo.setUrl(obj_url[0]);
				Wilddog ref = new Wilddog("https://201605111151fei.wilddogio.com");
				ref.child("news").push().setValue(pojo);
				Thread.sleep(100);
			}else
				return "";
		}catch(Exception ex){
			System.out.println("错误："+ex.getMessage());
		}
		return "success";
	}
	
	@Override
	public List<NewsPojo> getNewsForPage(Long timestamp, String type) {
		List<NewsPojo> list_news= newsDao.getNewsForPage(timestamp, type);
		return list_news;
	}

	@Override
	public NewsPojo getNewsById(int id) {
		NewsPojo pojo=newsDao.getNewsById(id);
		return pojo;
	}
}
