package com.ujuit.qbcf.activity;

import com.anthole.quickdev.invoke.SystemBarTintInvoke;
import com.ujuit.qbcf.R;
import com.ujuit.qbcf.activity.base.BaseActivity;
import com.ujuit.qbcf.activity.base.BaseSwipeActivity;

import android.os.Bundle;

public class MainActivity extends BaseSwipeActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}
	
	@Override
	protected void initData(Bundle savedInstanceState) {
		
	}

	@Override
	public void afterInitSwipe() {
		SystemBarTintInvoke.apply(this, R.color.red);
	}
}
