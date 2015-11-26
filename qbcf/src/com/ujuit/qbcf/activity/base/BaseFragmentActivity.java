package com.ujuit.qbcf.activity.base;

import com.anthole.quickdev.QFragmentActivity;
import com.anthole.quickdev.ui.IProgressDialog;
import com.anthole.quickdev.ui.QProgressDialog;

public abstract class BaseFragmentActivity extends QFragmentActivity{


	@Override
	protected IProgressDialog createIProgressDialog() {
		return new QProgressDialog(this) ;
	}

}
