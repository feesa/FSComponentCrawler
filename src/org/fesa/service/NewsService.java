package org.fesa.service;

import java.util.List;
import java.util.Map;

import org.fesa.pojo.NewsPojo;

public interface NewsService {

	/*
	 * Ұ���ӿ�:�������
	 */
	String pushNewsBywilddog(Map<String,String[]> pdata);
	
	/*
	 * ���ؽӿ�:�������
	 */
	String pushNewsByNative(Map<String,String[]> pdata);
	
	/*
	 * ���ؽӿ�:��ѯ���� type:loadmore,loadnew
	 */
	List<NewsPojo> getNewsForPage(Long timestamp, String type);
	
	/*
	 * ���ؽӿ�:����ID��ȡ����ʵ��
	 */
	NewsPojo getNewsById(int id);
	
	
	
}
