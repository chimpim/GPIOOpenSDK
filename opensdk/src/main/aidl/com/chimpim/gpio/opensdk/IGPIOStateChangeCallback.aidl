// IGPIOStateListener.aidl
package com.chimpim.gpio.opensdk;

// Declare any non-default types here with import statements

interface IGPIOStateChangeCallback {

    void call(int group, int pin, int state);

}
