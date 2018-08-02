package com.chimpim.gpio.opensdk;

import com.chimpim.gpio.opensdk.annotations.GPIOGroup;
import com.chimpim.gpio.opensdk.annotations.GPIOInPin;
import com.chimpim.gpio.opensdk.annotations.GPIOOutPin;
import com.chimpim.gpio.opensdk.annotations.GPIOState;

public final class GPIOChecker {

    public static boolean checkGPIOGroup(@GPIOGroup int group) {
        return group == GPIOConstants.GPIO_GROUP_ONBOARD
                || group == GPIOConstants.GPIO_GROUP_UART_1
                || group == GPIOConstants.GPIO_GROUP_UART_2;
    }

    public static boolean checkGPIOGroupInPin(@GPIOGroup int group, @GPIOInPin int inPin) {
        switch (group) {
            case GPIOConstants.GPIO_GROUP_ONBOARD:
                return inPin == GPIOConstants.GPIO_INOUT_PIN_0 || inPin == GPIOConstants.GPIO_INOUT_PIN_1
                        || inPin == GPIOConstants.GPIO_INOUT_PIN_2 || inPin == GPIOConstants.GPIO_INOUT_PIN_3;
            case GPIOConstants.GPIO_GROUP_UART_1:
            case GPIOConstants.GPIO_GROUP_UART_2:
                return inPin == GPIOConstants.GPIO_IN_PIN_1 || inPin == GPIOConstants.GPIO_IN_PIN_2
                        || inPin == GPIOConstants.GPIO_IN_PIN_3 || inPin == GPIOConstants.GPIO_IN_PIN_4
                        || inPin == GPIOConstants.GPIO_IN_PIN_5;
        }
        return false;
    }

    public static boolean checkGPIOGroupOutPin(@GPIOGroup int group, @GPIOOutPin int outPin) {
        switch (group) {
            case GPIOConstants.GPIO_GROUP_ONBOARD:
                return outPin == GPIOConstants.GPIO_INOUT_PIN_0 || outPin == GPIOConstants.GPIO_INOUT_PIN_1
                        || outPin == GPIOConstants.GPIO_INOUT_PIN_2 || outPin == GPIOConstants.GPIO_INOUT_PIN_3;
            case GPIOConstants.GPIO_GROUP_UART_1:
            case GPIOConstants.GPIO_GROUP_UART_2:
                return outPin == GPIOConstants.GPIO_OUT_PIN_1 || outPin == GPIOConstants.GPIO_OUT_PIN_2
                        || outPin == GPIOConstants.GPIO_OUT_PIN_3;
        }
        return false;
    }

    @GPIOState
    public static int checkGPIOState(Integer state) {
        return checkGPIOState(state, GPIOConstants.GPIO_STATE_LOW);
    }

    @GPIOState
    public static int checkGPIOState(int state, @GPIOState int defState) {
        if (state == GPIOConstants.GPIO_STATE_HIGH) return GPIOConstants.GPIO_STATE_HIGH;
        else if (state == GPIOConstants.GPIO_STATE_LOW) return GPIOConstants.GPIO_STATE_LOW;
        else return defState;
    }
}
