package com.sshs.core.view.component;

public interface Component {
	String getId();

	String forEndTag();

	String forStartTag();

	void init();
}
