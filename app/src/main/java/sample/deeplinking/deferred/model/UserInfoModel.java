package sample.deeplinking.deferred.model;


import android.support.annotation.StringRes;

import sample.deeplinking.deferred.deferreddeeplink.R;

public enum UserInfoModel {
    BLACK(R.string.url_black),
    BLUE(R.string.url_blue),
    DEFAULT(R.string.url_default),
    GREEN(R.string.url_green),
    ORANGE(R.string.url_orange),
    RED(R.string.url_red),
    YELLOW(R.string.url_yellow);
    private int url;

    UserInfoModel(@StringRes int url) {
        this.url = url;
    }

    public int getUrl() {
        return url;
    }
}
