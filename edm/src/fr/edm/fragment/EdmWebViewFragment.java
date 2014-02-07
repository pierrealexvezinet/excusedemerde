package fr.edm.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.activity.edm.R;


/**
 * @author pvezinet
 *
 */

@SuppressLint("ValidFragment")
public class EdmWebViewFragment extends Fragment {
	private String mUrl = null;
	private String mHtml = null;
	private String mTitle = null;
	private boolean mHasLocalAssert = false;
	private WebView mWebView;
	private TextView mTextViewTitle;
	private ProgressBar mProgressBar;
	
	public EdmWebViewFragment() {
	}
	
	public EdmWebViewFragment(String url, String title) {
		this.mUrl = url;
		this.mTitle = title;
	}
	
	public void setHtmlContent(String html){
		this.mHtml = html;
	}
	
	public void setHtmlContent(String html, boolean includeLocalAssert){
		this.mHtml = html;
		this.mHasLocalAssert = includeLocalAssert;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_webview, null);
		mWebView = (WebView) view.findViewById(R.id.fragment_webView);
		mTextViewTitle = (TextView) view.findViewById(R.id.fragment_webView_title);
		mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_webView_progressBar);
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (mTitle == null)
			mTextViewTitle.setVisibility(View.GONE);
		else
			mTextViewTitle.setText(mTitle.toUpperCase());
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);
		setupWebViewClient();
		if(mUrl != null){
			webSettings.setUseWideViewPort(true);
			mWebView.loadUrl(mUrl);
		} else if (mHasLocalAssert && mHtml != null) {
			webSettings.setUseWideViewPort(false);
			mWebView.loadDataWithBaseURL("file:///android_asset/", mHtml, "text/html", "utf-8", null);
		} else if(mHtml != null){
			webSettings.setUseWideViewPort(false);
			mWebView.loadDataWithBaseURL(null, mHtml, "text/html", "utf-8", null);
		}
	}
	
	private void setupWebViewClient() {
		mWebView.setWebViewClient(new WebViewClient() {
	        private int running = 0; // Could be public if you want a timer to check.

	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
	            running++;
	            mWebView.loadUrl(urlNewString);
	            mProgressBar.setVisibility(View.VISIBLE);
	            return true;
	        }

	        @Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) {
	            running = Math.max(running, 1); // First request move it to 1.
	        }

	        @Override
	        public void onPageFinished(WebView view, String url) {
	            if(--running == 0) { // just "running--;" if you add a timer.
		            mProgressBar.setVisibility(View.GONE);
	            }
	        }
	    });
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
}
