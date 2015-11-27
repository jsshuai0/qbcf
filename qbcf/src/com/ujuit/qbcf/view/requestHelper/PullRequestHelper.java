package com.ujuit.qbcf.view.requestHelper;

import android.content.Context;
import android.view.View;

import com.anthole.quickdev.ui.MultiStateView;
import com.anthole.quickdev.ui.RequestHelper.RequestHelper;
import com.anthole.quickdev.ui.ptr.PtrFrameLayout;
import com.anthole.quickdev.ui.ptr.PtrHandler;
import com.ujuit.qbcf.bean.ws_code;
import com.ujuit.qbcf.http.base.BaseResponseHandler;

public class PullRequestHelper<T,V extends PtrFrameLayout> extends RequestHelper<T, V,BaseResponseHandler> implements PtrHandler{

	boolean success = false;
	public PullRequestHelper(Context context) {
		super(context);
	}

	@Override
	public BaseResponseHandler getResponseHandler() {
		return new BaseResponseHandler() {
			
			@Override
			public void onSuccess(String responseString) {
				success = true;
				contentView.refreshComplete();
			}

			@Override
			public void onFailure(ws_code code, String message) {
				contentView.refreshComplete();
				if(!success){
					multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
				}
			}
		};
	}
	
	@Override
	public void refresh() {
		multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
		contentView.autoRefresh();
	}

	@Override
	public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
		return false;
	}

	@Override
	public void onRefreshBegin(PtrFrameLayout frame) {
		dataSource.request(responseHandler);
	}
	
	
	

}
