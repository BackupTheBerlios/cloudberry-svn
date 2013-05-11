package service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import jpa.Transactional;
import model.entity.UserTest;

@Transactional
public class UserServiceImpl {

	@Inject
	private EntityManager em;
	
	public void save(UserTest user){
		em.persist(user);
	}
	
	public UserTest load(long id){
		return em.find(UserTest.class, id);
	}
	
}
