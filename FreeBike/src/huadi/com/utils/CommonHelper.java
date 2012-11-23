package huadi.com.utils;

import android.app.ProgressDialog;
import android.content.Context;


public class CommonHelper  //搜尋時的進度圈
{
	private static ProgressDialog mProgress;
	
	// show the progress bar.
	public static void showProgress(Context context, CharSequence message)
	{
		mProgress = new ProgressDialog(context);
		mProgress.setMessage(message);
		mProgress.setIndeterminate(false);
		mProgress.setCancelable(false);
		mProgress.show();
	}
	
	public static void closeProgress()
    {
    	try
    	{
	    	if( mProgress != null )
	    	{
	    		mProgress.dismiss();
	    		mProgress = null;
	    	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }   
}
