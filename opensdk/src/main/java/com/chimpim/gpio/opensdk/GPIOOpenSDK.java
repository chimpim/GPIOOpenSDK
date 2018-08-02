package com.chimpim.gpio.opensdk;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public final class GPIOOpenSDK {
    private static final String TAG = "GPIOOpenSDK";
    private static final String GPIO_MANAGER_SERVICE_PACKAGE = "com.chimpim.gpio";
    private static final String GPIO_MANAGER_SERVICE_NAME = "com.chimpim.gpio.GPIOService";
    /**
     * GPIO服务就绪是广播
     */
    public static final String ACTION_GPIO_SERVICE_READY = "com.chimpim.gpio.action.ACTION_GPIO_SERVICE_READY";
    /**
     * GPIO服务销毁时广播
     */
    public static final String ACTION_GPIO_SERVICE_DESTROY = "com.chimpim.gpio.action.ACTION_GPIO_SERVICE_DESTROY";

    public static final int[] ALL_ONBOARD_GPIO_PIN = new int[]{
            GPIOConstants.GPIO_INOUT_PIN_0,
            GPIOConstants.GPIO_INOUT_PIN_1,
            GPIOConstants.GPIO_INOUT_PIN_2,
            GPIOConstants.GPIO_INOUT_PIN_3,
    };
    public static final int[] ALL_UART1_IN_PIN = new int[]{
            GPIOConstants.GPIO_IN_PIN_1,
            GPIOConstants.GPIO_IN_PIN_2,
            GPIOConstants.GPIO_IN_PIN_3,
            GPIOConstants.GPIO_IN_PIN_4,
            GPIOConstants.GPIO_IN_PIN_5,
    };
    public static final int[] ALL_UART2_IN_PIN = new int[]{
            GPIOConstants.GPIO_IN_PIN_1,
            GPIOConstants.GPIO_IN_PIN_2,
            GPIOConstants.GPIO_IN_PIN_3,
            GPIOConstants.GPIO_IN_PIN_4,
            GPIOConstants.GPIO_IN_PIN_5,
    };

    public static boolean isInstallService(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (PackageInfo pi : packageInfos) {
                if (GPIO_MANAGER_SERVICE_PACKAGE.equals(pi.packageName)) {
                    return true;
                }
            }
        }
        return false;

    }

    @Deprecated
    public static boolean isInstallGPIOApp(@NonNull Context context) {
        return isInstallService(context);
    }

    public static boolean isServiceRunning(@NonNull Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo service : runningServices) {
            if (GPIO_MANAGER_SERVICE_NAME.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean isGPIOServiceRunning(@NonNull Context context) {
        return isServiceRunning(context);
    }

    @Nullable
    public static ComponentName startService(@NonNull Context context) {
        final Intent service = new Intent();
        service.setClassName(GPIO_MANAGER_SERVICE_PACKAGE, GPIO_MANAGER_SERVICE_NAME);
        return context.startService(service);
    }

    public static void bindService(@NonNull Context context, @NonNull ServiceConnection conn) {
        final Intent service = new Intent();
        service.setClassName(GPIO_MANAGER_SERVICE_PACKAGE, GPIO_MANAGER_SERVICE_NAME);
        context.bindService(service, conn, Context.BIND_AUTO_CREATE);
    }

    public static void unbindService(@NonNull Context context, ServiceConnection conn) {
        if (conn != null) {
            context.unbindService(conn);
        }
    }
}
