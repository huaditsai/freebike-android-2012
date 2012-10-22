package huadi.com;

import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.googlemap.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity implements LocationListener
{
	private MapView mapView;  //宣告map物件
	private MapController controller;
	
	private LocationManager locationMgr;
	
	private MyLocationOverlay myLayer;
	
	private MapOverlay mapOverlay;
	Drawable pin; //地圖上的釘點圖
	
	protected static final int Bike_Timer = Menu.FIRST;//Menu
	protected static final int Show_BikeStation = Menu.FIRST+1;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        
        findViews();    	
    	
    	LocationManager status = (LocationManager)(this.getSystemService(Context.LOCATION_SERVICE));		
		if (!status.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			new AlertDialog.Builder(MainActivity.this).setTitle("地圖工具")
			.setMessage("您尚未開啟定位服務，要前往設定頁面啟動定位服務嗎?")
			.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));//開啟一個Activity，將使用者帶到定位設定頁面
						}
					})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							Toast.makeText(MainActivity.this, "未開啟定位服務，無法使用本工具!!", Toast.LENGTH_SHORT).show();
						}
					})
			.show();
		}
		else
		{
			setupMap();
			drawPin();
			updateStat();
		}
    }     	

    private void findViews() 
    {
    	mapView = (MapView) findViewById(R.id.mapView);
		controller = mapView.getController(); //設定controller物件至map
		
		mapView.setTraffic(true);//一般 mapView.setSatellite(true)//衛星 mapView.setStreetView(true)//街景
		//mapView.setBuiltInZoomControls(true);//縮放的按鈕
		controller.setZoom(17);//全球1 ~ 街景21
		
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, MainActivity.this); //模擬器會出錯
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);
    }

	private void setupMap()
	{
		//GeoPoint ntue = new GeoPoint( (int)(25.023389 * 1000000), (int)(121.545208 * 1000000) );
		//controller.animateTo(ntue);
		
		List<Overlay> overlays = mapView.getOverlays();//定位點
		myLayer = new MyLocationOverlay(this, mapView);
		myLayer.enableCompass();//顯示羅盤
		myLayer.enableMyLocation();//啟動更新
		myLayer.runOnFirstFix(new Runnable()//位置資訊更新時
								{
						   			public void run() //產生一個執行緒執行
						   			{
//						   				GeoPoint minPoint = MapOverlay.minDistience(myLayer.getMyLocation());
//						   				new GoogleDirection(myLayer, mapView).execute(myLayer.getMyLocation().getLatitudeE6()/ 1E6 + "," + myLayer.getMyLocation().getLongitudeE6()/ 1E6, 
//						   						minPoint.getLatitudeE6()/ 1E6 + "," + minPoint.getLongitudeE6()/ 1E6);
						   			    controller.animateTo(myLayer.getMyLocation());//將地點置中
						   			}
						   		});
		overlays.add(myLayer); //將locationLayer加入(add)overlays，才能顯示地圖
	}
	private void drawPin()
	{
		List<Overlay> overlays = mapView.getOverlays();//定位點
		
		pin = getResources().getDrawable(R.drawable.pin);//地圖上的釘點圖
		pin.setBounds(-pin.getMinimumWidth()/2, -pin.getMinimumHeight(), 0, 0);//以圖片中下為基準
		mapOverlay = new MapOverlay(pin,this);

		overlays.add(mapOverlay);
	}
	
	private void updateStat()
	{
		locationMgr = (LocationManager) getSystemService(LOCATION_SERVICE);//取得系統提供的定位服務
		Location location = locationMgr.getLastKnownLocation("gps");//使用GPS來定位
		
		try
		{
			if (location != null) 
			{
				GeoPoint minPoint = MapOverlay.minDistience(myLayer.getMyLocation());
					new GoogleDirection(myLayer, mapView).execute(location.getLatitude() + "," + location.getLongitude(), 
							minPoint.getLatitudeE6()/ 1E6 + "," + minPoint.getLongitudeE6()/ 1E6);
				//new GoogleDirection(myLayer, mapView).execute(location.getLatitude() + "," + location.getLongitude(), "");
			} 
			else
			{
				Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show();
				locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);
			}
		}
		catch (Exception e)
		{
			Log.v("1",""+e);
		}
	}
	
	@Override
   	protected void onResume() 
	{
   		super.onResume();
   		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, MainActivity.this);//模擬器會出錯
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);//讓系統定時檢查位置
   		myLayer.enableMyLocation();//啟動更新
   	}   	
   	@Override
   	protected void onPause() 
   	{
   		super.onPause();
   		myLayer.disableMyLocation();//關閉更新
   	}

	@Override
	protected boolean isRouteDisplayed()//告知任何移動資料
	{
		return false;
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		menu.add(0, Bike_Timer, 0, "開始計時");
		menu.add(0, Show_BikeStation, 0, "附近租賃站");
        return super.onCreateOptionsMenu(menu);
    }
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		
		switch(item.getItemId())
		{
			case Bike_Timer:
				new CountDownTimer(25*60*1000, 1000)//計時25分鐘
				{
					Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);//取得震動服務
					public void onTick(long millisUntilFinished) 
					{
						//mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
						Log.v("timer","remaining:"+ millisUntilFinished / 1000 / 60 +":"+(millisUntilFinished / 1000) % 60);
					}
					public void onFinish() 
					{
						myVibrator.vibrate(3000);
						Toast.makeText(MainActivity.this, "remaining 5 min", Toast.LENGTH_LONG).show();
						//mTextField.setText("done!");
					}
				}.start();
			case Show_BikeStation:
					controller.setZoom(17); //全球1 ~ 街景21
					controller.animateTo(myLayer.getMyLocation());//將地點置中
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override //當地點改變
	public void onLocationChanged(Location location)
	{
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		drawPin();
		updateStat();
		//Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
	}
	@Override //當GPS或網路關閉
	public void onProviderDisabled(String provider)
	{
		LocationManager status = (LocationManager)(this.getSystemService(Context.LOCATION_SERVICE));
		if (!status.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			new AlertDialog.Builder(MainActivity.this).setTitle("地圖工具")
			.setMessage("您尚未開啟定位服務，要前往設定頁面啟動定位服務嗎？")
			.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));//開啟一個Activity，將使用者帶到定位設定頁面
						}
					})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							Toast.makeText(MainActivity.this, "未開啟定位服務，無法使用本工具!!", Toast.LENGTH_SHORT).show();
						}
					})
			.show();
		}		
	}
	@Override //當GPS或網路開啟
	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub		
	}
	@Override //當GPS或網路狀態改變
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub		
	}
}
