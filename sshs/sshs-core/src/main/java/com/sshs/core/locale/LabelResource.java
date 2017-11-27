package com.sshs.core.locale;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LabelResource {
	ResourceBundle pubResource = null;
	ResourceBundle privateResource = null;
	Locale locale = new Locale("zh", "CN");

	public LabelResource(Locale locale, ResourceBundle pubResource, ResourceBundle privateResource) {
		super();
		if (locale != null) {
			this.locale = locale;
		}
		this.pubResource = pubResource;
		this.privateResource = privateResource;
	}

	/**
	 * 获取label
	 * 
	 * @param name
	 * @return
	 */
	public String getLabel(String name) {
		String value = null;
		if (this.privateResource != null) {
			try {
				value = this.privateResource.getString(name);
			} catch (MissingResourceException e) {

			}
		}
		if (value == null && this.pubResource != null) {
			try {
				value = this.pubResource.getString(name);
			} catch (MissingResourceException e) {
			}
		}
		if (value == null) {
			value = name;
		}
		return value;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
