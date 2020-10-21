package com.cg.mts.exception;

public class CustomerNotFoundException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1970762601225780363L;

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
