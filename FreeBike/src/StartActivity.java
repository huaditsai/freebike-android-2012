import huadi.com.MainActivity;
import huadi.com.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class StartActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.some);
		
		Intent intent = new Intent(StartActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}