package huadi.com.Route;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RealTimeBike extends AsyncTask<Integer, Integer, String>
{
	private final String bikeurl = "http://its.taipei.gov.tw/aspx/Youbike.aspx?Mode=1";
	String info = "";

	Transform trans = new Transform();
	
	String mitems; //按下的圖標
	Context mcontext;

	public RealTimeBike(String items, Context context)
	{
		mitems = items;
		mcontext = context;		
	}

	@Override
	protected String doInBackground(Integer... params)
	{
		if (params.length < 0)
			return null;

		HttpGet get = new HttpGet(bikeurl);
		String strRequest = "";
		try
		{
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse httpResponse = null;
			httpResponse = httpClient.execute(get);
			if (httpResponse.getStatusLine().getStatusCode() == 200)// 判斷網路連接是否成功
			{
				strRequest = EntityUtils.toString(httpResponse.getEntity());
				//Log.e("strResult", "" + strRequest);
				if (strRequest.length() > 0)
				{
					info = Separate(strRequest.split("[|]"), params[0] );
				}
			}
		}
		catch (Exception e)
		{
			Log.e("Exception", e.toString());
		}
		return info;
	}
	
	private String Separate(String[] request, int num)
	{
		Arrays.sort(request, new CustomComparator()); // 照代號排序
		
		//寫下所有的腳踏車站點---------------------------------------------------------
		// 代號 名稱 總車位 目前車輛數 地區 地址 英文區域 英文名稱
		// sno + "_" + sna + "_" + tot + "_" + sbi + "_" + sarea + "_" + lat +
		// "_" + lng + "_" + ar + "_" + sareaen + "_" + snaen + "_|";
//		String sno = "";// 代號
//		String sna = "";// 名稱
//		String tot = "";// 總車位
//		String sbi = "";// 目前車輛數
//		String lat = "";// tm2_tw67
//		String lng = "";
//		String snaen = "";// 英文名稱
//		String bemp = "";// 可用車位數		
//		
//		try 
//		{
//			FileWriter fw = new FileWriter("/sdcard/output.txt", false);
//			BufferedWriter bw = new BufferedWriter(fw); // 將BufferedWeiter與FileWrite物件做連結
//			
//			for (String BikeInfo : request)
//			{
//				sno = BikeInfo.split("_")[0];
//				sna = BikeInfo.split("_")[1];
//				tot = BikeInfo.split("_")[2];
//				sbi = BikeInfo.split("_")[3];
//				lat = BikeInfo.split("_")[5];
//				lng = BikeInfo.split("_")[6];
//				snaen = BikeInfo.split("_")[9];
//				
//				bw.write("items.add(new OverlayItem(new GeoPoint(" + 
//						trans.TWD67_To_lonlat(Integer.parseInt(lat),Integer.parseInt(lng), 2)
//						+ "), null, \" " + sna + " \" )); \r\n");		        
//			}
//			bw.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		//寫下所有的腳踏車站點----------------------------------------------------------------------
		
		return request[num].split("_")[3] + " / " + request[num].split("_")[2];
	}

	protected void onPostExecute(String place)
	{
		Toast.makeText(mcontext, "這裡是 " + mitems+ 
				"\r\n目前車位 " + place, Toast.LENGTH_SHORT).show();
	}
}

class CustomComparator implements Comparator<String> // 排序用
{
	public int compare(String str1, String str2)
	{
		int name1 = Integer.parseInt(str1.split("_")[0]);
		int name2 = Integer.parseInt(str2.split("_")[0]);
		return name1 - name2;
	}
}
