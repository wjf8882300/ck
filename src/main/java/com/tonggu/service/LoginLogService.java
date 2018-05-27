package com.tonggu.service;

import java.util.Map;

import com.tonggu.vo.ResultVo;

public interface LoginLogService {

	/**
	 * 保存系统日志
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo saveLoginLog(Map<String, Object> params);
}
