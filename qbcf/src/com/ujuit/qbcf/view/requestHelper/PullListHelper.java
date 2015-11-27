package com.ujuit.qbcf.view.requestHelper;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.anthole.quickdev.adapter.QuickAdapter;
import com.anthole.quickdev.adapter.QuickAdapter.OnDataChangeListener;
import com.anthole.quickdev.ui.MultiStateView;
import com.anthole.quickdev.ui.RequestHelper.RequestHelper;
import com.anthole.quickdev.ui.ptr.PtrDefaultHandler;
import com.anthole.quickdev.ui.ptr.PtrFrameLayout;
import com.anthole.quickdev.ui.ptr.PtrHandler;
import com.ujuit.qbcf.bean.ws_code;
import com.ujuit.qbcf.http.base.BaseResponseHandler;
import com.ujuit.qbcf.view.xlist.XListView;
import com.ujuit.qbcf.view.xlist.XListView.IXListViewListener;

public class PullListHelper<T,V extends PtrFrameLayout> extends RequestHelper<T, V, BaseResponseHandler> implements PtrHandler,IXListViewListener,OnDataChangeListener{

	private final static int START_PAGE = 1;
	XListView xListView;
	QuickAdapter<T> adapter;
	
	boolean loadMore;
	int page;
	
	public PullListHelper(Context context, boolean loadMore) {
		super(context);
		this.loadMore = loadMore;
	}
	
	@Override
	public void setContentView(V contentView) {
		super.setContentView(contentView);
		xListView = (XListView) contentView.getContentView();
	}
	
	public void setAdatper(QuickAdapter<T> adatper){
		this.adapter =adatper;
	}
	
	@Override
	public void refresh() {
		multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
		contentView.autoRefresh();
	}

	@Override
	public BaseResponseHandler getResponseHandler() {
		return new BaseResponseHandler() {
			
			@Override
			public void onSuccess(String data) {
				contentView.refreshComplete();
				List<T> onePage = parser.parseList(data);
				
				if (onePage != null) {
					adapter.replaceAll(onePage);
				}
				if(loadMore){
					if (onePage==null||onePage.size() < dataSource.getPageStep()) {
						xListView.setPullLoadEnable(false);
					} else {
						xListView.setPullLoadEnable(true);
					}
				}
				page = START_PAGE;
			}
			
			@Override
			public void onFailure(ws_code code, String message) {
				contentView.refreshComplete();
				handlerError(context, code, message, true);
				if (adapter.getCount() == 0) {
					multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
				}
			}
		};
	}

	@Override
	public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
		return PtrDefaultHandler.checkContentCanBePulledDown(frame, xListView, header) && !xListView.isLoading();
	}

	@Override
	public void onRefreshBegin(PtrFrameLayout frame) {
		xListView.setPullLoadEnable(false);
		dataSource.requestPage(START_PAGE, responseHandler);
	}

	@Override
	public void onLoadMore() {
		dataSource.requestPage(page + 1, new BaseResponseHandler() {

			@Override
			public void onSuccess(String data) {
				xListView.stopLoadMore();
				List<T> onePage = parser.parseList(data);
				if (onePage != null) {
					adapter.addAll(onePage);
				}
				if (onePage == null || onePage.size()<dataSource.getPageStep()) {
					xListView.setPullLoadEnable(false);
				} else {
					xListView.setPullLoadEnable(true);
				}
				page++;
			}

			@Override
			public void onFailure(ws_code code, String message) {
				xListView.stopLoadMore();
				handlerError(context, code, message, true);
			}
		});
	}

	@Override
	public void onDataChanged() {
		if ((adapter.getData() == null || adapter.getData().size() == 0) && xListView.getHeaderViewsCount() == 0) {
			multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
		} else {
			multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
		}
	}
	
	
	

}
