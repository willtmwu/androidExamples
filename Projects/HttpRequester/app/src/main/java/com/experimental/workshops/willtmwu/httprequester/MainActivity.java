package com.experimental.workshops.willtmwu.httprequester;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText editTextWebsite;
    private TextView responseView;
    private Button refreshButton;
    private WebView webResponseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWebsite = (EditText) findViewById(R.id.editText);
        responseView = (TextView) findViewById(R.id.textView);
        refreshButton = (Button) findViewById(R.id.button);
        webResponseView = (WebView) findViewById(R.id.webViewDisplay);


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make an web request, set it to the textView

                // By Async
                WebRequestAsync asyncCaller = new WebRequestAsync(responseView, webResponseView);
                asyncCaller.execute(new String[]{editTextWebsite.getText().toString()});

                // By Retrofit



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
