package com.cg.mts.exception;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1970762601225780363L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
