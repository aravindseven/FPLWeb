package com.fpl.crypt;

import java.io.IOException;

@SuppressWarnings("serial")
public class CEFormateException extends IOException {

	public CEFormateException(final String errorMsg) {
		super(errorMsg);
	}
}
