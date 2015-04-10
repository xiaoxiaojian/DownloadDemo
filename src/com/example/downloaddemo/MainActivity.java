package com.example.downloaddemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
		final FileInfo fileInfo = new FileInfo(0, "node-v0.12.1-x64.msi",
				"http://nodejs.org/dist/v0.12.1/x64/node-v0.12.1-x64.msi", 0, 0);
		mBeginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DownloadService.class);
				intent.setAction(DownloadService.ACTION_STAR);
				intent.putExtra("fileInfo", fileInfo);
				startService(intent);
			}
		});
		mStopBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DownloadService.class);
				intent.setAction(DownloadService.ACTION_STOP);
				intent.putExtra("fileInfo", fileInfo);
				startService(intent);
			}
		});
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.textView1);
		mBeginBtn = (Button) findViewById(R.id.beginbtn);
		mStopBtn = (Button) findViewById(R.id.stopbtn);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
	}
}
