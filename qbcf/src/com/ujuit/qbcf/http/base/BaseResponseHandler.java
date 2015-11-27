package com.ujuit.qbcf.http.base;

import org.json.JSONException;
import org.json.JSONObject;

import com.anthole.quickdev.commonUtils.T;
import com.anthole.quickdev.commonUtils.jsonUtils.JSONUtils;
import com.anthole.quickdev.commonUtils.logUtils.Logs;
import com.anthole.quickdev.http.TextHttpResponseHandler;
import com.ujuit.qbcf.BuildConfig;
import com.ujuit.qbcf.bean.ws_code;
import com.ujuit.qbcf.utils.Constants.Tip;

import cz.msebera.android.httpclient.Header;

import android.content.Context;



/**
 * 处理网络请求结果
 * @author Administrator
 *
 */
public abstract class BaseResponseHandler extends TextHttpResponseHandler{
	

	@Override
	public void onFailure(final int statusCode, Header[] headers, String responseString, Throwable throwable) {
		try {
			if(BuildConfig.DEBUG){
				Logs.d("网络请求失败", statusCode+responseString+"\n"+throwable.getMessage());
			}
			if(statusCode>400){
				onFailure(ws_code.FAIL, "接口异常");
			}else{
				onFailure(ws_code.SLOWNET, Tip.SLOW_NET);
			}
		} catch (Exception e) {
		}
		
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, String responseString) {
		try {
			if(BuildConfig.DEBUG){
				Logs.d("网络请求结果", responseString);
			}
			JSONObject obj;
			try {
				obj = new JSONObject(responseString);
				ws_code code = ws_code.parse(JSONUtils.getInt(obj, "WS_RET_CODE", -1));
				final String data = JSONUtils.getString(obj, "WS_RET_DATA", "");
				String message = JSONUtils.getString(obj, "WS_RET_MSG", "");
				if(code == ws_code.SUCCESS){
					onSuccess(data);
				}else{
					onFailure(code, message);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				onFailure(ws_code.JSONERROR, "解析异常");
			}
		} catch (Exception e) {
			
		}
	}
	
	
	public abstract void onSuccess(String data);
	
	public abstract void onFailure(ws_code code,String message);
	
	/**
	 * 处理异常
	 * @param context
	 * @param code
	 * @param message
	 */
	public void handlerError(Context context,ws_code code,String message,boolean showToast){
		try {
			if(showToast){
				T.showLong(context, message);
			}
			
			if(code == ws_code.NO_LOGIN){
				//TODO 登录去
			}
		} catch (Exception e) {
			
		}
		
	}

}
