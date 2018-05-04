package com.banyan.FullLoadRequest.config;

import com.banyan.FullLoadRequest.models.enums.Databases;

public class DatabaseContextHolder {
	   private static final ThreadLocal<Databases> contextHolder = new ThreadLocal<Databases>();

	   public static void setDbType(Databases databases) {
	       if(databases == null){
	           throw new NullPointerException();
	       }
	      contextHolder.set(databases);
	   }

	   public static Databases getDbType() {
	      return (Databases) contextHolder.get();
	   }

	   public static void clearDbType() {
	      contextHolder.remove();
	   }
}
