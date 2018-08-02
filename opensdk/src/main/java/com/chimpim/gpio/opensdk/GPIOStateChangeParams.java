package com.chimpim.gpio.opensdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.chimpim.gpio.opensdk.annotations.GPIOGroup;

import java.util.Arrays;

public class GPIOStateChangeParams implements Parcelable {
    public final int group;
    public final int[] pins;

    /**
     * 构造方法
     *
     * @param group 分组
     * @param pins  管脚
     */
    public GPIOStateChangeParams(@GPIOGroup int group, @NonNull int[] pins) {
        for (int pin : pins) {
            if (!GPIOChecker.checkGPIOGroupInPin(group, pin)) {
                throw new IllegalArgumentException("Not find group or pin");
            }
        }
        this.group = group;
        this.pins = pins;
    }

    @Override
    public String toString() {
        return "GPIOStateChangeParams{" +
                "group=" + group +
                ", pins=" + Arrays.toString(pins) +
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
    }

    protected GPIOStateChangeParams(Parcel in) {
        this.group = in.readInt();
        this.pins = in.createIntArray();
    }

    public static final Creator<GPIOStateChangeParams> CREATOR = new Creator<GPIOStateChangeParams>() {
        @Override
        public GPIOStateChangeParams createFromParcel(Parcel source) {
            return new GPIOStateChangeParams(source);
        }

        @Override
        public GPIOStateChangeParams[] newArray(int size) {
            return new GPIOStateChangeParams[size];
        }
    };
}
