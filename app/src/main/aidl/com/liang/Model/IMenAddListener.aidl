// IMenListChangListener.aidl
package com.liang.Model;

// Declare any non-default types here with import statements
import com.liang.Model.ContactMen;

interface IMenAddListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  void onNewMenAdded(in ContactMen men);
}
