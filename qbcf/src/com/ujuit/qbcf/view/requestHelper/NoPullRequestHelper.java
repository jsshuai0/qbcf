package com.ujuit.qbcf.view.requestHelper;

import android.content.Context;
import android.view.View;

import com.anthole.quickdev.ui.MultiStateView;
import com.anthole.quickdev.ui.RequestHelper.RequestHelper;
import com.ujuit.qbcf.bean.ws_code;
import com.ujuit.qbcf.http.base.BaseResponseHandler;

public class NoPullRequestHelper<T,V extends View> extends RequestHelper<T, V,BaseResponseHandler>{

	public NoPullRequestHelper(Context context) {
		super(context);
	}

	@Override
	public BaseResponseHandler getResponseHandler() {
		return new BaseResponseHandler() {
			
			@Override
			public void onSuccess(String responseString) {
				multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
				parser.parseList(responseString);
			}

			@Override
			public void onFailure(ws_code code, String message) {
				handlerError(context, code, message, true);
				multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
			}
		};
	}
	
	

}
