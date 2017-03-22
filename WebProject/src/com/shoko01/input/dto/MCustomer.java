package com.shoko01.input.dto;

public class MCustomer extends BaseDTO {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	
	/** NAME */
	private String name;
	
	/** AGE */
	private Integer age;
	
	/** SEX */
	private Integer sex;
	
	/** ZIP_CODE */
	private String zipCode;

	/** ID */
	public Integer getId() {
		return id;
	}

	/** ID */
	public void setId(Integer id) {
		this.id = id;
	}

	/** NAME */
	public String getName() {
		return name;
	}

	/** NAME */
	public void setName(String name) {
		this.name = name;
	}

	/** AGE */
	public Integer getAge() {
		return age;
	}

	/** AGE */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** SEX */
	public Integer getSex() {
		return sex;
	}

	/** SEX */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** ZIP_CODE */
	public String getZipCode() {
		return zipCode;
	}

	/** ZIP_CODE */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
