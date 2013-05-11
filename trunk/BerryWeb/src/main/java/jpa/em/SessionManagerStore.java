package jpa.em;

import org.hibernate.Session;

public interface SessionManagerStore {
	/**
	 * @return
	 */
	Session get();

	/**
	 * @return
	 */
	Session register();

	/**
	 * @param entityManager
	 */
	void unregister(Session session);
}
