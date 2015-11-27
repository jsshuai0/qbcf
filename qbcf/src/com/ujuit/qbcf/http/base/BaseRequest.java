package com.ujuit.qbcf.http.base;

import com.anthole.quickdev.http.RequestParams;
import com.anthole.quickdev.ui.RequestHelper.base.IDataSource;

public interface BaseRequest extends IDataSource<BaseResponseHandler>{
	
	public static final String BaseHost = "";
	
	public RequestParams getParams();
	
	public String getHost();
	
	/**
	 * 获取请求方法 get post
	 * 
	 * @return
	 */
	public HttpMethod getHttpMethod();
	
	public boolean cancel(boolean mayInterruptIfRunning);
	
	public enum HttpMethod {
		POST, GET
	}
}
