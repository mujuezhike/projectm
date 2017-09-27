package com.mujuezhike.projectm.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BaseTestController 测试控制类
 * @author mujuezhike
 * @date   20170927
 */
@RestController
public class BaseTestController {
	
	 	@RequestMapping("/greeting")
	    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
	        return "ok";
	    }

}
