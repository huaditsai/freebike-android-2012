package huadi.com;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapOverlay extends ItemizedOverlay<OverlayItem>
{	
	//宣告items列表，負責儲存圖標列表
	private List<OverlayItem> items = new ArrayList<OverlayItem>();
	Context mcontext;//為了用MainActivity叫Toast等
	
	//GeoPoint  = new GeoPoint( (int)( * 1000000), (int)( * 1000000) );
	GeoPoint Taipei_City_Hall_Station = new GeoPoint( (int)(25.0408388889 * 1000000), (int)(121.567894444 * 1000000) );
	GeoPoint Sun_Yet_Sen_Memorial_Hall_Station = new GeoPoint( (int)(25.0410833333 * 1000000), (int)(121.5578 * 1000000) );
	GeoPoint Taipei_City_Government  = new GeoPoint( (int)( 25.0377972222 * 1000000), (int)(121.565169444 * 1000000) );
	GeoPoint Taipei_City_Hall_Plaza = new GeoPoint( (int)(25.0360361111 * 1000000), (int)(121.562325 * 1000000) );
	GeoPoint Xingya_Jr_High_School = new GeoPoint( (int)(25.0365638889 * 1000000), (int)(121.568663889 * 1000000) );
	
	GeoPoint New_York_New_York_Greenway = new GeoPoint( (int)(25.0347361111 * 1000000), (int)(121.565658333 * 1000000) );
	GeoPoint Xinyi_Square = new GeoPoint( (int)(25.0330388889 * 1000000), (int)(121.565619444 * 1000000) );
	GeoPoint TWTC_Exhibition_Hall = new GeoPoint( (int)(25.0352138889 * 1000000), (int)(121.563688889 * 1000000) );
	GeoPoint Exit_C_of_the_World_Trade_Center = new GeoPoint( (int)(25.033943 * 1000000), (int)(121.562959 * 1000000) );
	GeoPoint Taipei_City_Disaster_Response_Center = new GeoPoint( (int)(25.0286611111 * 1000000), (int)(121.566116667 * 1000000) );
	
	GeoPoint San_Zhang_Li = new GeoPoint( (int)(25.0335472222 * 1000000), (int)(121.557594444 * 1000000) );
	
	public MapOverlay(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		mcontext = context;
		//items.add(new OverlayItem( , null, null));
		items.add(new OverlayItem(Taipei_City_Hall_Station, null, "捷運市政府站"));
		items.add(new OverlayItem(Sun_Yet_Sen_Memorial_Hall_Station, null, "捷運國父紀念館站"));
		items.add(new OverlayItem(Taipei_City_Government , null, "臺北市政府"));
		items.add(new OverlayItem(Taipei_City_Hall_Plaza , null,"市民廣場"));
		items.add(new OverlayItem(Xingya_Jr_High_School , null, "興雅國中"));
		
		items.add(new OverlayItem(New_York_New_York_Greenway , null, "紐約紐約綠園道"));
		items.add(new OverlayItem(Xinyi_Square , null, "信義廣場"));
		items.add(new OverlayItem(TWTC_Exhibition_Hall , null, "世貿三館"));
		items.add(new OverlayItem(Exit_C_of_the_World_Trade_Center , null, "世貿一館C出口"));
		items.add(new OverlayItem(Taipei_City_Disaster_Response_Center , null, "台北市災害應變中心"));
		
		items.add(new OverlayItem(San_Zhang_Li , null, "三張犁"));
		
		populate();//準備ItemizedOverly建構後，所需的處理動作
	}

	public void addOverlay(OverlayItem overlay) 
	{
		items.add(overlay);
	    populate();//準備ItemizedOverly建構後，所需的處理動作
	}
	
	@Override
	protected OverlayItem createItem(int i) //被populate()呼叫
	{//依據參數 i 抓取對應的OverlayItem顯示在地圖上
	  return items.get(i);
	}

	@Override
	public int size() //被populate()呼叫，用以計算圖標的數量
	{
	  return items.size();
	}
	
	@Override
	protected boolean onTap(int index) //使用者點選到圖標時觸發
	{
		Toast.makeText(mcontext, "這裡是 " + items.get(index).getSnippet(), Toast.LENGTH_SHORT).show();
		return true;		
	}

}
