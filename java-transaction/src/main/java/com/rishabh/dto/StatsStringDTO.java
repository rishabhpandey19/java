package com.rishabh.dto;

/**
 * The Class StatsDTO.
 */
public class StatsStringDTO {

	/** The count. */
	private long count;

	/** The max. */
	private String max;

	/** The avg. */
	private String avg;

	/** The min. */
	private String min;

	/** The sum. */
	private String sum;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "StatsDTO [count=" + count + ", max=" + max + ", avg=" + avg + ", min=" + min + ", sum=" + sum + "]";
	}

}
