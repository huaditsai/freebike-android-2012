package huadi.com;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

@SuppressWarnings("deprecation")
public class RotateView  extends ViewGroup implements SensorListener 
{
    private static final float SQ2 = 1.414213562373095f; // 2 的平方根 (sqrt)
    private final SmoothCanvas mCanvas = new SmoothCanvas();
    private float mHeading = 0;

    public RotateView(Context context) 
    {
        super(context);
    }
    
    public void onSensorChanged(int sensor, float[] values) // 當 Senser 數據更動時
    {
        //Log.d(TAG, "x: " + values[0] + "y: " + values[1] + "z: " + values[2]);
        synchronized (this)
        {
            mHeading = values[0]; // 指定角度數據給 mHeading 變數
            invalidate(); // 廢止 : 清空角度數據有變動的通知指標
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        canvas.save(Canvas.MATRIX_SAVE_FLAG); // 儲存目前的矩陣標記
        canvas.rotate(-1 * mHeading, getWidth() * 0.5f, getHeight() * 0.5f); // 旋轉畫布
        
        mCanvas.delegate = canvas; // 指定目前畫布給自定畫布物件內的參數
        super.dispatchDraw(mCanvas); // 派送自定畫布
        canvas.restore(); // 還原矩陣標記
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) 
    {
        final int width = getWidth();
        final int height = getHeight();
        final int count = getChildCount();
        
        for (int i = 0; i < count; i++) 
        {
            final View view = getChildAt(i);
            final int childWidth = view.getMeasuredWidth(); // 指定子圖寬度為 View 實測寬度
            final int childHeight = view.getMeasuredHeight(); // 指定子圖高度為 View 的實測高度
            final int childLeft = (width - childWidth) / 2; // 指定子圖左邊界寬度
            final int childTop = (height - childHeight) / 2; // 指定子圖上邊界寬度
            
            view.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            // 重新指定 View 的 Layout 的 左.上.右.下.端點座標.
            // 相當於將 Map 轉向後, 在 Map 圖上重切出一塊正向的直角長方形圖.
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
        int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int sizeSpec;
        
        if (w > h)// 如是 寬大於高 
        {        	
            sizeSpec = MeasureSpec.makeMeasureSpec((int) (w * SQ2), MeasureSpec.EXACTLY); // 以 width * 2 的平方根算出實測尺寸
        } 
        else // 如非 寬大於高
        {        	
            sizeSpec = MeasureSpec.makeMeasureSpec((int) (h * SQ2), MeasureSpec.EXACTLY); // 以 height * 2 的平方根算出實測尺寸
        }
        final int count = getChildCount();
        
        for (int i = 0; i < count; i++) 
        {
            getChildAt(i).measure(sizeSpec, sizeSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) 
    {
        return super.dispatchTouchEvent(ev);
    }

    public void onAccuracyChanged(int sensor, int accuracy) 
    {
        // TODO Auto-generated method stub        
    }
}
