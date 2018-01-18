package com.control.situation.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration  
@EnableAutoConfiguration  
public class SysContants implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 466599795783194166L;


	private static Logger logger = Logger.getLogger(SysContants.class);
	 

	 	@Value("${goods.expire.time}")
	    private String STOCKEXPIRE;


		public String getSTOCKEXPIRE() {
			return STOCKEXPIRE;
		}


		public void setSTOCKEXPIRE(String sTOCKEXPIRE) {
			STOCKEXPIRE = sTOCKEXPIRE;
		}
	 	
	 	
}
