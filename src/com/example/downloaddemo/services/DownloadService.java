package com.example.downloaddemo.services;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpStatus;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.downloaddemo.bean.FileInfo;

public class DownloadService extends Service {

	public static final int INIT_THREAD = 1;
	public static final String DOWNLOAD_PAHT = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/mydownload";
	public static final String ACTION_STAR = "ACTION_STAR";
	public static final String ACTION_STOP = "ACTION_STOP";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (ACTION_STAR.equals(intent.getAction())) {
			FileInfo fileInfo = (FileInfo) intent
					.getSerializableExtra("fileInfo");
			Log.i("test", "star:" + fileInfo.toString());
			new InitThread(fileInfo).start();
		} else if (ACTION_STOP.equals(intent.getAction())) {
			FileInfo fileInfo = (FileInfo) intent
					.getSerializableExtra("fileInfo");
			Log.i("test", "stop:" + fileInfo.toString());
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case INIT_THREAD:
				FileInfo file_info = (FileInfo) msg.obj;
				Log.i("test", "handler:" + file_info.toString());
				break;

			default:
				break;
			}
		};
	};

	/**
	 * 初始化子线程
	 */
	public class InitThread extends Thread {
		private FileInfo mFileInfo;

		public InitThread(FileInfo fileInfo) {
			mFileInfo = fileInfo;
		}

		@Override
		public void run() {
			HttpURLConnection conn = null;
			RandomAccessFile raf = null;
			try {
				// 连接网络
				URL url = new URL(mFileInfo.getUrl());
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000);
				conn.setRequestMethod("GET");
				int length = -1;
				if (conn.getResponseCode() == HttpStatus.SC_OK) {
					length = conn.getContentLength();
				}
				if (length <= 0) {
					// 连接有问题
					return;
				}
				File dir = new File(DOWNLOAD_PAHT);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				//创建本地文件
				File file = new File(dir, mFileInfo.getFileName());
				raf = new RandomAccessFile(file, "rwd");
				raf.setLength(length);
				mFileInfo.setLength(length);
				mHandler.obtainMessage(INIT_THREAD, mFileInfo).sendToTarget();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.disconnect();
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
