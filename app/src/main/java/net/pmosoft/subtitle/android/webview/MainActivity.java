package net.pmosoft.subtitle.android.webview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webView = (WebView) findViewById(R.id.webview);
        //webView.getSettings().setDomStorageEnabled(true);
        //webView.setWebViewClient(new WebViewClient());
        //setContentView(webView);
        //webView.setWebChromeClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        //webView.loadUrl("http://pmosoft.net:8085");
        //webView.loadUrl("http://zeany.net/14");
        //webView.loadUrl("https://angularjs.org/");
        //webView.loadUrl("https://www.naver.com/");
        //webView.loadUrl("http://www.naver.com/");
        //WebView myWebView = new WebView(activityContext);
        //setContentView(myWebView);
        initWebView(webView);
    }

    private void initWebView(View view) {
        webView=findViewById(R.id.webview);
        loadWebViewDatafinal(webView, "http://pmosoft.net:8085");
    }

    private void loadWebViewDatafinal(WebView wv, String url) {
        WebSettings ws=wv.getSettings();

        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            try {
                Log.e("WEB_VIEW_JS", "Enabling HTML5-Features");
                Method m1=WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                m1.invoke(ws, Boolean.TRUE);

                Method m2=WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
                m2.invoke(ws, Boolean.TRUE);

                Method m4=WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
                m4.invoke(ws, 1024 * 1024 * 8);


                Method m6=WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
                m6.invoke(ws, Boolean.TRUE);

                Log.e("WEB_VIEW_JS", "Enabled HTML5-Features");
            } catch (NoSuchMethodException e) {
                Log.e("WEB_VIEW_JS", "Reflection fail", e);
            } catch (InvocationTargetException e) {
                Log.e("WEB_VIEW_JS", "Reflection fail", e);
            } catch (IllegalAccessException e) {
                Log.e("WEB_VIEW_JS", "Reflection fail", e);
            }
        }

        wv.loadUrl(url);

    }

    //뒤로가기 기능
    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
