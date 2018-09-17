package com.rishabh.entity;

import java.math.BigDecimal;

/**
 * The Class StatsEntity.
 */
public class StatsEntity {

	/** The count. */
	private long count;
	
	/** The max. */
	private BigDecimal max;
	
	/** The avg. */
	private BigDecimal avg;
	
	/** The min. */
	private BigDecimal min;
	
	/** The sum. */
	private BigDecimal sum;
	
	/** The time now. */
	private long timeNow;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public long getTimeNow() {
		return timeNow;
	}

	public void setTimeNow(long timeNow) {
		this.timeNow = timeNow;
	}

	@Override
	public String toString() {
		return "StatsEntity [count=" + count + ", " + (max != null ? "max=" + max + ", " : "")
				+ (avg != null ? "avg=" + avg + ", " : "") + (min != null ? "min=" + min + ", " : "")
				+ (sum != null ? "sum=" + sum + ", " : "") + "timeNow=" + timeNow + "]";
	}
}
