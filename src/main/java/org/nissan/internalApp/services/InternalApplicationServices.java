package org.nissan.internalApp.services;

import org.nissan.internalApp.model.UserAuthentication;
import org.nissan.internalApp.reimbursement.model.UserLoginPackage;

public interface InternalApplicationServices {

	public UserLoginPackage authenticatUser(UserAuthentication uAuthModel);
	
}
