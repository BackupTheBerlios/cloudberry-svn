package org.berrycloud.core.tx;

public interface TransactionInfoStore {

	
	/**
	 * @return
	 */
	TransactionInfo get();

	/**
	 * @return
	 */
	TransactionInfo register();

	/**
	 * @param entityManager
	 */
	void unregister(TransactionInfo info);
	
}
