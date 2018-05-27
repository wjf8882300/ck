package com.tonggu.component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.tonggu.annotation.Idempotent;
import com.tonggu.annotation.Rules;
import com.tonggu.exception.JJException;
import com.tonggu.validate.RulesCache;
import com.tonggu.validator.SLValidator;
import com.tonggu.validator.SLValidatorMapAnnotation;

@Aspect
@Component
@Slf4j
public class ServiceLayerAspect implements Ordered {
	  private static final int DEFAULT_MAX_RETRIES = 3;

	    private int maxRetries = DEFAULT_MAX_RETRIES;
	    private int order = 100;

	    public void setMaxRetries(int maxRetries) {
	        this.maxRetries = maxRetries;
	    }

	    public int getOrder() {
	        return this.order;
	    }

	    public void setOrder(int order) {
	        this.order = order;
	    }

		@Around("com.tonggu.component.SystemArchitecture.businessService()")
		@Order(Ordered.HIGHEST_PRECEDENCE)
		public Object log(ProceedingJoinPoint pjp) throws Throwable {
			Object result = null;
			Throwable error = null;
			Class<?> type = pjp.getTarget().getClass();
			MethodSignature ms = (MethodSignature) pjp.getSignature();
			Object[] args = pjp.getArgs();
			StopWatch sw = new StopWatch(getClass().getSimpleName());
			sw.start(ms.getName());
			
			try {
				//输入参数验证
				Method method = ms.getMethod();
				Rules rules = RulesCache.getAnnotationRulesByMethod(method);
				if(rules == null) {
					Class<?>[] interfaces = type.getInterfaces();
					if(interfaces.length != 0) {
						Class<?> clz = interfaces[0];
						method = clz.getMethod(method.getName(), method.getParameterTypes());
						rules = RulesCache.getAnnotationRulesByMethod(method);
					}
				}
				if (rules != null && args.length > 0) {
					Object args0 = args[0];
					SLValidator slValidator = validate(args0, rules);
					if (slValidator.hasErrors()) {
						String messages = slValidator.toString();
						log.warn("{} 注解数据校验不通过:入参为{},校验结果为:{}", method.toString(), args0.toString());
						throw new JJException(messages);
					}
				}
				
				Idempotent idempotent = method.getAnnotation(Idempotent.class);
				if(idempotent!=null) {
					int numAttempts = 0;
					OptimisticLockingFailureException lockFailureException;
			        do {
			            numAttempts++;
			            try {
			                return pjp.proceed();
			            }
			            catch(OptimisticLockingFailureException ex) {
			                lockFailureException = ex;
			            }
			            
			        } while(numAttempts <= maxRetries);
			        throw lockFailureException;
				}
				else {
					return pjp.proceed();
				}
				
			} catch (Throwable e) {
				log.error("----------error with " + type.getName() + "." + ms.getName() + " with argv " + Arrays.toString(args) + "\n" + e.getMessage(), e);
				error = e;
				throw e;
			} finally {
				sw.stop();
				log.info(type.getSimpleName() + "." + ms.getName() + " " + Arrays.toString(pjp.getArgs()) + " " + sw.getTotalTimeMillis() + " " + result + " " + error);
			}
		}
		
		/**
		 * 支持验证类型
		 * <p>
		 * Map
		 * </p>
		 * <p>
		 * 
		 * </p>
		 * 
		 * @param parameters
		 * @param rules
		 * @return
		 * @throws SLException
		 */
		@SuppressWarnings("unchecked")
		private SLValidator validate(Object parameters, Rules rules) throws JJException {
			SLValidator slValidator = null;
			if (parameters instanceof Map) {
				slValidator = new SLValidatorMapAnnotation((Map<String, Object>) parameters, rules);
			} else {
				throw new JJException("数据验证失败");
			}
			return slValidator;
		}

}
