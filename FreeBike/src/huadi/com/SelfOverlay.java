package huadi.com;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class SelfOverlay extends ItemizedOverlay<OverlayItem>
{	
	//宣告items列表，負責儲存圖標列表
	private static List<OverlayItem> items = new ArrayList<OverlayItem>();
	
	public SelfOverlay(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));		
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
		return true;		
	}
	
}
