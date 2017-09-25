package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import org.tugva.basaksehir.tugvabasaksehir.R;

/**
 * Created by cdirman on 29.6.2016.
 */
public class UyelikActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyelik);
        WebView webViewUyelik = (WebView)findViewById(R.id.webViewuyelik);
        webViewUyelik.getSettings().setJavaScriptEnabled(true);
        webViewUyelik.loadUrl("http://www.123contactform.com/form-1954042/TUGVA-Baakehir-Uyelik-Formu");
    }
}
