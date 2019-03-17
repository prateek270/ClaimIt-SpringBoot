package org.nissan.internalApp.reimbursement.repository;

import org.nissan.internalApp.reimbursement.model.ApproverMapModel;
import org.springframework.data.repository.CrudRepository;

public interface ApproverMappingRepository extends CrudRepository<ApproverMapModel, String> {

	public ApproverMapModel findByFromAndType(String from, String type);
	
}
