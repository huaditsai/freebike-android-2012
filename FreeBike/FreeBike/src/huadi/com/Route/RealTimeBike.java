package huadi.com.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RealTimeBike extends AsyncTask<Integer, Integer, String>
{
//	private final String bikeurl = "http://its.taipei.gov.tw/aspx/Youbike.aspx?Mode=1"; //台北市交通局5分鐘更新(舊)
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
		
		HttpURLConnection con = null;		
		try
		{
			URL url = new URL("http://www.youbike.com.tw/info3b.php?sno=" + String.format("%04d", params[0]) );
			Log.e("Exception",String.format("%04d", params[0]));
	        con = (HttpURLConnection) url.openConnection();            
	        con.setReadTimeout(10000);
	        con.setConnectTimeout(15000);
	        con.setRequestMethod("GET" );
	        con.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.2; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9");
	        con.setDoInput(true);
	        con.connect();
	
	        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8" ));
	
	        String n, result="";
	        StringBuilder htmlContent = new StringBuilder();
	        while ((n = reader.readLine()) != null)
	           htmlContent.append(n);
	
	        result = htmlContent.toString();
	        
	        String pattern;
	        pattern = "sbi\\s*\\=\\s*'([0-9]+)?'.*sus\\s*\\=\\s*'([0-9]+)?';";//"<h3[^>]*>[^<]*<a href=\"(.*?)\"[^>]*>(.*?)</a></h3>";
	        Pattern p = Pattern.compile( pattern , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	        Matcher m = p.matcher( result );
	        while( m.find() )
	        {
	        	int total = Integer.parseInt(m.group(1)) + Integer.parseInt(m.group(2));
	        	info = m.group(1) + " / " + total ;
	        }
		}
		catch(Exception e)
		{
			Log.e("Exception",e.toString());
		}
		finally
        {
            if ( con != null )
                con.disconnect();
        }

//		HttpGet get = new HttpGet(bikeurl);
//		String strRequest = "";
//		try
//		{
//			HttpParams httpParameters = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
//			HttpClient httpClient = new DefaultHttpClient(httpParameters);
//			HttpResponse httpResponse = null;
//			httpResponse = httpClient.execute(get);
//			if (httpResponse.getStatusLine().getStatusCode() == 200)// 判斷網路連接是否成功
//			{
//				strRequest = EntityUtils.toString(httpResponse.getEntity());
//				//Log.e("strResult", "" + strRequest);
//				if (strRequest.length() > 0)
//				{
//					info = Separate(strRequest.split("[|]"), params[0] );
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			Log.e("Exception", e.toString());
//		}
		return info;
	}
	
//	private String Separate(String[] request, int num)
//	{
//		// 代號 名稱 總車位 目前車輛數 地區 地址 英文區域 英文名稱
//		// sno + "_" + sna + "_" + tot + "_" + sbi + "_" + sarea + "_" + lat +
//		// "_" + lng + "_" + ar + "_" + sareaen + "_" + snaen + "_|";
////		String sno = "";// 代號
////		String sna = "";// 名稱
////		String tot = "";// 總車位
////		String sbi = "";// 目前車輛數
////		String lat = "";// tm2_tw67
////		String lng = "";
////		String snaen = "";// 英文名稱
////		String bemp = "";// 可用車位數
//		
//		Arrays.sort(request, new CustomComparator()); // 照代號排序
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
//		
//		return request[num].split("_")[3] + " / " + request[num].split("_")[2];
//	}

	protected void onPostExecute(String place)
	{
		Toast.makeText(mcontext, "這裡是 " + mitems+ 
				"\r\n目前車位 " + place, Toast.LENGTH_SHORT).show();
	}
}

//class CustomComparator implements Comparator<String> // 排序用
//{
//	public int compare(String str1, String str2)
//	{
//		int name1 = Integer.parseInt(str1.split("_")[0]);
//		int name2 = Integer.parseInt(str2.split("_")[0]);
//		return name1 - name2;
//	}
//}
