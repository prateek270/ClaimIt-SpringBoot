package org.nissan.internalApp.repository;

import java.sql.SQLException;

import org.nissan.internalApp.model.UserProfileModel;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthenticationRepository extends CrudRepository<UserProfileModel, String>{

	public UserProfileModel findByZidAndPassword(String zid, String password) throws SQLException;
	public UserProfileModel findByZid(String zid);
}
