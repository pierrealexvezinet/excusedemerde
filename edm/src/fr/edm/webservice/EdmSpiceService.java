package fr.edm.webservice;

import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;

public class EdmSpiceService extends Jackson2SpringAndroidSpiceService {

	protected static final int DEFAULT_THREAD_COUNT = 4;

	@Override
	public int getThreadCount() {
		return DEFAULT_THREAD_COUNT;
	}

}
