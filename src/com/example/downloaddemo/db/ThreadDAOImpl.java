package com.example.downloaddemo.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.downloaddemo.bean.ThreadInfo;

public class ThreadDAOImpl implements ThreadDAO {

	private DBHelper dbHelper = null;

	public ThreadDAOImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void insertThread(ThreadInfo threadInfo) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(
				"insert into thread_info (thread_id,url,star,end,finished) values (?,?,?,?,?)",
				new Object[] { threadInfo.getId(), threadInfo.getUrl(),
						threadInfo.getStart(), threadInfo.getEnd(),
						threadInfo.getFinshed() });
		db.close();
	}

	@Override
	public void delteThread(String url, int thread_id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("delete from thread_info where url = ? and thread_id = ?",
				new Object[] { url, thread_id });
		db.close();

	}

	@Override
	public void updateThread(String url, int thread_id, int finshed) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(
				"update thread_info set finished = ? where url = ? and thread_id= ?",
				new Object[] { finshed, url, thread_id });
		db.close();

	}

	@Override
	public List<ThreadInfo> getThreadInfo(String url) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		List<ThreadInfo> list = new ArrayList<ThreadInfo>();
		Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
				new String[] { url });
		while (cursor.moveToNext()) {
			ThreadInfo thread = new ThreadInfo();
			thread.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
			thread.setUrl(cursor.getString(cursor.getColumnIndex("url")));
			thread.setStart(cursor.getInt(cursor.getColumnIndex("star")));
			thread.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
			thread.setFinshed(cursor.getInt(cursor.getColumnIndex("finished")));
			list.add(thread);
		}
		cursor.close();
		db.close();
		return list;
	}

	@Override
	public boolean isExists(String url, int thread_id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from thread_info where url = ? and thread_id = ?",
				new String[] { url, thread_id + "" });
		boolean exists = cursor.moveToNext();
		cursor.close();
		db.close();
		return exists;
	}

}
