package com.petterp.latte_core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.ui.camera.RequestCodes;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;
import com.petterp.latte_core.util.callback.IGlobalCallback;
import com.petterp.latte_core.util.log.LatteLogger;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author Petterp on 2019/6/18
 * Summary:二维码扫描
 * 邮箱：1509492795@qq.com
 */
public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {


    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        @SuppressWarnings("unchecked")
        final IGlobalCallback<String> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }
        getSupportDelegate().pop();
    }
}
