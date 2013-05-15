package com.vizhen.androidoffice;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hdf.extractor.NewOleFile;

import com.vizhen.poihelper.SimpleWord2Html;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity
{
    public static final String TAG = "MainActivity";
    
    WebView webView;
    
    String docFile;
    
    String outhtmlFile;
    
    Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webView = (WebView)findViewById(R.id.webview);
        if (Environment.getExternalStorageDirectory() != null)
        {
            docFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "test.doc";
            
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "Cache", "test.html");
            if (!file.exists())
            {
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "Cache");
                if (!dir.exists())
                {
                    dir.mkdir();
                }
                try
                {
                    file.createNewFile();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            outhtmlFile = file.getAbsolutePath();
            new Thread(new ReadDocRunnable()).start();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    Runnable updateWebView = new Runnable()
    {
        
        @Override
        public void run()
        {
            if (webView != null)
            {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl("file:///" + outhtmlFile);
            }
            else 
            {
                Log.e(TAG, "web view is null!");
            }
            
        }
    };
    
    class ReadDocRunnable implements Runnable
    {
        
        @Override
        public void run()
        {
          
            Log.d(TAG, "docFile:" + docFile + "outhtmlFile:" + outhtmlFile);
            SimpleWord2Html simpleWord2Html =
                new SimpleWord2Html(docFile, outhtmlFile, Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                    + "Cache");
            if (simpleWord2Html.word2Html())
            {
                Log.d(TAG, "Update Web View");
                handler.post(updateWebView);
            }
            else
            {
                Log.e(TAG, "Read Doc Fail!");
                
            }
        }
        
    }
}
