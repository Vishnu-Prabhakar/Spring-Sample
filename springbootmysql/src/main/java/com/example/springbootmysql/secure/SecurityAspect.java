package com.example.springbootmysql.secure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.springbootmysql.annotation.Secure;
import com.example.springbootmysql.exception.UnauthorizedException;

@Aspect
@Component
public class SecurityAspect {

	final Logger log = LoggerFactory.getLogger(SecurityAspect.class);

	@Around("@annotation(secure)")
	protected Object authenticationApiCall(ProceedingJoinPoint joinPoint, Secure secure) throws Throwable {
		try{
			String accessToken = joinPoint.getArgs()[0].toString();
			if(null == accessToken || accessToken.length() == 0)
				throw new UnauthorizedException("Unauthorised, accessToken Header might be missing in your request");
		}catch (Exception e){
			throw new UnauthorizedException("Unauthorised, accessToken Header might be missing in your request");
		}
		return joinPoint.proceed();
	}


}
