package org.beatific.ddirori.attribute.refresh;

import org.beatific.ddirori.context.ApplicationContextUtils;

public abstract class RefreshObject {

	public void refresh() {
		ApplicationContextUtils.getApplicationContext().refresh(this);
	}
}
