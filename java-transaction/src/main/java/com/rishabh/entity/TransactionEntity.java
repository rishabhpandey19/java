package com.rishabh.entity;

import java.math.BigDecimal;

/**
 * The Class TransactionEntity.
 */
public class TransactionEntity {
	
	/** The amount. */
	private BigDecimal amount;
	
	/** The timestamp. */
	private long timestamp;

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "TransactionEntity [" + (amount != null ? "amount=" + amount + ", " : "") + "timestamp=" + timestamp
				+ "]";
	}

}
