package com.shark.dyapi.utils;

import android.util.Log;

import com.ss.android.common.applog.UserInfo;

import java.util.HashMap;
import java.util.Map.Entry;

public class DouyinUtils {
    private final static String GETDATA_JSON_URL = "https://aweme-eagle.snssdk.com/aweme/v1/feed/";

    public static String getEncryptUrl() {
        String url = null;

        //得到时间戳
        int time = (int) (System.currentTimeMillis() / 1000);

        try {
            //构建参数map
            HashMap<String, String> paramsMap = getCommonParams();
//            map中包含了key和val，所以数组大小是*2
            String[] paramsAry = new String[paramsMap.size() * 2];
            int i = 0;
            //参数存放到数组中
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                paramsAry[i] = entry.getKey();
                i++;
                paramsAry[i] = entry.getValue();
                i++;
            }
            //下面的参数就不在getUserInfo第三个参数里了所以单独拿了出来
            paramsMap.put("count", "5");
            paramsMap.put("type", "0");
            paramsMap.put("ts", "" + time);

            //分页参数
            paramsMap.put("max_cursor", "0");
            paramsMap.put("min_cursor", "0");
            //所有参数的字符串
            StringBuilder paramsSb = new StringBuilder();
            for (String key : paramsMap.keySet()) {
                paramsSb.append(key + "=" + paramsMap.get(key) + "&");
            }
            //拼接处最后的url
            String urlStr = GETDATA_JSON_URL + "?" + paramsSb.toString();
            //去掉最后一个&
            if (urlStr.endsWith("&")) {
                urlStr = urlStr.substring(0, urlStr.length() - 1);
            }

            Log.i("SharkChilli", "get data url:" + urlStr + ",len:" + paramsAry.length);
            //调用so的native方法得到sign
            String as_cp = UserInfo.getUserInfo(time, urlStr, paramsAry);
            Log.i("SharkChilli", "get as_cp:" + as_cp);
            //对半分得到as和cp
            String asStr = as_cp.substring(0, as_cp.length() / 2);
            String cpStr = as_cp.substring(as_cp.length() / 2, as_cp.length());

            url = urlStr + "&as=" + asStr + "&cp=" + cpStr;
            Log.i("SharkChilli", "url:" + url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


//    arg0:1602928306
//    arg1:https://aweme-eagle.snssdk.com/aweme/v1/feed/?type=0&max_cursor=0&min_cursor=0&count=5&iid=0&device_id=0&ac=wifi&channel=wandoujia&aid=1128&app_name=aweme&version_code=152&version_name=1.5.2&device_platform=android&ssmix=a&device_type=AOSP+on+HammerHead&device_brand=Android&os_api=19&os_version=4.4.4&uuid=352136068461108&openudid=865d910c9e66d273&manifest_version_code=152&resolution=1080*1776&dpi=480&update_version_code=1522&ts=1602928306
//    arg2:iid:0:device_id:0:ac:wifi:channel:wandoujia:aid:1128:app_name:aweme:version_code:152:version_name:1.5.2:device_platform:android:ssmix:a:device_type:AOSP on HammerHead:device_brand:Android:os_api:19:os_version:4.4.4:uuid:352136068461108:openudid:865d910c9e66d273:manifest_version_code:152:resolution:1080*1776:dpi:480:update_version_code:1522:
//    result:a1856b08026b4f3ecab7b8fd512eae8befe1


    public static HashMap<String, String> getCommonParams() {
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("iid", "0");
        params.put("device_id", "0");
        params.put("ac", "wifi");
        params.put("channel", "wandoujia");
        params.put("aid", "1128");
        params.put("app_name", "aweme");
        params.put("version_code", "152");
        params.put("version_name", "1.5.2");
        params.put("device_platform", "android");
        params.put("ssmix", "a");
        params.put("device_type", "AOSP+on+HammerHead");
        params.put("device_brand", "Android");
        params.put("os_api", "19");
        params.put("os_version", "4.4.4");
        params.put("uuid", "352136068461108");
        params.put("openudid", "865d910c9e66d273");
        params.put("manifest_version_code", "152");
        params.put("resolution", "1080*1776");
        params.put("dpi", "480");
        params.put("update_version_code", "1522");
        return params;
    }
}
