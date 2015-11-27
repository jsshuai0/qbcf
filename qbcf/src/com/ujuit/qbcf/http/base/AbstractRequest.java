package com.ujuit.qbcf.http.base;

import com.anthole.quickdev.commonUtils.NetUtils;
import com.anthole.quickdev.http.RequestHandle;
import com.anthole.quickdev.http.RequestParams;
import com.anthole.quickdev.http.base.AsyncHttpClientUtil;
import com.ujuit.qbcf.bean.ws_code;

import android.content.Context;
import android.os.Handler;

/**
 * @author Administrator
 * 
 */
public abstract class AbstractRequest implements BaseRequest {

	private final static int DEFAULT_PAGE_STEP = 10;
	
	private final static String PAGE_NUM_KEY = "page_num";

	private final static String PAGE_STEP_KEY = "page_step";

	protected RequestHandle mRequestHandle = null;

	protected Context mContext;
	
	protected int pageStep;

	@Override
	public int getPageStep() {
		return pageStep;
	}

	public void setPageStep(int pageStep) {
		this.pageStep = pageStep;
	}

	public AbstractRequest(Context context) {
		this.mContext = context;
		this.pageStep = DEFAULT_PAGE_STEP;
	}

	/**
	 * 获取controller名
	 * 
	 * @return
	 */
	public abstract String getControllerName();

	/**
	 * 获取方法名
	 * 
	 * @return
	 */
	public abstract String getMethodName();

	@Override
	public void request(BaseResponseHandler responseHandler) {
		request(getParams(), responseHandler);
	}

	@Override
	public void request(RequestParams params, BaseResponseHandler responseHandler) {
		request(getControllerName(), getMethodName(), params, responseHandler);
	}

	/**
	 * 请求
	 * 
	 * @param controller
	 *            模块 名
	 * @param method
	 *            方法名
	 * @param params
	 *            参数
	 * @param responseHandler
	 */
	public void request(String controller, String method, RequestParams params,final BaseResponseHandler responseHandler) {

		if (!NetUtils.isConnected(mContext)) {
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					responseHandler.onFailure(ws_code.NONET, "无网络请求");
				}
			}, 100);
			
			return;
		}
		HttpMethod httpMethod = getHttpMethod();
		if (params == null) {
			params = new RequestParams();
		}
		String url = getHost() + "/api/" + controller + "/" + method + "?from=app";
		switch (httpMethod) {
		case POST:
			mRequestHandle = AsyncHttpClientUtil.getInstance(mContext).post(url, params, responseHandler);
			break;
		case GET:
			mRequestHandle = AsyncHttpClientUtil.getInstance(mContext).get(url, params, responseHandler);
			break;
		}
	}

	@Override
	public String getHost() {
		return BaseHost;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		if (mRequestHandle != null) {
			return mRequestHandle.cancel(mayInterruptIfRunning);
		}
		return false;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

	public void requestPage(int page, BaseResponseHandler responseHandler) {
		RequestParams params = getParams();
		if (params != null) {
			params.put(PAGE_NUM_KEY, page);
			if (!params.has(PAGE_STEP_KEY)) {
				params.put(PAGE_STEP_KEY, pageStep);
			}
		}
		request(params, responseHandler);
	}


}
