package com.chimpim.gpio.opensdk.annotations;

import android.support.annotation.IntDef;

import com.chimpim.gpio.opensdk.GPIOConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * GPIO状态枚举
 */
@IntDef(flag = true, value = {
        GPIOConstants.GPIO_STATE_UNKNOWN,
        GPIOConstants.GPIO_STATE_LOW,
        GPIOConstants.GPIO_STATE_HIGH,
})
@Retention(RetentionPolicy.SOURCE)
public @interface GPIOState {
}
