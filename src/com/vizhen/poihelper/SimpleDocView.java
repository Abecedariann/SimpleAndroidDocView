package com.vizhen.poihelper;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class SimpleDocView extends WebView
{
    private Context mContext;
    private String docPath;
    private String outDir;
    
    public SimpleDocView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public SimpleDocView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SimpleDocView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    private boolean doc2Html()
    {
        File htmlFile = new File(outDir);
        
        if(!htmlFile.exists())
        {
            try
            {
                htmlFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        
        
        
        return false;
    }
    
}
