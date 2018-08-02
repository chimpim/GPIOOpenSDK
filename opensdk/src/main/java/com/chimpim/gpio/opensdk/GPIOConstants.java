package com.chimpim.gpio.opensdk;

public class GPIOConstants {
    /**
     * 高电平
     */
    public static final int GPIO_STATE_LOW = 0;
    /**
     * 低电平
     */
    public static final int GPIO_STATE_HIGH = 1;
    /**
     * 未知电平
     */
    public static final int GPIO_STATE_UNKNOWN = -1;


    /**
     * UART扩展GPIO
     */
    public static final int GPIO_GROUP_UART_1 = 1;
    public static final int GPIO_GROUP_UART_2 = 2;

    public static final int GPIO_IN_PIN_1 = 1;
    public static final int GPIO_IN_PIN_2 = 2;
    public static final int GPIO_IN_PIN_3 = 3;
    public static final int GPIO_IN_PIN_4 = 4;
    public static final int GPIO_IN_PIN_5 = 5;

    public static final int GPIO_OUT_PIN_1 = 1;
    public static final int GPIO_OUT_PIN_2 = 2;
    public static final int GPIO_OUT_PIN_3 = 3;

    /**
     * 板载GPIO
     */
    public static final int GPIO_GROUP_ONBOARD = 3;

    public static final int GPIO_INOUT_PIN_0 = 0;
    public static final int GPIO_INOUT_PIN_1 = 1;
    public static final int GPIO_INOUT_PIN_2 = 2;
    public static final int GPIO_INOUT_PIN_3 = 3;

    public static final int GPIO_IN_PIN_FROM_LOW_TO_HIGH = 1;
    public static final int GPIO_IN_PIN_FROM_HIGH_TO_LOW = 2;


}
