package com.liang.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Liang on 2017/3/3 0003.
 */

public class MessengerService extends Service {

    private static final String TAG="MessengerService";

    private static class messengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstents.MSG_FROM_CLIENT:
                    Log.d(TAG, "handleMessage: "+msg.getData().getString("client"));
                    Messenger serverMsger=msg.replyTo;
                    Message serMsg=Message.obtain(null,MyConstents.MSG_FROM_SERVER);
                    Bundle bud = new Bundle();
                    bud.putString("server","我已经收到，稍后我会作详细处理");
                    serMsg.setData(bud);
                    try {
                        serverMsger.send(serMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case MyConstents.MSG_FROM_SERVER:
                    Log.d(TAG, "handleMessage: "+msg.getData());
                    break;
                case MyConstents.MSG_FROM_COMMON:
                    Log.d(TAG, "handleMessage: "+msg.getData());
                    break;

            }
        }
    }

    private final Messenger mMessenger=new Messenger(new messengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
