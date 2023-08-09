package com.app.abcdapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.abcdapp.R;

import im.delight.android.webview.AdvancedWebView;


public class ExploreFragment extends Fragment implements AdvancedWebView.Listener {
    private AdvancedWebView mWebView;




    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_explore, container, false);

        mWebView = (AdvancedWebView) root.findViewById(R.id.webview);
        mWebView.setListener(getActivity(), this);
        mWebView.setMixedContentAllowed(true);
        mWebView.setDesktopMode(true);

        // Set the user-agent to mimic a desktop browser
        WebSettings webSettings = mWebView.getSettings();
        String desktopUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webSettings.setUserAgentString(desktopUserAgent);

        // Other settings for your WebView, if necessary
        webSettings.setJavaScriptEnabled(true);

        // Load the URL
        mWebView.loadUrl("https://slveenterprises.org/shop");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("phonepe")){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );

                }

                return true;
            }

        });




        return root;

    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }



    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }



    @Override
    public void onPageStarted(String url, Bitmap favicon) {
//        if (url.startsWith("intent://")){
//// Create the intent with the ACTION_VIEW action and the deep link URL
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//
//            // Verify if there's an app available to handle the deep link
//            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                // If an app is available, start the activity to handle the deep link
//                startActivity(intent);
//            } else {
//                // If no app is available to handle the deep link, you may handle this scenario accordingly
//            }
//        }
    }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }

}