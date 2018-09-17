package com.rishabh.dto;

/**
 * The Class TransactionDTO.
 */
public class TransactionDTO {
	
	/** The amount. */
	private String amount;
	
	/** The timestamp. */
	private String timestamp;
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "TransactionDTO [" + (amount != null ? "amount=" + amount + ", " : "") + "timestamp=" + timestamp + "]";
	}
	

}
