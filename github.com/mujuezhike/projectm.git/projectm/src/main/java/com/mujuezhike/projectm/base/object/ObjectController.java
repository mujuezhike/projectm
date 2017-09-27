package com.mujuezhike.projectm.base.object;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ObjectController 通用控制类
 * @author mujuezhike
 * @date   20170927
 */
@RestController
public class ObjectController {
	
	   /** 获取表元信息  **/
	   @RequestMapping(value = "/o/h/{TABLENAME}")
	   public String head( HttpServletRequest request
			              ,HttpServletResponse response
			 			  ,@PathVariable("TABLENAME") String tableName){
		   return "ok"+ tableName;
	   }
	
	   /** 添加表元信息  **/
	   @RequestMapping(value = "/o/ha/{TABLENAME}")
	   public String headadd( HttpServletRequest request
			              	 ,HttpServletResponse response
			              	 ,@PathVariable("TABLENAME") String tableName){
		   return "ok"+ tableName;
	   }
	
		/** 获取列表信息 **/
		/**
		 * @author mujuezhike
		 * @date   20170927
		 * 固定  搜索参数
		 * ?limit=10
		 * ?offset=10
		 * ?page=2
		 * ?perpage=100
		 * ?sortby=name
		 * ?order=asc
		 * 参数  搜索参数  都以 and 关联
		 * ?oid=_e_312312      oid =  312312
		 * ?oid=_gt_1233       oid >  1233
		 * ?oid=_lt_1233       oid <  1233
		 * ?oid=_n_1233        oid != 1233
		 * ?oid=_l_1233        oid LIKE '%1233%'
		 * ?ned.emmd=_e_312312 ned.emmd = 312312 //关联表
		 */
		@RequestMapping(value = "/o/l/{TABLENAME}")
		public String list(    HttpServletRequest request
				              ,HttpServletResponse response
				 			  ,@PathVariable("TABLENAME") String tableName){
			return "ok"+ tableName;
		}
	
	 	/** 获取单条信息  **/
		@RequestMapping(value = "/o/g/{TABLENAME}/{ID}")
	    public String get(     HttpServletRequest request
	            		      ,HttpServletResponse response
	    		              ,@PathVariable("ID") String id
	    		              ,@PathVariable("TABLENAME") String tableName){
	        return "ok"+id+ tableName;
	    }
		
		/** 添加单条信息  **/
		@RequestMapping(value = "/o/a/{TABLENAME}")
	    public String add(     HttpServletRequest request
	            		      ,HttpServletResponse response
	    		              ,@PathVariable("TABLENAME") String tableName){
	        return "ok"+tableName;
	    }

}
