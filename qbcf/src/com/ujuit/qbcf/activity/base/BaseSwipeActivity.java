package com.ujuit.qbcf.activity.base;

import com.anthole.quickdev.ui.IProgressDialog;
import com.anthole.quickdev.ui.QProgressDialog;
import com.anthole.quickdev.ui.swipeback.app.SwipeBackActivity;

public abstract class BaseSwipeActivity extends SwipeBackActivity{

	@Override
	public IProgressDialog createIProgressDialog() {
		return new QProgressDialog(this);
	}
	
	

}
