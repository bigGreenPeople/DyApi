package com.ss.android.common.applog;

public class UserInfo {
    public static native void getPackage(String str);

    public static native String getUserInfo(int i, String str, String[] strArr);

    public static native int initUser(String str);

    public static native void setAppId(int i);

}
