package huadi.com;

import java.util.List;

import android.app.Service;
import android.content.Context;
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
	Location location;
	
	List<Overlay> overlays;// = mapView.getOverlays();//定位點
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
    	setupMap();
    	
    	LocationManager status = (LocationManager)(this.getSystemService(Context.LOCATION_SERVICE));
		if(status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) 
		     updateStat();
		else 	
			startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));//開啟一個Activity，將使用者帶到定位設定頁面
    }     	

    private void findViews() 
    {
    	mapView = (MapView) findViewById(R.id.mapView);
		controller = mapView.getController(); //設定controller物件至map
		
		mapView.setTraffic(true);//一般 mapView.setSatellite(true)//衛星 mapView.setStreetView(true)//街景
		mapView.setBuiltInZoomControls(true);//縮放的按鈕
		controller.setZoom(17);//全球1 ~ 街景21
		
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, MainActivity.this);
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
    }

	private void setupMap()
	{
		//GeoPoint ntue = new GeoPoint( (int)(25.023389 * 1000000), (int)(121.545208 * 1000000) );
		
		//controller.animateTo(ntue);
		
		overlays = mapView.getOverlays();//定位點
		myLayer = new MyLocationOverlay(this, mapView);
		myLayer.enableCompass();//顯示羅盤
		myLayer.enableMyLocation();//啟動更新
		myLayer.runOnFirstFix(new Runnable()//位置資訊更新時
								{
						   			public void run() //產生一個執行緒執行
						   			{
//						   			      mapView.setTraffic(true);//一般 
//						   			      mapView.setBuiltInZoomControls(true);//縮放的按鈕
//						   			      controller.setZoom(15); //全球1 ~ 街景21
						   			      controller.animateTo(myLayer.getMyLocation());//將地點置中
						   			}
						   		});
		overlays.add(myLayer); //將locationLayer加入(add)overlays，才能顯示地圖
		
		pin = getResources().getDrawable(R.drawable.pin);//getDrawable(android.R.drawable.checkbox_on_background);
		pin.setBounds(-pin.getMinimumWidth()/2, -pin.getMinimumHeight(), 0, 0);//以圖片中下為基準
		mapOverlay = new MapOverlay(pin,this);
		overlays.add(mapOverlay);		
	}
	
	private void updateStat()
	{
		locationMgr = (LocationManager) getSystemService(LOCATION_SERVICE);//取得系統提供的定位服務
		location = locationMgr.getLastKnownLocation("gps");//使用GPS來定位
		
		if (location != null) 
		{
			new GoogleDirection(myLayer, mapView).execute(location.getLatitude() + "," + location.getLongitude(), "捷運市政府站");
		} 
		else
		{
			Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
   	protected void onResume() 
	{
   		super.onResume();
   		locationMgr.requestLocationUpdates("gps", 1000, 1, this);//讓系統定時檢查位置
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
		menu.add(0, Show_BikeStation, 0, "半徑5Km內租賃站");
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
				if (location != null)
				{
					controller.setZoom(16); //全球1 ~ 街景21
					controller.animateTo(myLayer.getMyLocation());//將地點置中
				}
				else
				{
					Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override //當地點改變
	public void onLocationChanged(Location location)
	{
		overlays.clear();
		mapOverlay = new MapOverlay(pin,this);
		overlays.add(mapOverlay);
		updateStat();
		//Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
	}
	@Override //當GPS或網路關閉
	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub		
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
