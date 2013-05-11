package org.berrycloud.service.contact;

import javax.inject.Inject;


import org.berrycloud.core.tx.Transactional;
import org.berrycloud.dao.contact.UserDao;
import org.berrycloud.model.entity.contact.User;

@Transactional
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao dao;
	
	@Override
	public User load(long id) {
		return dao.load(id);
	}

	@Override
	public User get(long id) {
		return dao.get(id);
	}

	@Override
	public void save(User user) {
		dao.save(user);
	}

}
