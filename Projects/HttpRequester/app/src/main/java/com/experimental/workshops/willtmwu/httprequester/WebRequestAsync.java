package com.experimental.workshops.willtmwu.httprequester;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by William on 24/08/2015.
 */
public class WebRequestAsync extends AsyncTask<String, Void, String>{

    private static final String LOG_TAG = WebRequestAsync.class.getSimpleName();

    private TextView responseView;
    private WebView webResponseView;


    /* NOTES
    AsyncTask <TypeOfVarArgParams , ProgressValue , ResultValue> .

    The AsyncTask does not handle configuration changes automatically, i.e. if the activity is recreated, the programmer has to handle that in his coding.

    A common solution to this is to declare the AsyncTask in a retained headless fragment.
     */

    public WebRequestAsync (TextView tv) {
        this(tv, null);
    }
    public WebRequestAsync (TextView tv, WebView wb) {
        this.responseView = tv;
        this.webResponseView = wb;
    }

    // Get the website, in all the html glory
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = "ERROR!!!";

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();
            StringBuffer sb = new StringBuffer();

            if (in == null) {
                return "";
            }
            reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }

            return sb.toString();

        } catch (MalformedURLException urlException){
            response = "MALFORMED URL";
            Log.e(LOG_TAG, urlException.toString());
        } catch (IOException ioException){
            response = "IO Exception";
            Log.e(LOG_TAG, ioException.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e){
                    Log.e(LOG_TAG, e.toString());
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        responseView.setText(s);
        if (webResponseView != null) {
            webResponseView.loadData(s, "text/html" , "utf-8");
        }
    }


}
