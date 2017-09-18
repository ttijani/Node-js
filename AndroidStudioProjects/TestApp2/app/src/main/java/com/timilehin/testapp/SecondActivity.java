package com.timilehin.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class
SecondActivity extends AppCompatActivity
{

    WebView webView;

    @Override
    protected void
    onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://www.google.com");
        webView.getSettings().getJavaScriptEnabled();
        // Lets the page be loaded in the app
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void
    onBackPressed() {

        if (webView.canGoBack())
            webView.goBack();
        else
            finish();

    }

}
