package org.nissan.internalApp.reimbursement.repository;

import java.util.List;

import org.nissan.internalApp.reimbursement.model.ActiveRequestsModel;
import org.springframework.data.repository.CrudRepository;

public interface ActiveRequestRepository extends CrudRepository<ActiveRequestsModel,String> {

	public List<ActiveRequestsModel> findByInitiator(String zid);
	public List<ActiveRequestsModel> findByCurrentapproverAndStatus(String zid, String status);
	public List<ActiveRequestsModel> findByCurrentapprover(String zid);
	public ActiveRequestsModel findByRequid(String requid);
	public List<ActiveRequestsModel> findByStatus(String status);
	
}
	