package mnilg.com.countrypicker.model;

import mnilg.com.countrypicker.utills.StringUtils;

public class Country {
	private String code;
	private String name;
	private String dialCode;
	private String sortLetter;

	public Country() {}

	public Country(String code, String name, String dialCode) {
		setCode(code);
		setName(name);
		setDialCode(dialCode);
		this.sortLetter = StringUtils.getSortLetter(name);
	}

	public String getDialCode() {
		return dialCode;
	}

	private void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSortLetter() {
		return sortLetter;
	}

	public void setSortLetter(String sortLetter) {
		this.sortLetter = sortLetter;
	}
}