package com.ujuit.qbcf.view;

import com.anthole.quickdev.commonUtils.DensityUtils;
import com.anthole.quickdev.ui.ptr.PtrFrameLayout;
import com.anthole.quickdev.ui.ptr.header.MaterialHeader;

import android.content.Context;
import android.util.AttributeSet;


public class PtrMaterialFrameLayout extends PtrFrameLayout {

    private MaterialHeader mPtrClassicHeader;

    public PtrMaterialFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public PtrMaterialFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrMaterialFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new MaterialHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        mPtrClassicHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        mPtrClassicHeader.setPadding(0, DensityUtils.dp2px(getContext(), 15f), 0, DensityUtils.dp2px(getContext(), 10f));
        addPtrUIHandler(mPtrClassicHeader);
        setPinContent(true);
    }

    public MaterialHeader getHeader() {
        return mPtrClassicHeader;
    }

//    /**
//     * Specify the last update time by this key string
//     *
//     * @param key
//     */
//    public void setLastUpdateTimeKey(String key) {
//        if (mPtrClassicHeader != null) {
//            mPtrClassicHeader.setLastUpdateTimeKey(key);
//        }
//    }
//
//    /**
//     * Using an object to specify the last update time.
//     *
//     * @param object
//     */
//    public void setLastUpdateTimeRelateObject(Object object) {
//        if (mPtrClassicHeader != null) {
//            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
//        }
//    }
}
