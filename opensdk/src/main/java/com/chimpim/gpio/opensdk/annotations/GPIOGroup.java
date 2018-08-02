package com.chimpim.gpio.opensdk.annotations;

import android.support.annotation.IntDef;

import com.chimpim.gpio.opensdk.GPIOConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(flag = true, value = {
        GPIOConstants.GPIO_GROUP_UART_1,
        GPIOConstants.GPIO_GROUP_UART_2,
        GPIOConstants.GPIO_GROUP_ONBOARD,
})
@Retention(RetentionPolicy.SOURCE)
public @interface GPIOGroup {
}
