package com.rishabh.dto;

import java.math.BigDecimal;

/**
 * The Class StatsDTO.
 */
public class StatsDTO {
	
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



	@Override
	public String toString() {
		return "StatsDTO [count=" + count + ", max=" + max + ", avg=" + avg + ", min=" + min + ", sum=" + sum + "]";
	}

}
