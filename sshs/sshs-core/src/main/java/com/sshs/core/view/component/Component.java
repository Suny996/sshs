package com.sshs.core.view.component;

import com.sshs.core.locale.LabelResource;

public interface Component {
	String getId();

	String forEndTag();

	String forStartTag();

	void init(LabelResource labelResource);
}
