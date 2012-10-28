package huadi.com;

import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity implements LocationListener
{
	boolean isOkStatu = false;
	private SensorManager sensorManager;
    private RotateView rotateView;    
    private boolean isRotateMap = false; 
    
	private MapView mapView;  //宣告map物件
	private MapController controller;
	
	private LocationManager locationMgr;	
	private MyLocationOverlay myLayer;
	
	private MapOverlay mapOverlay;
	Drawable pin; //地圖上的釘點圖
	
	protected static final int Bike_Timer = Menu.FIRST;//Menu
	protected static final int Show_BikeStation = Menu.FIRST+1;
	
	Button btnRotate;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        findViews();
    }     	
    
    private void initMap()
    {
    	if (!locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER))
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
			isOkStatu = true;
			setupMap();
			drawPin();
			updateStat();
		}
    }

	private void findViews() 
    {
    	sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    	
        rotateView = new RotateView(this);
        mapView = new MapView(this, "0XKrp4dJ2ko56MQU06zceVRaushjMvFfsgmTsHA"); // API KEY
        rotateView.addView(mapView);
        
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(rotateView); 
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        
    	//mapView = (MapView) findViewById(R.id.mapView);
		controller = mapView.getController(); //設定controller物件至map
		
		mapView.setTraffic(false);//一般 mapView.setSatellite(true)//衛星 mapView.setStreetView(true)//街景
		mapView.setBuiltInZoomControls(true);//縮放的按鈕
		controller.setZoom(17);//全球1 ~ 街景21
		
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, MainActivity.this);
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
		myLayer.runOnFirstFix(
				new Runnable()//位置資訊更新時
				{
		   			public void run() //產生一個執行緒執行
		   			{
		   			    controller.animateTo(myLayer.getMyLocation());//將地點置中
		   			}
		   		});
		overlays.add(myLayer); //將locationLayer加入(add)overlays，才能顯示地圖
		
		mapView.setClickable(true);
        mapView.setEnabled(true);
        
        
        btnRotate = (Button) findViewById(R.id.btnRotate);        
        btnRotate.setOnClickListener(new Button.OnClickListener() 
        {
            @SuppressWarnings("deprecation")
			@Override
            public void onClick(View view) 
            {
            	if( isRotateMap ) // 關閉電子羅盤
				{						
					sensorManager.unregisterListener(rotateView);
					isRotateMap = false;
					btnRotate.setText(R.string.rotate_start);
				} 
				else // 啟動電子羅盤
				{						
					sensorManager.registerListener(rotateView,SensorManager.SENSOR_ORIENTATION, SensorManager.SENSOR_DELAY_UI);
					isRotateMap = true;
					btnRotate.setText(R.string.rotate_stop);
				}
            }
        });
        
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
		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, MainActivity.this);//模擬器會出錯
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);//讓系統定時檢查位置
		
		try
		{
			GeoPoint minPoint = MapOverlay.minDistience(myLayer.getMyLocation());
				new GoogleDirection(myLayer, mapView).execute(
						myLayer.getMyLocation().getLatitudeE6()/ 1E6 + "," + myLayer.getMyLocation().getLongitudeE6()/ 1E6, 
						minPoint.getLatitudeE6()/ 1E6 + "," + minPoint.getLongitudeE6()/ 1E6);
		}
		catch (Exception e)
		{
			Log.v("1",""+e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
   	protected void onResume() 
	{
   		super.onResume();
   		
   		if(isOkStatu)
   		{
	   		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, MainActivity.this);//模擬器會出錯
			locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);//讓系統定時檢查位置
			
			if( isRotateMap )// 啟動電子羅盤
			{			
				sensorManager.registerListener(rotateView,SensorManager.SENSOR_ORIENTATION, SensorManager.SENSOR_DELAY_UI);
			}
			
	   		myLayer.enableMyLocation();//啟動更新
   		}
   		else 
   		{
			initMap();
		}
   	}   	

	@SuppressWarnings("deprecation")
	@Override
   	protected void onPause() 
   	{
   		super.onPause();
   		if(isOkStatu)
   		{
   			locationMgr.removeUpdates(MainActivity.this);
   			myLayer.disableMyLocation();//關閉更新
   			sensorManager.unregisterListener(rotateView);
   		}
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
