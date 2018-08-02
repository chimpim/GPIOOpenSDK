package com.chimpim.gpio.opensdk.annotations;

import android.support.annotation.IntDef;

import com.chimpim.gpio.opensdk.GPIOConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(flag = true, value = {
        GPIOConstants.GPIO_IN_PIN_FROM_HIGH_TO_LOW,
        GPIOConstants.GPIO_IN_PIN_FROM_LOW_TO_HIGH,
})
@Retention(RetentionPolicy.SOURCE)
public @interface GPIOInPinStateChange {
}
