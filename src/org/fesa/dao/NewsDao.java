package org.fesa.dao;

import java.util.List;

import org.fesa.pojo.NewsPojo;

public abstract interface NewsDao
{
  public abstract boolean saveNews(NewsPojo paramNewsPojo);
  
  public abstract List<NewsPojo> getNewsForPage(Long paramLong, String paramString);
  
  public abstract NewsPojo getNewsById(int paramInt);
}
