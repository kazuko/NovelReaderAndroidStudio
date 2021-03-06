package com.ads;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

import android.app.Activity;

/**
 * Created by steven on 11/12/16.
 */

public class AdInterstitialManager {

    MoPubInterstitial mInterstitial_1;
    MoPubInterstitial mInterstitial_2;
    Activity mActivity;
    boolean isReadyAd_1 = false;
    boolean isReadyAd_2 = false;

    public AdInterstitialManager(Activity activity) {
        mActivity = activity;
        if(!isReadyAd_1)
            requestInterstitial_1();
        if(!isReadyAd_2)
            requestInterstitial_2();
    }

    private void requestInterstitial_1() {
        mInterstitial_1 = new MoPubInterstitial(mActivity,"ccf6296fccc54758adf23f534175664b");
        mInterstitial_1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                if (interstitial.isReady()) {
                    isReadyAd_1 = true;
                }
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                isReadyAd_1 = false;
            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {
                if(!isReadyAd_2)
                    requestInterstitial_2();
            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                isReadyAd_1 = false;
                requestInterstitial_1();
            }
        });
        mInterstitial_1.load();
    }

    private void requestInterstitial_2() {
        mInterstitial_2 = new MoPubInterstitial(mActivity,"ccf6296fccc54758adf23f534175664b");
        mInterstitial_2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                if (interstitial.isReady()) {
                    isReadyAd_2 = true;
                }
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                isReadyAd_2 = false;
            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {
                if(!isReadyAd_1)
                    requestInterstitial_1();
            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                isReadyAd_2 = false;
                requestInterstitial_2();
            }
        });
        mInterstitial_2.load();
    }

    public MoPubInterstitial getAd() {
        if(isReadyAd_1)
            return mInterstitial_1;
        else
            return mInterstitial_2;
    }
}
