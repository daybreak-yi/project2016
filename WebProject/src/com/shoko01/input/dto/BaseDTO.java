package com.shoko01.input.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 実行SQL */
	private String sql;

	/** 実行時間(ナノ秒) */
	private long nanoTime;

	/** レコード総件数 */
	private long totalRecords;

	/** 現在レコード */
	private long rownum;

	/** 実行SQL */
	public String getSql() {
		return this.sql;
	}

	/** 実行SQL */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/** 実行時間(ナノ秒) */
	public long getNanoTime() {
		return this.nanoTime;
	}

	/** 実行時間(ナノ秒) */
	public void setNanoTime(long nanoTime) {
		this.nanoTime = nanoTime;
	}

	/** レコード総件数 */
	public long getTotalRecords() {
		return this.totalRecords;
	}

	/** レコード総件数 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/** 現在レコード */
	public long getRownum() {
		return this.rownum;
	}

	/** 現在レコード */
	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

}
