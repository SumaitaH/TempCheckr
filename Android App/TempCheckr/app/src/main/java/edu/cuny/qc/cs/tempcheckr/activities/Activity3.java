package edu.cuny.qc.cs.tempcheckr.activities;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import edu.cuny.qc.cs.tempcheckr.R;

public class Activity3 extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = (WebView) findViewById(R.id.webViewer);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://10.0.2.2:8080/Servlet_SumaitaH");
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
