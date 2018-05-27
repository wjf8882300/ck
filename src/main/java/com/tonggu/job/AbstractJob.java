package com.tonggu.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonggu.vo.ResultVo;

/**
 * 
 * Job任务调度需要实现此接口
 * <p>
 * 添加 JOB监听 启动前检查是否已运行，如果已运行则不运行此次JOB;为了防止监听影响JOB正常运行,如果监听报错 则JOB会继续运行
 * </p>
 * 
 * @author larry
 * @version 1.0
 */
abstract class AbstractJob {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	abstract protected void execute();
	
	private String jobRunDetailId = null;

	/**
	 * 获取当前job的名称，主要以功能命名
	 * 
	 * @return
	 */
	abstract protected String getJobName();

	/**
	 * 调度任务执行逻辑
	 * <p>
	 * <b>去掉final job使用到了代理,final类型的不能被代理 导致父类不能注入;故最后去掉final</b>
	 * </p>
	 */
	public ResultVo invoke() {
		logger.info("{} invoke....", this.getClass().getName());

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					execute();
					callback();
				} catch (Exception e) {
					catchException(e);
				} finally {
					finallyCallBack();
				}
			}
		}).start();
		return new ResultVo(true);
	}

	/**
	 * 任务调度完成回调扩展接口 eg: 任务完成后发送邮件等
	 */
	protected void callback() {
	}

	protected void catchException(Throwable t) {
		stopJob(t.getMessage());
	}

	protected void finallyCallBack() {
		stopJob(null);
	}

	protected void stopJob(String exception) {
		if (jobRunDetailId != null) {
			try {
				logger.info("{}【{}】JOB停止运行....", this.getClass().getName(), getJobName());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "JOB监听停止异常", e);
			} finally {
				jobRunDetailId = null;
				logger.info(this.getClass().getName() + "JOB运行结束...");
			}
		}
	}

}
