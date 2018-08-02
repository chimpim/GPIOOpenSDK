package com.chimpim.gpio.opensdk;

import android.os.Parcel;
import android.os.Parcelable;

public class SparseIntArray implements Cloneable, Parcelable {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private int[] mKeys;
    private int[] mValues;
    private int mSize;

    public SparseIntArray() {
        this(10);
    }

    public static final SparseIntArray EMPTY = new SparseIntArray(0);

    public SparseIntArray(int initialCapacity) {
        if (initialCapacity == 0) {
            this.mKeys = EMPTY_INT_ARRAY;
            this.mValues = EMPTY_INT_ARRAY;
        } else {
            this.mKeys = new int[initialCapacity];
            this.mValues = new int[this.mKeys.length];
        }

        this.mSize = 0;
    }

    public static int growSize(int currentSize) {
        return currentSize <= 4 ? 8 : currentSize + (currentSize >> 1);
    }

    @Override
    public SparseIntArray clone() {
        SparseIntArray copy = null;
        try {
            copy = (SparseIntArray) super.clone();
            copy.mKeys = this.mKeys.clone();
            copy.mValues = this.mValues.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return copy;
    }

    public int get(int key) {
        return this.get(key, 0);
    }

    public int get(int key, int valueIfKeyNotFound) {
        int var3 = this.binarySearch(this.mKeys, this.mSize, key);
        return var3 < 0 ? valueIfKeyNotFound : this.mValues[var3];
    }

    public void delete(int key) {
        int var2 = this.binarySearch(this.mKeys, this.mSize, key);
        if (var2 >= 0) {
            this.removeAt(var2);
        }

    }

    public void removeAt(int index) {
        System.arraycopy(this.mKeys, index + 1, this.mKeys, index, this.mSize - (index + 1));
        System.arraycopy(this.mValues, index + 1, this.mValues, index, this.mSize - (index + 1));
        --this.mSize;
    }

    public void put(int key, int value) {
        int var3 = this.binarySearch(this.mKeys, this.mSize, key);
        if (var3 >= 0) {
            this.mValues[var3] = value;
        } else {
            var3 = ~var3;
            this.mKeys = this.insertElementIntoIntArray(this.mKeys, this.mSize, var3, key);
            this.mValues = this.insertElementIntoIntArray(this.mValues, this.mSize, var3, value);
            ++this.mSize;
        }

    }

    public int size() {
        return this.mSize;
    }

    public int keyAt(int index) {
        return this.mKeys[index];
    }

    public int valueAt(int index) {
        return this.mValues[index];
    }

    public int indexOfKey(int key) {
        return this.binarySearch(this.mKeys, this.mSize, key);
    }

    public boolean containsKey(int key) {
        return this.indexOfKey(key) >= 0;
    }

    public int indexOfValue(int value) {
        for (int var2 = 0; var2 < this.mSize; ++var2) {
            if (this.mValues[var2] == value) {
                return var2;
            }
        }

        return -1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public void append(int key, int value) {
        if (this.mSize != 0 && key <= this.mKeys[this.mSize - 1]) {
            this.put(key, value);
        } else {
            this.mKeys = this.appendElementIntoIntArray(this.mKeys, this.mSize, key);
            this.mValues = this.appendElementIntoIntArray(this.mValues, this.mSize, value);
            ++this.mSize;
        }
    }

    private int binarySearch(int[] array, int size, int value) {
        int var4 = 0;
        int var5 = size - 1;

        while (var4 <= var5) {
            int var6 = var4 + var5 >>> 1;
            int var7 = array[var6];
            if (var7 < value) {
                var4 = var6 + 1;
            } else {
                if (var7 <= value) {
                    return var6;
                }

                var5 = var6 - 1;
            }
        }

        return ~var4;
    }

    private int[] appendElementIntoIntArray(int[] array, int currentSize, int element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + array.length + " currentSize: " + currentSize);
        } else {
            if (currentSize + 1 > array.length) {
                int[] var4 = new int[growSize(currentSize)];
                System.arraycopy(array, 0, var4, 0, currentSize);
                array = var4;
            }

            array[currentSize] = element;
            return array;
        }
    }

    private int[] insertElementIntoIntArray(int[] array, int currentSize, int index, int element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + array.length + " currentSize: " + currentSize);
        } else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        } else {
            int[] var5 = new int[growSize(currentSize)];
            System.arraycopy(array, 0, var5, 0, index);
            var5[index] = element;
            System.arraycopy(array, index, var5, index + 1, array.length - index);
            return var5;
        }
    }

    @Override
    public String toString() {
        if (size() <= 0) {
            return "{}";
        }

        StringBuilder buffer = new StringBuilder(mSize * 28);
        buffer.append('{');
        for (int i = 0; i < mSize; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            int key = keyAt(i);
            buffer.append(key);
            buffer.append('=');
            int value = valueAt(i);
            buffer.append(value);
        }
        buffer.append('}');
        return buffer.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.mKeys);
        dest.writeIntArray(this.mValues);
        dest.writeInt(this.mSize);
    }

    protected SparseIntArray(Parcel in) {
        this.mKeys = in.createIntArray();
        this.mValues = in.createIntArray();
        this.mSize = in.readInt();
    }

    public static final Creator<SparseIntArray> CREATOR = new Creator<SparseIntArray>() {
        @Override
        public SparseIntArray createFromParcel(Parcel source) {
            return new SparseIntArray(source);
        }

        @Override
        public SparseIntArray[] newArray(int size) {
            return new SparseIntArray[size];
        }
    };
}
