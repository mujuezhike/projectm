package com.mujuezhike.projectm.base.object.service;

import java.util.List;
import java.util.Map;

public interface ObjectService{
	
	public Map<String,Object> head(String tablename);
	
	public Map<String,Object> headByTableId(String tableid);
	
	public Map<String,Object> headAdd(String tablename, Map<String, Object> beanmap);
	
	public Map<String,Object> getOri(String tablename,String id);
	
	public Map<String,Object> get(String tablename,String id);
	
	public Map<String,Object> getAddtion(Map<String,Object> map,Map<String,Object> headmap);
	
	public Map<String,Object> getByTableId(String tableid,String id);
	
	public List<Map<String,Object>> listOri(String tablename,Map<String,Object> beanmap);
	
	public List<Map<String,Object>> list(String tablename,Map<String,Object> beanmap);
	
	public Map<String,Object> add(String tablename,Map<String,Object> beanmap);
	
	public List<Map<String,Object>> addlist(String tablename,List<Map<String,Object>> beanmaplist);

}
