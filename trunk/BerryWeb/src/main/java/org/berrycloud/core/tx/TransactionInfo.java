package org.berrycloud.core.tx;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionInfo {

	private boolean allowBegin = true;
	private boolean allowCommit = true;
	
	private  Transaction transaction;
	private final Session session;

	public TransactionInfo(Session session) {
		super();
		this.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Session getSession() {
		return session;
	}
	
	public TransactionInfo noBeginNoCommit() {
		TransactionInfo copy = new TransactionInfo(session);
		copy.transaction = transaction;
		allowBegin = false;
		allowCommit = false;
		return copy;
	}

	public boolean isAllowBegin() {
		return allowBegin;
	}

	public boolean isAllowCommit() {
		return allowCommit;
	}

	public void begin() {
		transaction = session.beginTransaction();
	}
	
}
