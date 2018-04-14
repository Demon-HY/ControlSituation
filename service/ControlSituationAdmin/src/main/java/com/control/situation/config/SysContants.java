package com.control.situation.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration  
@EnableAutoConfiguration  
public class SysContants implements Serializable{

	private static final long serialVersionUID = 466599795783194166L;

	/** cookie有效时间，单位秒  */
	public static final int COOKIE_EXPIRE = 1800;

	/** 用户所属的角色列表,%d 为用户ID  */
	public static final String USER_ROLE_LIST = "%d_ROLE_LIST";

    /** 用户所属的菜单列表,%d 为用户ID  */
    public static final String USER_MENU_LIST = "%d_MENU_LIST";
	 
}
