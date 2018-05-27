package com.tonggu.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 * @author samson
 */
public class WebInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	
	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	/**
	 * 所有权限集合
	 */
	Collection<ConfigAttribute> functions = new ArrayList<ConfigAttribute>();

	public WebInvocationSecurityMetadataSourceService() {
		requestMap = loadResourceDefine();
	}

	private Map<RequestMatcher, Collection<ConfigAttribute>> loadResourceDefine() {
		// TODO 初始化 url对应的所有角色对应记录 对functions初始化
		Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		allAttributes.add(new SecurityConfig("ROLE_ADMIN"));
		requestMap.put(new AntPathRequestMatcher("/index"), allAttributes);
		
		return requestMap;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	/**
	 * 由于权限匹配修改为 url 匹配 url 所以 url 对应的 权限 为自身url
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
