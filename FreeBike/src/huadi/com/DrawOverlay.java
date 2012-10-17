package huadi.com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class DrawOverlay extends Overlay
{
	private List<GeoPoint> mGeoPoints = new ArrayList<GeoPoint>();
	private static final int ALPHA = 120;
	private static final float STROKE = 10;
	private final Path path;
	private final Point p;
	private final Paint paint;

	public DrawOverlay(List<GeoPoint> geoPoints)
	{
		mGeoPoints = geoPoints;
		path = new Path();
		p = new Point();
		paint = new Paint();
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		super.draw(canvas, mapView, shadow);

		//線的樣式
		paint.setColor(Color.argb(120, 70, 50, 200));
		paint.setAlpha(ALPHA);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(STROKE);//邊的寬度
		paint.setStyle(Paint.Style.STROKE);

		Projection proj = mapView.getProjection();//投影
		path.rewind();
		Iterator<GeoPoint> it = mGeoPoints.iterator();
		proj.toPixels(it.next(), p);
		path.moveTo(p.x, p.y);

		while (it.hasNext())
		{
			proj.toPixels(it.next(), p);
			path.lineTo(p.x, p.y);
		}
		path.setLastPoint(p.x, p.y);
		
		canvas.drawPath(path, paint);
	}
}
