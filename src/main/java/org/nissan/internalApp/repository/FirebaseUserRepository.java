package org.nissan.internalApp.repository;

import org.nissan.internalApp.model.FirebaseUserModel;
import org.springframework.data.repository.CrudRepository;

public interface FirebaseUserRepository extends CrudRepository<FirebaseUserModel, String>{

	public FirebaseUserModel findByZid(String zid);
}
