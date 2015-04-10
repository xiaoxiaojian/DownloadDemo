package com.example.downloaddemo.db;

import java.util.List;

import com.example.downloaddemo.bean.ThreadInfo;

/**
 * ���ݷ��ʽӿ�
 * 
 * @author Administrator
 * 
 */
public interface ThreadDAO {
	/**
	 * �����߳���Ϣ
	 * 
	 * @param threadInfo
	 */
	public void insertThread(ThreadInfo threadInfo);

	/**
	 * ɾ���߳���Ϣ
	 * 
	 * @param url
	 * @param thread_id
	 */
	public void delteThread(String url, int thread_id);

	/**
	 * �����߳����ؽ���
	 * 
	 * @param url
	 * @param thread_id
	 * @param finshed
	 */
	public void updateThread(String url, int thread_id, int finshed);

	/**
	 * ��ѯ�ļ����߳���Ϣ
	 * 
	 * @param url
	 * @return
	 */
	public List<ThreadInfo> getThreadInfo(String url);

	/**
	 * �߳���Ϣ�Ƿ����
	 * 
	 * @param url
	 * @param thread_id
	 * @return
	 */
	public boolean isExists(String url, int thread_id);
}
