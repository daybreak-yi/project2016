package com.shoko01.input.dto;

import java.math.BigDecimal;

public class VNumeric extends BaseDTO {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** NUMERIC */
	private BigDecimal numeric;

	/** NUMERIC */
	public BigDecimal getNumeric() {
		return numeric;
	}

	/** NUMERIC */
	public void setNumeric(BigDecimal numeric) {
		this.numeric = numeric;
	}
}
