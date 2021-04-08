package com.sonia.matdaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class About_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        WebView webview2= findViewById(R.id.web);
        webview2.loadUrl("file:///android_asset/matdaan1.html");
        WebView webView1 = findViewById(R.id.web);
        webView1.loadUrl("file:///android_asset/Contact.html");



    }
    }
