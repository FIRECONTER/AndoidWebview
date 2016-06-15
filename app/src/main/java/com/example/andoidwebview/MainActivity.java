package com.example.andoidwebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private WebView web;
	private Button btn1;
	private Button btn2;
	private EditText edit;
	private int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		web = (WebView)findViewById(R.id.web1);
		btn1 = (Button)findViewById(R.id.btu1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				web.loadUrl("javascript:add("+i+")");
				i++;
			}
		});

		
		WebSettings er = web.getSettings();//websetting 实现基本的设置浏览器的属性，
		er.setJavaScriptEnabled(true);//设置可以使用javascript
		
		String url = "http://10.0.2.2/Webforwebview";
		//android 虚拟机本机IP映射为10.0.2.2.
		web.addJavascriptInterface(new myobj(),"diao");//添加一个可以被js调用的类型对象
		web.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				web.loadUrl(url);
				return true;
			}
			
			
		});
		web.loadUrl(url);
	}
	class myobj
	{
		int i=0;
		public void myclick()
		{
			//js 调用的函数。实现tianjai
			web.loadUrl("javascript:add("+i+")");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
