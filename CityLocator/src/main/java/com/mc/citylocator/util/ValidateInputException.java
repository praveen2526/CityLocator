package com.mc.citylocator.util;

public class ValidateInputException extends Exception {
	private static final long serialVersionUID = 1L;
	private String desc;
	public ValidateInputException() {
		
	}
	public ValidateInputException(String desc) {
		super(desc);
	}
	public ValidateInputException(String desc, Throwable exception) {
		super(desc, exception);
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
