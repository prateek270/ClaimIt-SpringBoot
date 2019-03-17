package org.nissan.internalApp.reimbursement.repository;

import org.nissan.internalApp.reimbursement.model.ActiveRequestImageData;
import org.springframework.data.repository.CrudRepository;

public interface ActiveRequestImageDataRepository extends CrudRepository<ActiveRequestImageData, String>{

	public ActiveRequestImageData findByImgId(String imgId);
}
