package jpa.em;

import java.util.Stack;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import model.entity.UserTest;

import org.apache.log4j.Logger;

import test.TestService;



@ApplicationScoped
public class EntityManagerStoreImpl implements EntityManagerStore {

	@Inject
	private Logger log ;//= Logger.getLogger(EntityManagerStoreImpl.class);

	@Inject
	private TestService ttest;
	
	
	@PersistenceUnit(unitName= "testPersistence1")
	private EntityManagerFactory emf;

	private ThreadLocal<Stack<EntityManager>> emStackThreadLocal = new ThreadLocal<>();

	
	@Override
	public EntityManager get() {
		 final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
	      if (entityManagerStack == null || entityManagerStack.isEmpty()) {
	         
	         return null;
	      } else
	         return entityManagerStack.peek();
	}

	@Override
	public EntityManager register() {
		Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
		if (entityManagerStack == null) {
			entityManagerStack = new Stack<EntityManager>();
			emStackThreadLocal.set(entityManagerStack);
		}
		log.info("=============="+Persistence.getPersistenceUtil());
		log.info("=============="+Persistence.getPersistenceUtil().isLoaded(UserTest.class));
		if(emf == null){
			log.info("\n====================================ERRRROOOORRR================");
			emf = Persistence.createEntityManagerFactory("testPersistence1");
		}


		final EntityManager entityManager = emf.createEntityManager();
		entityManagerStack.push(entityManager);
		return entityManager;
	}

	@Override
	public void unregister(EntityManager entityManager) {
		final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
	      if (entityManagerStack == null || entityManagerStack.isEmpty())
	         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

	      if (entityManagerStack.peek() != entityManager)
	         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");
	      entityManagerStack.pop();

	}

}
