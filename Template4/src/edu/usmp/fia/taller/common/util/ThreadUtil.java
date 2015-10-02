package edu.usmp.fia.taller.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.usmp.fia.taller.common.action.ActionServlet;

public class ThreadUtil {
	
	protected static final Logger log = LogManager.getLogger(ThreadUtil.class);
	
	public static int getCallerModule(){
	   StackTraceElement[] steList = Thread.currentThread().getStackTrace();
	   
	   for (int i=steList.length-1;i>=0;i--){
		   StackTraceElement ste = steList[i];
		   //log.info("ste.getClassName():" + ste.getClassName());
		   //if(ste.getClassName().contains("edu.usmp.fia.taller.commons.dao.modules")){
		   if(ste.getClassName().contains("edu.usmp.fia.taller.common.action")){
			   return 0;
       	   }else if(ste.getClassName().contains("edu.usmp.fia.taller.seguimiento.dao")){
       		   return 1;
       	   }else if(ste.getClassName().contains("edu.usmp.fia.taller.ejemplo.dao")){
       		   return 2;
       	   }
	   }
	   //return -1;
	   return 0;
	}
	
}
