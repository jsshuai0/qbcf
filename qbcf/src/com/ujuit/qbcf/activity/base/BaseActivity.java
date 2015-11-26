package com.ujuit.qbcf.activity.base;

import com.anthole.quickdev.QActivity;
import com.anthole.quickdev.ui.IProgressDialog;
import com.anthole.quickdev.ui.QProgressDialog;

public abstract class BaseActivity extends QActivity{


	@Override
	public IProgressDialog createIProgressDialog() {
		return new QProgressDialog(this) ;
	}

}
