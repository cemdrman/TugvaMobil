package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.tugva.basaksehir.tugvabasaksehir.R;

public class HaberGonderActivity extends Activity {
    private EditText txtKonu;
    private EditText txtLink;
    private EditText txtMesaj;
    private Button btnGonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_gonder);
        txtKonu = (EditText)findViewById(R.id.txtKonu);
        txtLink = (EditText)findViewById(R.id.txtLink);
        txtMesaj = (EditText)findViewById(R.id.txtMesaj);
        btnGonder = (Button)findViewById(R.id.btnGonder);
        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailGonder();
            }
        });
    }

    private void mailGonder(){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "cemdrman@gmail.com");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Test Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From My App");
       // emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/Myimage.jpeg"));
        startActivity(emailIntent);
        finish();
    }

}
