package com.tonggu.vo;

import java.io.Serializable;
import java.util.Map;

import lombok.NoArgsConstructor;

import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.tonggu.util.Json;

/**
 * 请求结果
 * 
 * @author zhanghao
 * @version $Revision:1.0.0, $Date: 2014年6月19日 下午3:44:53 $
 */
@NoArgsConstructor
public class ResultVo implements Serializable {

	private static final long serialVersionUID = 4116312283845727413L;
	public Map<String, Object> result = Maps.newHashMap();

	public ResultVo(boolean isSuccess) {
		this(isSuccess, isSuccess ? "操作成功!" : "操作失败!");
	}

	public ResultVo(boolean isSuccess, Object message) {
		this(isSuccess, message, null);
	}
	
	public ResultVo(boolean isSuccess, Object message, Object data) {
		renderResult(isSuccess, message, data);
	}

	private void renderResult(boolean success, Object message, Object data) {
		result.put(success ? "success" : "error", true);
		result.put("message", message);
		result.put("data", data);
	}

	public void putValue(String key, Object value) {
		result.put(key, value);
	}

	public Object getValue(String key) {
		return result.get(key);
	}
	
	public static boolean isSuccess(final ResultVo result) {
		if(result == null)
			return false;
		
		if (StringUtils.isEmpty(result.result.get("success"))) {
			return false;
		}
		return (boolean) result.result.get("success");
	}

	public String toString() {
		if(!StringUtils.isEmpty(result.get("data")) && isSuccess(this)) {
			return Json.ObjectMapper.writeValue(result.get("data"));
		}
		return Json.ObjectMapper.writeValue(this);
	}
}