package com.example.downloaddemo.bean;

import java.io.Serializable;

/**
 * 文件实体类
 * @author Administrator
 *
 */
public class FileInfo implements Serializable{
	private int id;
	private String fileName;
	private String url;
	private int length;
	private int finshed;

	public FileInfo() {
		super();
	}

	public FileInfo(int id, String fileName, String url, int length, int finshed) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.url = url;
		this.length = length;
		this.finshed = finshed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFinshed() {
		return finshed;
	}

	public void setFinshed(int finshed) {
		this.finshed = finshed;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", url=" + url
				+ ", length=" + length + ", finshed=" + finshed + "]";
	}

}
