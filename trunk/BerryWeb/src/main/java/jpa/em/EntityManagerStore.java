package jpa.em;

import javax.persistence.EntityManager;

public interface EntityManagerStore {
	/**
	 * @return
	 */
	EntityManager get();

	/**
	 * @return
	 */
	EntityManager register();

	/**
	 * @param entityManager
	 */
	void unregister(EntityManager entityManager);
}
