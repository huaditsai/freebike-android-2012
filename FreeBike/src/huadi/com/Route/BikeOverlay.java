package huadi.com.Route;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class BikeOverlay extends ItemizedOverlay<OverlayItem>
{	
	//宣告items列表，負責儲存圖標列表
	private static List<OverlayItem> items = new ArrayList<OverlayItem>();
	Context mcontext;//為了用MainActivity叫Toast等
	
	//GeoPoint  = new GeoPoint( (int)( * 1000000), (int)( * 1000000) );
	GeoPoint Taipei_Medical_University = new GeoPoint( (int)(25.026691 * 1000000), (int)(121.561725 * 1000000) );
	GeoPoint FuDe_Park = new GeoPoint( (int)(25.038118 * 1000000), (int)(121.583652 * 1000000) );
	GeoPoint Rongxing_Park = new GeoPoint( (int)(25.064249 * 1000000), (int)(121.540297 * 1000000) );
	GeoPoint Raoho_Street = new GeoPoint( (int)(25.049833 * 1000000), (int)(121.57189 * 1000000) );
	GeoPoint Songshan_High_School_of_Commerce_and_Home_Economics = new GeoPoint( (int)(25.036096 * 1000000), (int)(121.579116 * 1000000) );	
	GeoPoint Guangfu_Rd_Minsheng_St = new GeoPoint( (int)(25.058437 * 1000000), (int)(121.555046 * 1000000) );
	GeoPoint Taipei_Municipal_Stadium = new GeoPoint( (int)(25.048271 * 1000000), (int)(121.552246 * 1000000) );
	GeoPoint ZhongQiang_park = new GeoPoint( (int)(25.029214 * 1000000), (int)(121.569774 * 1000000) );
	GeoPoint Technology_Bldg_station = new GeoPoint( (int)(25.025911 * 1000000), (int)(121.54329 * 1000000) );
	GeoPoint Minsheng_East_Rd_Dunhua_North_Rd = new GeoPoint( (int)(25.058002 * 1000000), (int)(121.548987 * 1000000) );
	
	GeoPoint Songshan_Train_Station = new GeoPoint( (int)(25.048643 * 1000000), (int)(121.578062 * 1000000) );
	GeoPoint Dongxin_Elementary_School = new GeoPoint( (int)(25.05504 * 1000000), (int)(121.602792 * 1000000) );
	GeoPoint Ta_an_Forest_Park = new GeoPoint( (int)(25.033117 * 1000000), (int)(121.537461 * 1000000) );
	GeoPoint Yongji_Rd_Somgxin_Rd = new GeoPoint( (int)(25.045401 * 1000000), (int)(121.572035 * 1000000) );
	GeoPoint Kunyajg_station_Exit1 = new GeoPoint( (int)(25.050154 * 1000000), (int)(121.592374 * 1000000) );
	GeoPoint Taipei_Nangang_exhibition_canter_station_Exit5 = new GeoPoint( (int)(25.05478 * 1000000), (int)(121.616686 * 1000000) );
	GeoPoint WuChang_Park = new GeoPoint( (int)(25.048147 * 1000000), (int)(121.574707 * 1000000) );
	GeoPoint Aiguo_E_Rd_Jinshan_South_Rd = new GeoPoint( (int)(25.03168 * 1000000), (int)(121.526548 * 1000000) );
	GeoPoint Zhangxing_St_Jilong_Rd = new GeoPoint( (int)(25.017059 * 1000000), (int)(121.544384 * 1000000) );
	GeoPoint Xinsheng_South_Rd_Jianguo_Elevated_Rd_ = new GeoPoint( (int)(25.022404 * 1000000), (int)(121.534589 * 1000000) );
	
	GeoPoint Liuzhangli_station = new GeoPoint( (int)(25.023906 * 1000000), (int)(121.55316 * 1000000) );
	GeoPoint Zhonglun_High_School = new GeoPoint( (int)(25.048786 * 1000000), (int)(121.560885 * 1000000) );
	GeoPoint Xingtian_temple_station_Exit1 = new GeoPoint( (int)(25.058364 * 1000000), (int)(121.532929 * 1000000) );
	GeoPoint Xingtian_temple_station_Exit3 = new GeoPoint( (int)(25.060002 * 1000000), (int)(121.533315 * 1000000) );
	GeoPoint NTU_Information_Building = new GeoPoint( (int)(25.021013 * 1000000), (int)(121.541509 * 1000000) );
	GeoPoint Dongmen_Station_Exit5 = new GeoPoint( (int)(25.033698 * 1000000), (int)(121.529163 * 1000000) );
	GeoPoint National_Taiwan_Normal_University_of_Library = new GeoPoint( (int)(25.026684 * 1000000), (int)(121.528924 * 1000000) );
	GeoPoint Nangang_Park = new GeoPoint( (int)(25.05801 * 1000000), (int)(121.614237 * 1000000) );
	GeoPoint Yucheng_Park = new GeoPoint( (int)(25.042913 * 1000000), (int)(121.586398 * 1000000) );
	GeoPoint Academia_Park = new GeoPoint( (int)(25.047425 * 1000000), (int)(121.613693 * 1000000) );
	
	GeoPoint Taipei_City_Hall_Station_2 = new GeoPoint( (int)(25.040898 * 1000000), (int)(121.567851 * 1000000) );

	GeoPoint Taipei_City_Hall_Station_1 = new GeoPoint( (int)(25.0408388889 * 1000000), (int)(121.567894444 * 1000000) );
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
	
	public BikeOverlay(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		mcontext = context;
		//items.add(new OverlayItem( , null, null));
		items.add(new OverlayItem(Taipei_Medical_University, null, "臺北醫學大學"));
		items.add(new OverlayItem(FuDe_Park, null, "福德公園"));
		items.add(new OverlayItem(Rongxing_Park, null, "榮星花園"));
		items.add(new OverlayItem(Raoho_Street, null, "饒河夜市"));
		items.add(new OverlayItem(Songshan_High_School_of_Commerce_and_Home_Economics, null, "松山家商"));
		items.add(new OverlayItem(Guangfu_Rd_Minsheng_St, null, "民生光復路口"));
		items.add(new OverlayItem(Taipei_Municipal_Stadium, null, "社教館"));
		items.add(new OverlayItem(ZhongQiang_park, null, "中強公園"));
		items.add(new OverlayItem(Technology_Bldg_station, null, "捷運科技大樓站"));
		items.add(new OverlayItem(Minsheng_East_Rd_Dunhua_North_Rd, null, "民生敦化路口"));
		
		items.add(new OverlayItem(Songshan_Train_Station, null, "松山車站"));
		items.add(new OverlayItem(Dongxin_Elementary_School, null, "東新國小"));
		items.add(new OverlayItem(Ta_an_Forest_Park, null, "大安森林公園"));
		items.add(new OverlayItem(Yongji_Rd_Somgxin_Rd, null, "永吉松信路口"));
		items.add(new OverlayItem(Kunyajg_station_Exit1, null, "捷運昆陽站(1號出口)"));
		items.add(new OverlayItem(Taipei_Nangang_exhibition_canter_station_Exit5, null, "捷運南港展覽館站(5號出口)"));
		items.add(new OverlayItem(WuChang_Park, null, "五常公園"));
		items.add(new OverlayItem(Aiguo_E_Rd_Jinshan_South_Rd, null, "金山愛國路口"));
		items.add(new OverlayItem(Zhangxing_St_Jilong_Rd, null, "基隆長興路口"));
		items.add(new OverlayItem(Xinsheng_South_Rd_Jianguo_Elevated_Rd_, null, "辛亥新生路口"));
		
		items.add(new OverlayItem(Liuzhangli_station, null, "捷運六張犁站"));
		items.add(new OverlayItem(Zhonglun_High_School, null, "中崙高中"));
		items.add(new OverlayItem(Xingtian_temple_station_Exit1, null, "捷運行天宮站(1號出口)"));
		items.add(new OverlayItem(Xingtian_temple_station_Exit3, null, "捷運行天宮站(3號出口)"));
		items.add(new OverlayItem(NTU_Information_Building, null, "臺大資訊大樓"));
		items.add(new OverlayItem(Dongmen_Station_Exit5, null, "捷運東門站(5號出口)"));
		items.add(new OverlayItem(National_Taiwan_Normal_University_of_Library, null, "臺灣師範大學(圖書館)"));
		items.add(new OverlayItem(Nangang_Park, null, "南港世貿公園"));
		items.add(new OverlayItem(Yucheng_Park, null, "玉成公園"));
		items.add(new OverlayItem(Academia_Park, null, "中研公園"));
		
		items.add(new OverlayItem(Taipei_City_Hall_Station_2, null, "捷運市政府站-2"));

		items.add(new OverlayItem(Taipei_City_Hall_Station_1, null, "捷運市政府站"));
		items.add(new OverlayItem(Sun_Yet_Sen_Memorial_Hall_Station, null, "捷運國父紀念館站"));
		items.add(new OverlayItem(Taipei_City_Government , null, "臺北市政府"));
		items.add(new OverlayItem(Taipei_City_Hall_Plaza , null,"市民廣場"));
		items.add(new OverlayItem(Xingya_Jr_High_School , null, "興雅國中"));		
		items.add(new OverlayItem(New_York_New_York_Greenway , null, "紐約紐約綠園道"));
		items.add(new OverlayItem(Xinyi_Square , null, "信義廣場"));
		items.add(new OverlayItem(TWTC_Exhibition_Hall , null, "世貿三館"));
		items.add(new OverlayItem(Exit_C_of_the_World_Trade_Center , null, "世貿一館C出口(松德站)"));
		items.add(new OverlayItem(Taipei_City_Disaster_Response_Center , null, "台北市災害應變中心"));
		
		items.add(new OverlayItem(San_Zhang_Li , null, "三張犁"));
		
		//Gener();
		
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
	
	public static GeoPoint minDistience(GeoPoint userPoint)//計算離自己最近的租賃站
	{
		Location locationA = new Location("point A");
		locationA.setLatitude(userPoint.getLatitudeE6() / 1E6);  
		locationA.setLongitude(userPoint.getLongitudeE6() / 1E6);  

		Location locationB = new Location("point B");
		
		float distance[] = new float[items.size()];

		for(int i = 0 ; i < items.size() ; i++)
		{
			locationB.setLatitude(items.get(i).getPoint().getLatitudeE6() / 1E6);  
			locationB.setLongitude(items.get(i).getPoint().getLongitudeE6() / 1E6);
			
			distance[i] = locationA.distanceTo(locationB); //公尺
		}
		
		float min = distance[0];
		int minI = 0;
		
		for(int i=0 ; i<distance.length ; i++)
		{
			if(distance[i] <= min)
			{
				min = distance[i];
				minI = i;
			}
		}
		return items.get(minI).getPoint();
	}
	
	public static GeoPoint CloserStation(GeoPoint start, GeoPoint end, List<OverlayItem> station)//找起點到車站+車站到終點 最近的點
	{
		Location start_Location = new Location("point A");
		Location end_Location = new Location("point B");
		Location station_Location = new Location("point C");
		
		start_Location.setLatitude(start.getLatitudeE6() / 1E6);  
		start_Location.setLongitude(start.getLongitudeE6() / 1E6);
		
		end_Location.setLatitude(end.getLatitudeE6() / 1E6);  
		end_Location.setLongitude(end.getLongitudeE6() / 1E6);
		
		float distance[] = new float[station.size()];

		for(int i = 0 ; i < station.size() ; i++)
		{
			station_Location.setLatitude(station.get(i).getPoint().getLatitudeE6() / 1E6);  
			station_Location.setLongitude(station.get(i).getPoint().getLongitudeE6() / 1E6);
			
			distance[i] = start_Location.distanceTo(station_Location) + station_Location.distanceTo(end_Location); //起點到站+站到終點
		}
		
		float min = distance[0];
		int minI = 0;
		
		for(int i=0 ; i<distance.length ; i++)
		{
			if(distance[i] <= min)
			{
				min = distance[i];
				minI = i;
			}
		}
		return station.get(minI).getPoint();//起點到車站+車站到終點 最近的點
	}
	
	public static List<GeoPoint> WayStation(GeoPoint start, GeoPoint end, List<OverlayItem> station, List<GeoPoint> route)//找出路上的車站
	{
		//先找到 車站終點 比 起點到終點 近的點
		Location start_Location = new Location("point A");
		Location end_Location = new Location("point B");
		Location station_Location = new Location("point C");
		
		List<OverlayItem> closer_station = new ArrayList<OverlayItem>();//車站終點 比 起點到終點 近的點 們
		
		start_Location.setLatitude(start.getLatitudeE6() / 1E6);  
		start_Location.setLongitude(start.getLongitudeE6() / 1E6);
		
		end_Location.setLatitude(end.getLatitudeE6() / 1E6);  
		end_Location.setLongitude(end.getLongitudeE6() / 1E6);		
		
		for(int i = 0 ; i < station.size() ; i++)
		{
			station_Location.setLatitude(station.get(i).getPoint().getLatitudeE6() / 1E6);  
			station_Location.setLongitude(station.get(i).getPoint().getLongitudeE6() / 1E6);
			
			if(station_Location.distanceTo(end_Location) < start_Location.distanceTo(end_Location))
				closer_station.add(station.get(i)); //車站終點 比 起點到終點 近的點 們
		}		
		//找起點到車站+車站到終點 最近的點
		if(closer_station.size() > 1)
		{
			GeoPoint nearest = CloserStation(start, end, closer_station);		
			route.add(nearest);
		
			return WayStation(nearest, end, closer_station, route);
		}
		else
			return route;
	}
	
	public static List<OverlayItem> GetItems()
	{
		return items;
	}

}
