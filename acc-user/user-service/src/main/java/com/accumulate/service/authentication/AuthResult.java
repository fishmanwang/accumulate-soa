package com.accumulate.service.authentication;

public class AuthResult {

	private boolean success;

	private AuthResultType type;

	public static AuthResult build() {
		AuthResult ar = new AuthResult();
		ar.setSuccess(true);
		ar.setType(AuthResultType.ACTIVE);
		return ar;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public AuthResultType getType() {
		return type;
	}

	public void setType(AuthResultType type) {
		this.type = type;
	}
}
