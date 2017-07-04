// IMenManager.aidl
package com.liang.Model;

// Declare any non-default types here with import statements
import com.liang.Model.ContactMen;
import com.liang.Model.IMenAddListener;

interface IMenManager {
   List<ContactMen> getMenList();
   void addContactMen(in ContactMen men);
   void registerListener(IMenAddListener iMenAddListener);
   void unregisterListener(IMenAddListener iMenAddListener);
}
