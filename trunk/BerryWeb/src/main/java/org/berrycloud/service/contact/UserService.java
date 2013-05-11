package org.berrycloud.service.contact;

import org.berrycloud.model.entity.contact.User;

public interface UserService {

	User load(long id);
	
	User get(long id);
	
	void save(User user);
	
}
