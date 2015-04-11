package com.example.downloaddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.downloaddemo.bean.FileInfo;
import com.example.downloaddemo.services.DownloadService;

public class MainActivity extends ActionBarActivity {
	private TextView mTextView;
	private Button mBeginBtn, mStopBtn;
	private ProgressBar mProgressBar;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initView();
		// 创建文本信息
		final FileInfo fileInfo = new FileInfo(0, "imooc.apk",
				"http://www.imooc.com/mobile/imooc.apk", 0, 0);
		mBeginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DownloadService.class);
				intent.setAction(DownloadService.ACTION_STAR);
				intent.putExtra("fileInfo", fileInfo);
				startService(intent);
				Toast.makeText(mContext, "开始下载", 1).show();

			}
		});
		mStopBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DownloadService.class);
				intent.setAction(DownloadService.ACTION_STOP);
				intent.putExtra("fileInfo", fileInfo);
				startService(intent);
				Toast.makeText(mContext, "暂停下载", 1).show();
			}
		});
		// 注册广播接收器
		IntentFilter filter = new IntentFilter();
		filter.addAction(DownloadService.ACTION_UPDATE);
		registerReceiver(mReceiver, filter);
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.textView1);
		mBeginBtn = (Button) findViewById(R.id.beginbtn);
		mStopBtn = (Button) findViewById(R.id.stopbtn);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		mProgressBar.setMax(100);
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	};

	/**
	 * 更新UI进度
	 */
	BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(DownloadService.ACTION_UPDATE)) {
				int finished = intent.getIntExtra("finished", 0);
				Log.i("test", "receiver finished:" + finished);
				mProgressBar.setProgress(finished);
			}
		}
	};
}
