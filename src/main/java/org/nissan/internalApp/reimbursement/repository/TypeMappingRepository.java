package org.nissan.internalApp.reimbursement.repository;

import org.nissan.internalApp.reimbursement.model.TypeMapModel;
import org.springframework.data.repository.CrudRepository;

public interface TypeMappingRepository extends CrudRepository<TypeMapModel	, String> {

	
}
