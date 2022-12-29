/*
 * KnoxPatch
 * Copyright (C) 2022 BlackMesa123
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.mesalabs.knoxpatch.hooks;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SamsungHealthHooks implements IXposedHookLoadPackage {
    private final static String TAG = "SamsungHealthHooks";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("KnoxPatch: " + TAG + " handleLoadPackage: " + lpparam.packageName);

        Class<?> cls = XposedHelpers.findClass(
                "com.samsung.android.service.health.security.KnoxAdapter",
                lpparam.classLoader);

        /* Disable Knox support */
        XposedHelpers.findAndHookMethod(
                cls,
                "isKnoxAvailable", Context.class,
                XC_MethodReplacement.returnConstant(Boolean.FALSE));
        XposedHelpers.findAndHookMethod(
                cls,
                "isKnoxAvailableCore", Context.class,
                XC_MethodReplacement.returnConstant(Boolean.FALSE));

        /* Disable SAK support */
        XposedHelpers.findAndHookMethod(
                cls,
                "isAksSakMandatory",
                XC_MethodReplacement.returnConstant(Boolean.FALSE));
    }

}
