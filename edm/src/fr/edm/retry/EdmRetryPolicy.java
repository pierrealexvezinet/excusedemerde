package fr.edm.retry;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.retry.DefaultRetryPolicy;
import com.octo.android.robospice.retry.RetryPolicy;


public class EdmRetryPolicy implements RetryPolicy{

	protected int retryCount = DefaultRetryPolicy.DEFAULT_RETRY_COUNT;
	private long delayBeforeRetry = DefaultRetryPolicy.DEFAULT_DELAY_BEFORE_RETRY;
	private float backOffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
	
	@Override
	public long getDelayBeforeRetry() {
		return 0;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void retry(SpiceException exception) {
	
	}

}
