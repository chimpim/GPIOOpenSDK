package com.chimpim.gpio.opensdk.annotations;


import android.annotation.SuppressLint;
import android.support.annotation.IntDef;

import com.chimpim.gpio.opensdk.GPIOConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint("UniqueConstants")
@IntDef(flag = true, value = {
        // UART扩展GPIO
        GPIOConstants.GPIO_OUT_PIN_1,
        GPIOConstants.GPIO_OUT_PIN_2,
        GPIOConstants.GPIO_OUT_PIN_3,
        // 板载GPIO
        GPIOConstants.GPIO_INOUT_PIN_0,
        GPIOConstants.GPIO_INOUT_PIN_1,
        GPIOConstants.GPIO_INOUT_PIN_2,
        GPIOConstants.GPIO_INOUT_PIN_3,
})
@Retention(RetentionPolicy.SOURCE)
public @interface GPIOOutPin {
}
