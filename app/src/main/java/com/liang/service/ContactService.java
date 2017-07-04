package com.liang.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.liang.Model.ContactMen;
import com.liang.Model.IMenAddListener;
import com.liang.Model.IMenManager;
import com.liang.controlHelper.ContactHelper;
import com.liang.phonenum.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang on 2017/3/2 0002.
 */

public class ContactService extends Service {

    private static final String TAG="ContactService";

    private DatabaseHelper databaseHelper;

    ContactHelper menHelper;

    RemoteCallbackList<IMenAddListener> listenersList=new RemoteCallbackList<>();

    private List<ContactMen> menList=new ArrayList<>();

    private Binder mBinder= new IMenManager.Stub() {
        @Override
        public List<ContactMen> getMenList() throws RemoteException {
            menList.clear();
            menList.addAll(menHelper.getDataList());
            return menList;
        }

        @Override
        public void addContactMen(ContactMen men) throws RemoteException {
            try {
                menHelper.AddContact(men);
                menList.add(men);
                final int N=listenersList.getRegisteredCallbackCount();
                for (int i = 0; i < N; i++) {
                    IMenAddListener ll=listenersList.getBroadcastItem(i);
                    if (ll!=null){
                        ll.onNewMenAdded(men);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void registerListener(IMenAddListener iMenAddListener) throws RemoteException {
            listenersList.register(iMenAddListener);
        }

        @Override
        public void unregisterListener(IMenAddListener iMenAddListener) throws RemoteException {
            listenersList.unregister(iMenAddListener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
          menHelper = ContactHelper
                .getContactHelper(this);
        menList.addAll(menHelper.getDataList());

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
