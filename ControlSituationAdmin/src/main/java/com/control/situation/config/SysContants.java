package com.control.situation.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration  
@EnableAutoConfiguration  
public class SysContants implements Serializable{

	private static final long serialVersionUID = 466599795783194166L;

	/** cookie有效时间，单位秒  */
	public static final int COOKIE_EXPIRE = 86400;
	 

//	 	@Value("${test}")
//	    private String test;
	 	
	 	
}
