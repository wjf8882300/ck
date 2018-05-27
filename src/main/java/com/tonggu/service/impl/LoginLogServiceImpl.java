package com.tonggu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonggu.entity.LoginLogEntity;
import com.tonggu.repository.LoginLogRepository;
import com.tonggu.service.LoginLogService;
import com.tonggu.vo.ResultVo;

@Service("@loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	private LoginLogRepository loginLogRepository;
	
	@Transactional(readOnly = false)
	@Override
	public ResultVo saveLoginLog(Map<String, Object> params) {
		LoginLogEntity loginLogEntity = new LoginLogEntity();
		loginLogEntity.setUserId((String)params.get("userId"));
		loginLogEntity.setIpAddress((String)params.get("ipAddress"));
		loginLogEntity.setBasicModelProperty(params.get("userId").toString(), true);
		loginLogRepository.save(loginLogEntity);
		return new ResultVo(true);
	}

}
