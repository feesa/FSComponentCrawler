package org.fesa.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.fesa.dao.UserDao;
import org.fesa.pojo.NewsPojo;
import org.fesa.pojo.UserPojo;
import org.fesa.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wilddog.client.Wilddog;

@Service
public class MethodServiceImpl implements MethodService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserPojo> method1() {
		List<UserPojo> list_result=userDao.getAllUser();
		return list_result;
	}

	@Override
	public String pushDataForNews(Map<String, String[]> pdata) {
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
	public String pushDataForWebData(Map<String, String[]> pdata) {
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
				ref.child("webdata").push().setValue(pojo);
				Thread.sleep(100);
			}else
				return "";
		}catch(Exception ex){
			System.out.println("错误："+ex.getMessage());
		}
		return "success";
	}
}
