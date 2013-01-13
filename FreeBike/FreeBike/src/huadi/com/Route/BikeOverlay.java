package huadi.com.Route;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class BikeOverlay extends ItemizedOverlay<OverlayItem>
{	
	//宣告items列表，負責儲存圖標列表
	private static List<OverlayItem> items = new ArrayList<OverlayItem>();
	Context mcontext;//為了用MainActivity叫Toast等
	
	public BikeOverlay(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		mcontext = context;
		//items.add(new OverlayItem( , null, null));
		items.add(new OverlayItem(new GeoPoint((int)( 25.04069468907246 * 1E6), (int)( 121.5681683642343 * 1E6)), null, " 捷運市政府站-1 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04088571490804 * 1E6), (int)( 121.56779266584367 * 1E6)), null, " 捷運市政府站-2 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04111348947239 * 1E6), (int)( 121.55769540482456 * 1E6)), null, " 捷運國父紀念館站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.03782651877072 * 1E6), (int)( 121.56506328727501 * 1E6)), null, " 台北市政府 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.03606779365894 * 1E6), (int)( 121.56221115798536 * 1E6)), null, " 市民廣場 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.036594412178673 * 1E6), (int)( 121.56855574131255 * 1E6)), null, " 興雅國中 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.034764132394546 * 1E6), (int)( 121.56554471140144 * 1E6)), null, " 世貿二館 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.033066973944376 * 1E6), (int)( 121.565507190323 * 1E6)), null, " 信義廣場(台北101) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.035241070168134 * 1E6), (int)( 121.5635749016442 * 1E6)), null, " 世貿三館 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.03161611202749 * 1E6), (int)( 121.57424048770635 * 1E6)), null, " 松德站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.028686377226254 * 1E6), (int)( 121.56601225797743 * 1E6)), null, " 台北市災害應變中心 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.034965983739394 * 1E6), (int)( 121.55750901082726 * 1E6)), null, " 三張犁 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.02670772774701 * 1E6), (int)( 121.5616333592187 * 1E6)), null, " 臺北醫學大學 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.038116282130073 * 1E6), (int)( 121.58355624268788 * 1E6)), null, " 福德公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.064271952365335 * 1E6), (int)( 121.54025630977732 * 1E6)), null, " 榮星花園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.049871545632893 * 1E6), (int)( 121.57177857940214 * 1E6)), null, " 饒河夜市 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.03611165737906 * 1E6), (int)( 121.57902796696253 * 1E6)), null, " 松山家商 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.058430866710818 * 1E6), (int)( 121.55492930530143 * 1E6)), null, " 民生光復路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04829346066766 * 1E6), (int)( 121.55216808351058 * 1E6)), null, " 社教館 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.028654260535745 * 1E6), (int)( 121.56969826374642 * 1E6)), null, " 中強公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.025927544292166 * 1E6), (int)( 121.5431795944545 * 1E6)), null, " 捷運科技大樓站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.058010962247003 * 1E6), (int)( 121.54887155101419 * 1E6)), null, " 民生敦化路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.048646809758274 * 1E6), (int)( 121.57798684426102 * 1E6)), null, " 松山車站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.05510322925814 * 1E6), (int)( 121.6026859323695 * 1E6)), null, " 東新國小 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.03295455312573 * 1E6), (int)( 121.53736404171498 * 1E6)), null, " 信義建國路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.045456161782845 * 1E6), (int)( 121.57193645568783 * 1E6)), null, " 永吉松信路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.050170578439644 * 1E6), (int)( 121.59226544257453 * 1E6)), null, " 捷運昆陽站(1號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.054721271230257 * 1E6), (int)( 121.61657936996238 * 1E6)), null, " 捷運南港展覽館站(5號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.048163512984416 * 1E6), (int)( 121.57456542722157 * 1E6)), null, " 五常公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.031666440227198 * 1E6), (int)( 121.52643837876819 * 1E6)), null, " 金山愛國路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.0170850305129 * 1E6), (int)( 121.5442404184872 * 1E6)), null, " 基隆長興路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.02243810547205 * 1E6), (int)( 121.53445459901121 * 1E6)), null, " 辛亥新生路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.023914028595726 * 1E6), (int)( 121.55304964674367 * 1E6)), null, " 捷運六張犁站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04881203503207 * 1E6), (int)( 121.5607629407459 * 1E6)), null, " 中崙高中 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.058394315318914 * 1E6), (int)( 121.53282664956555 * 1E6)), null, " 捷運行天宮站(1號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.060009059167772 * 1E6), (int)( 121.53319046053814 * 1E6)), null, " 捷運行天宮站(3號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.021040636031064 * 1E6), (int)( 121.54142407611846 * 1E6)), null, " 臺大資訊大樓 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.033724635778558 * 1E6), (int)( 121.52905336581387 * 1E6)), null, " 捷運東門站(4號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.026674570363703 * 1E6), (int)( 121.52877536254647 * 1E6)), null, " 臺灣師範大學(圖書館) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.058026758312945 * 1E6), (int)( 121.61410819143059 * 1E6)), null, " 南港世貿公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.042899477425454 * 1E6), (int)( 121.58629429016747 * 1E6)), null, " 玉成公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.047456924654274 * 1E6), (int)( 121.61359957066833 * 1E6)), null, " 中研公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.044335191685846 * 1E6), (int)( 121.58163339656824 * 1E6)), null, " 捷運後山埤站(1號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.035664335224485 * 1E6), (int)( 121.61404619523384 * 1E6)), null, " 凌雲市場 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.059761031923042 * 1E6), (int)( 121.61607931675222 * 1E6)), null, " 捷運南港軟體園區站(2號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.014791264830727 * 1E6), (int)( 121.53443134570222 * 1E6)), null, " 捷運公館站(2號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.05648705610769 * 1E6), (int)( 121.61091901185264 * 1E6)), null, " 南港國小 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04195061481608 * 1E6), (int)( 121.53375637914878 * 1E6)), null, " 捷運忠孝新生(3號出口) " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.052499170864973 * 1E6), (int)( 121.60809443518042 * 1E6)), null, " 南港車站 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.0409323093464 * 1E6), (int)( 121.54814132762766 * 1E6)), null, " 龍門廣場 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.062031858438456 * 1E6), (int)( 121.56007981536945 * 1E6)), null, " 民權運動公園 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.06506139063163 * 1E6), (int)( 121.53666173151323 * 1E6)), null, " 建國農安街口  " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.054786752625606 * 1E6), (int)( 121.53681513005522 * 1E6)), null, " 建國長春路口 " )); 
		items.add(new OverlayItem(new GeoPoint((int)( 25.04481170702603 * 1E6), (int)( 121.5365040408787 * 1E6)), null, " 八德市場 " )); 
		
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
		new RealTimeBike(items.get(index).getSnippet(),mcontext).execute(index);

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
