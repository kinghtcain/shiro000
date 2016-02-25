package com.atguigu.shiro.spring;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresRoles;

public class MyService {

	@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("-->MyService" + new Date());
	}
}
