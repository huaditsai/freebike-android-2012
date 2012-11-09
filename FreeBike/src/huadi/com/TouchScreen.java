package huadi.com;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TouchScreen extends Overlay
{
	Context mContext;
	
	public TouchScreen(Context context)
	{
		mContext = context;
	}	
	
	GeoPoint destination;
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    {   
		destination = null;
        if (event.getAction() == 1) 
        {                
            final GeoPoint p = mapView.getProjection().fromPixels(
                (int) event.getX(),
                (int) event.getY());

            Geocoder geoCoder = new Geocoder( mContext, Locale.getDefault());
            
            try 
            {
                List<Address> addresses = geoCoder.getFromLocation(p.getLatitudeE6()/1E6, p.getLongitudeE6()/1E6, 1);

                final StringBuilder add = new StringBuilder();
                if (addresses.size() > 0) 
                {
                    for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); i++)
                    	add.append(addresses.get(0).getAddressLine(i) + "\n");
                }
                add.append(p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6() / 1E6);
                Toast.makeText(mContext, add, Toast.LENGTH_SHORT).show();
                
                new AlertDialog.Builder(mContext).setTitle("")
        		.setMessage("你要到這裡嗎?")
        		.setCancelable(false)
        		.setPositiveButton("確定", new DialogInterface.OnClickListener()
        				{
        					public void onClick(DialogInterface dialog, int which)
        					{
        						destination = p;
        					}
        				})
        		.setNegativeButton("取消", new DialogInterface.OnClickListener()
        				{
        					public void onClick(DialogInterface dialog, int which)
        					{
        						
        					}
        				})
        		.show();
            }
            catch (IOException e) 
            {                
                e.printStackTrace();
            }
            return true;
        }
        else                
            return false;
    }
	
	public GeoPoint GetDestination()
	{
		return destination;
	}
	
}
