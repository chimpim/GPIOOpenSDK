// IGPIOStateChnageCallback.aidl
package com.chimpim.gpio.opensdk;

// Declare any non-default types here with import statements

interface IGPIOStateChangeCallback2 {

    void call(int group, int pin, int stateChange);

}
