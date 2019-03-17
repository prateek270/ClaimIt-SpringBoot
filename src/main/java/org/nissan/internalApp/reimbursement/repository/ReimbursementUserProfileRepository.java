package org.nissan.internalApp.reimbursement.repository;

import org.nissan.internalApp.model.UserProfileModel;
import org.nissan.internalApp.reimbursement.model.UserProfileReimbursementModel;
import org.springframework.data.repository.CrudRepository;

public interface ReimbursementUserProfileRepository<T extends UserProfileModel> extends CrudRepository<T, String> {
	
	public UserProfileReimbursementModel findByZid(String zid);
	
}