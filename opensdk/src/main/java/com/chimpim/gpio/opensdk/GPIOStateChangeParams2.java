package com.chimpim.gpio.opensdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.chimpim.gpio.opensdk.annotations.GPIOGroup;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GPIOStateChangeParams2 implements Parcelable {
    public final int group;
    public final int[] pins;
    public final long[] durations;

    /**
     * 构造方法
     *
     * @param group     分组
     * @param pins      管脚
     * @param durations 单位毫秒
     */
    public GPIOStateChangeParams2(@GPIOGroup int group, @NonNull int[] pins, @NonNull long[] durations) {
        if (pins.length != durations.length) {
            throw new IllegalArgumentException("pin.length != duration.length");
        }
        for (int pin : pins) {
            if (!GPIOChecker.checkGPIOGroupInPin(group, pin)) {
                throw new IllegalArgumentException("Not find group or pin");
            }
        }
        this.group = group;
        this.pins = pins;
        this.durations = new long[durations.length];
        for (int i = 0; i < durations.length; i++) {
            this.durations[i] = TimeUnit.MILLISECONDS.toNanos(durations[i]);
        }
    }

    @Override
    public String toString() {
        return "GPIOStateChangeParams2{" +
                "group=" + group +
                ", pins=" + Arrays.toString(pins) +
                ", durations=" + Arrays.toString(durations) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.group);
        dest.writeIntArray(this.pins);
        dest.writeLongArray(this.durations);
    }

    protected GPIOStateChangeParams2(Parcel in) {
        this.group = in.readInt();
        this.pins = in.createIntArray();
        this.durations = in.createLongArray();
    }

    public static final Creator<GPIOStateChangeParams2> CREATOR = new Creator<GPIOStateChangeParams2>() {
        @Override
        public GPIOStateChangeParams2 createFromParcel(Parcel source) {
            return new GPIOStateChangeParams2(source);
        }

        @Override
        public GPIOStateChangeParams2[] newArray(int size) {
            return new GPIOStateChangeParams2[size];
        }
    };
}
