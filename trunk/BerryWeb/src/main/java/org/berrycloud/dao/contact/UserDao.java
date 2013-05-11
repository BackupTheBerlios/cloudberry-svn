package org.berrycloud.dao.contact;

import org.berrycloud.model.entity.contact.User;

public interface UserDao {

	User load(long id);
	
	User get(long id);
	
	void save(User user);
}
