// IGPIOManager.aidl
package com.chimpim.gpio.opensdk;

// Declare any non-default types here with import statements

import com.chimpim.gpio.opensdk.SparseIntArray;

import com.chimpim.gpio.opensdk.GPIOStateChangeParams;
import com.chimpim.gpio.opensdk.IGPIOStateChangeCallback;

import com.chimpim.gpio.opensdk.GPIOStateChangeParams2;
import com.chimpim.gpio.opensdk.IGPIOStateChangeCallback2;


interface IGPIOManager {
    //
    boolean setState(in int group, in int pin,in int state);
    //
    boolean setStates(in int group, in SparseIntArray states);
    //
    int getState(in int group, in int pin);
    //
    SparseIntArray getStates(in int group);
    //
    void registerGPIOStateChangeCallback(IGPIOStateChangeCallback callback, in GPIOStateChangeParams params);
    //
    void unregisterGPIOStateChangeCallback(IGPIOStateChangeCallback callback);
    //
    void registerGPIOStateChangeCallback2(IGPIOStateChangeCallback2 callback, in GPIOStateChangeParams2 params);
    //
    void unregisterGPIOStateChangeCallback2(IGPIOStateChangeCallback2 callback);

}
