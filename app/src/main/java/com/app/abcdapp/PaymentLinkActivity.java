package com.app.abcdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class PaymentLinkActivity extends AppCompatActivity implements AdvancedWebView.Listener{
    private AdvancedWebView mWebView;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_link);
        activity = PaymentLinkActivity.this;

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(activity, this);
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

                Log.d("REDIRECT_LINK",url);
                if (url.startsWith("phonepe") || url.startsWith("tez") || url.startsWith("paytmmp")){
                    String packagename = "";
                    if (url.startsWith("phonepe")){
                        packagename = "com.phonepe.app";

                    }else if (url.startsWith("gpay")){
                        packagename = "com.google.android.apps.nbu.paisa.user";

                    }else {
                        packagename = "net.one97.paytm";
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);




                }

                return true;
            }

        });








    }
    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {



        // Assuming you have a WebView instance named 'webView'
        mWebView.evaluateJavascript("(function() { " +
                        "var jsonElement = document.querySelector('script[type=\"application/ld+json\"]');" +
                        "if (jsonElement) { " +
                        "    var jsonData = JSON.parse(jsonElement.textContent); " +
                        "    return jsonData.description + '|' + jsonData.name; " +
                        "} else { " +
                        "    return null; " +
                        "}})();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        if (value != null && !value.equals("null")) {
                            // 'value' contains the extracted data in the format "description|name"
                            String[] data = value.split("\\|");
                            String description = data[0];
                            String name = data[1];
                            // Now you can use 'description' and 'name' as per your requirements
                            // For example, display them in TextViews
//                            textViewDescription.setText(description);
//                            textViewName.setText(name);

                            Toast.makeText(activity, "description: "+description, Toast.LENGTH_SHORT).show();
                            Toast.makeText(activity, ""+name, Toast.LENGTH_SHORT).show();

                            Log.d("WebView", "description: " + description);


                        } else {
                            Log.d("WebView", "JSON data not found.");
                        }
                    }
                });


    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}