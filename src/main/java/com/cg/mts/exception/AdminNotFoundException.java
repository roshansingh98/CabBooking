package com.cg.mts.exception;

public class AdminNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5595964428948080655L;


    public AdminNotFoundException() {
        super();
    }

    public AdminNotFoundException(String msg) {
        super(msg);
    }

}
