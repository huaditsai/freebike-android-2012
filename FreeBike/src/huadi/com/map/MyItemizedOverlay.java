package huadi.com.map;

import huadi.com.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.ItemizedOverlay.OnFocusChangeListener;

@SuppressWarnings("rawtypes")
public class MyItemizedOverlay extends ItemizedOverlay implements OnFocusChangeListener,OnClickListener
{
	private List<OverlayItem> overlays = new ArrayList<OverlayItem>();
	private GeoPoint point = null;
	private String desc = "";
	private int layout_x = 0; // 用於設置popview 相對某個位置向x軸偏移
	private int layout_y = -30; // 用於設置popview 相對某個位置向x軸偏移
	
	private MapView mMapView;
	private MapController mMapCtrl;
	private View mPopView;
	
	private Drawable itemDrawable;
	public void setItemSelectDrawable(Drawable itemSelectDrawable) {
	}

	public MyItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public MyItemizedOverlay(Drawable defaultMarker, Context context, MapView mapView, View popView, MapController mapCtrl) 
	{
		super(boundCenterBottom(defaultMarker));
		itemDrawable = defaultMarker;
		setOnFocusChangeListener(this);
		layout_x = itemDrawable.getBounds().centerX();
		layout_y = - itemDrawable.getBounds().height();
		mMapView = mapView;
		mPopView = popView;
		mMapCtrl = mapCtrl;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays.get(i);
	}

	@Override
	public int size() {
		return overlays.size();
	}

	public void addOverlay(OverlayItem item) 
	{
		overlays.add(item);
		populate();
	}

	public void removeOverlay(int location) {
		overlays.remove(location);
	}

	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		return super.onTap(p, mapView);
	}

	@Override
	protected boolean onTap(int index) {
		return super.onTap(index);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
	}

	@Override
	public void onFocusChanged(ItemizedOverlay overlay, OverlayItem newFocus) 
	{
		if (null != newFocus) 
		{
			MapView.LayoutParams params = (MapView.LayoutParams) mPopView.getLayoutParams();
			params.x = this.layout_x;//Y軸偏移
			params.y = this.layout_y;//Y軸偏移
			point = newFocus.getPoint();
			params.point = point;
			mMapCtrl.animateTo(point);
			TextView title_TextView = (TextView) mPopView.findViewById(R.id.map_bubbleTitle);
			title_TextView.setText(newFocus.getTitle());
			TextView desc_TextView = (TextView) mPopView.findViewById(R.id.map_bubbleText);
			if(null == newFocus.getSnippet() || "".equals(newFocus.getSnippet()))
			{
				desc_TextView.setVisibility(View.GONE);
			}
			else
			{
				desc = newFocus.getSnippet();
				desc_TextView.setText(desc);
				desc_TextView.setVisibility(View.VISIBLE);
			}
			RelativeLayout button = (RelativeLayout) mPopView.findViewById(R.id.map_bubblebtn);
			button.setOnClickListener(this);
			mMapView.updateViewLayout(mPopView, params);
			mPopView.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onClick(View v) //按下彈出的泡泡時
	{
		switch (v.getId()) 
		{
			case R.id.map_bubblebtn:
				break;
		}
	}
	
}