package com.liang.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liang.Model.ContactMen;
import com.liang.controlHelper.ContactHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang on 2017/3/3 0003.
 */

public class MyContactProvider extends ContentProvider {

    private static final String TAG="BookProvider";

    ContactHelper menHelper;

    private List<ContactMen> menList=new ArrayList<>();

    public static final String AUTHORITY="com.liang.provider.baiContactProvider";

    public static final Uri CONTACTMEN_URI=Uri.parse("content://"+AUTHORITY+"/contact");

    public static final Uri CALLLOG_URI=Uri.parse("content://"+AUTHORITY+"/calllog");

    public static final int CONTACT_CODE=0;

    public static final int CALLLOG_CODE=1;

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,"contact",CONTACT_CODE);
        sUriMatcher.addURI(AUTHORITY,"calllog",CALLLOG_CODE);
    }

    private void getDataSource(Uri uri){
        String tableName=null;
        switch (sUriMatcher.match(uri))
        {
            case CONTACT_CODE:
                Log.d(TAG, "getDataSource: 获取联系人列表");
                break;
            case CALLLOG_CODE:
                Log.d(TAG, "getDataSource: 获取通话记录");
                break;
        }
    }



    @Override
    public boolean onCreate() {
//        menHelper = ContactHelper
//                .getContactHelper(MyApplication.getmContent());
//        menList.addAll(menHelper.getDataList());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
