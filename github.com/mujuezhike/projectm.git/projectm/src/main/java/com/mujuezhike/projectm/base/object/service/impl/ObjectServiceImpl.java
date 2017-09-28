package com.mujuezhike.projectm.base.object.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mujuezhike.projectm.base.object.dao.ObjectDao;
import com.mujuezhike.projectm.base.object.service.ObjectService;

@Component
public class ObjectServiceImpl implements ObjectService{
	
	@Autowired
	private ObjectDao objectDao; 
	
	private static final String oritable = "s_object_table";
	private static final String oricolumn = "s_object_table_column";
	
	@Override
	public Map<String, Object> head(String tablename) {
		//表信息
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("table_name", "_e_"+tablename);
		bmap.put("limit", "1");
		List<Map<String, Object>> list = objectDao.listByBean(oritable, bmap);
		Map<String, Object> map = list.get(0);
		
		//表字段信息
		Map<String, Object> inmap = new HashMap<String, Object>();
		inmap.put("fid", "_e_"+map.get("id"));
		List<Map<String, Object>> ilist = objectDao.listByBean(oricolumn, inmap);
		map.put("_table_columns", ilist);
		return map;
	}

	@Override
	public Map<String, Object> headByTableId(String tableid) {
		//表信息
		Map<String, Object> map = getOri(oritable, tableid);
		
		//表字段信息
		Map<String, Object> inmap = new HashMap<String, Object>();
		inmap.put("fid", "_e_"+tableid);
		List<Map<String, Object>> list = objectDao.listByBean(oricolumn, inmap);
		map.put("_table_columns", list);
		return map;
	}
	
	@Override
	public Map<String, Object> headAdd(String tablename, Map<String, Object> beanmap) {
		
		
		
		return add(tablename, beanmap);
	}

	@Override
	public Map<String, Object> getOri(String tablename, String id) {
		//数据信息
		Map<String, Object> map = objectDao.getById(tablename, id);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> get(String tablename, String id) {
		
		//数据信息
		Map<String, Object> map = objectDao.getById(tablename, id);
		
		if(map!=null) {
			//表头信息
			Map<String, Object> headmap = head(tablename);
			
			map = getAddtion(map,headmap);
		}
		
		return map;
	}
	
	@Override
	public Map<String, Object> getAddtion(Map<String, Object> map, Map<String, Object> headmap) {
		
		List<Map<String, Object>> columnlist = (List<Map<String, Object>>)headmap.get("_table_columns");
		
		for(Map<String, Object> column:columnlist) {
			String columnType = column.get("column_type").toString();
			//各种关联情况
			if(columnType.equals("10")) {
				//需要变成只有键值对
				String columnName = column.get("column_name").toString();
				String relationTableName = column.get("relation_table_name").toString();
				Object columnValue = map.get(columnName);
				if(columnValue!=null) {
					
					String columnValueStr = columnValue.toString();
					Map<String, Object> childmap = getOri(relationTableName, columnValueStr);
					map.put("_"+columnName, childmap);
				}
			}
			
			if(columnType.equals("11")) {
				String columnName = column.get("column_name").toString();
				String relationTableName = column.get("relation_table_name").toString();
				Object columnValue = map.get(columnName);
				if(columnValue!=null) {
					
					String columnValueStr = columnValue.toString();
					Map<String, Object> childmap = getOri(relationTableName, columnValueStr);
					map.put("_"+columnName, childmap);
				}
			}
			
			if(columnType.equals("21")) {
				String columnName = column.get("column_name").toString();
				String relationTableName = column.get("relation_table_name").toString();
				String relationColumnName = column.get("relation_column_name").toString();
				
				Map<String, Object> inmap = new HashMap<String, Object>();
				inmap.put(relationColumnName, "_e_"+map.get("id").toString());
				List<Map<String, Object>> childlist = objectDao.listByBean(relationTableName, inmap);
				map.put(columnName, childlist);
			}
		}
		
		return map;
	}

	@Override
	public Map<String, Object> getByTableId(String tableid, String id) {
		//数据信息
		Map<String, Object> map = objectDao.getById(oritable, tableid);
		
		String tableName = map.get("table_name").toString();
		
		return get(tableName,id);
	}

	@Override
	public List<Map<String, Object>> listOri(String tablename, Map<String, Object> beanmap) {
		
		List<Map<String, Object>> list = objectDao.listByBean(tablename, beanmap);
		
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> list(String tablename, Map<String, Object> beanmap) {
		
		List<Map<String, Object>> list = objectDao.listByBean(tablename, beanmap);
		
		if(list!=null && list.size()>0) {
			//表头信息
			Map<String, Object> headmap = head(tablename);
			
			for(Map<String, Object> map:list) {
				
				map = getAddtion(map,headmap);
				
			}
		}
				
		return list;
		
	}

	@Override
	public Map<String, Object> add(String tablename, Map<String, Object> beanmap) {
		return objectDao.add(tablename, beanmap);
	}

	@Override
	public List<Map<String, Object>> addlist(String tablename, List<Map<String, Object>> beanmaplist) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
