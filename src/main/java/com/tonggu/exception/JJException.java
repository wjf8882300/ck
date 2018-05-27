package com.tonggu.exception;

public class JJException extends Exception {
	
	private static final long serialVersionUID = -8329795082712998332L;
	
	public JJException() {
	}

	public JJException(String message) {
		super(message);
	}

	public JJException(Throwable throwable) {
		super(throwable);
	}

	public JJException(String message, Throwable throwable) {
		super(message, throwable);
	}
	

}