package com.wgn.springaop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyLog {
	private static final Log log = LogFactory.getLog(MyLog.class);
	@Before("execution(* com.wgn.service.impl.*.*(..)")
	public void before(){
		log.info("开始执行方法");
	}
	
	@After("execution(* com.wgn.service.impl.*.*(..)")
	public void after(){
		log.info("结束执行方法");
	}
	
	@Around("execution(* com.wgn.service.impl.*.*(..)")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		log.info("环绕前");
		log.info("方法"+jp.getSignature());
		Object result=jp.proceed();
		log.info("环绕后");		
		return result;
		
		
	}
}
