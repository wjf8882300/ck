package com.tonggu.component;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.tonggu.annotation.AutoDispatch;
import com.tonggu.exception.JJException;
import com.tonggu.vo.ResultVo;

/**
 * 日志切面 默认在Controller的所有方法生效
 * 
 * @author larry
 *
 */
@Aspect
@Component
public class WebLayerAspect {

	@Autowired
	MetaDataManager metaDataManager;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Around("com.tonggu.component.SystemArchitecture.inWebLayer()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		//RateLimiter
		Object result = null;
		Throwable error = null;
		Class<?> type = pjp.getTarget().getClass();
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		Object[] args = pjp.getArgs();
		Logger logger = Logger.getLogger(this.getClass());
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		
		sw.start(ms.getName());
		try {
			if(ms.getName().contains("dipatch")) {
				AutoDispatch annotation = pjp.getTarget().getClass().getAnnotation(AutoDispatch.class);
				Class<?>[] services = annotation.serviceInterface();
				for (Class<?> cls : services) {
					String _k=new StringBuilder().append(cls.getName()).append(args[2].toString()).toString();
					if (metaDataManager.getMethodCache().containsKey(_k)) {
						method = metaDataManager.getMethodCache().get(_k);
					}
				}
			}						 
			result = pjp.proceed();
			return result;
		} catch (Throwable e) {
			if (method.getReturnType() == ResultVo.class) {
				
				Throwable ex = e;
				do  {
					if(JJException.class.isAssignableFrom(ex.getClass())){
						String err = ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
						result = new ResultVo(false, err.substring(err.indexOf(' ') + 1));
						return result;
					}					
					else if( OptimisticLockingFailureException.class.isAssignableFrom(ex.getClass())) {
						result = new ResultVo(false, "操作失败，请稍后重试！");
						return result;
					}
					ex = ex.getCause();
				} while(ex != null);
			}
			error = e;
			logger.error("----------error with " + type.getName() + "." + method.getName() + " with argv " + Arrays.toString(args) + "\n" + e.getMessage(), e);
			throw e;
		}
		finally {
			sw.stop();
			logger.info(type.getSimpleName() + "." + ms.getName() + " " + Arrays.toString(pjp.getArgs()) + " " + sw.getTotalTimeMillis() + " " + result + " " + error);
		}
	}
}
