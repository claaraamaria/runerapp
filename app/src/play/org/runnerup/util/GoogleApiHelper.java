

package org.runnerup.util;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleApiHelper {
    public static boolean isGooglePlayServicesAvailable(Context context) {
        boolean res = false;

        try {
            res = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS;
        } catch (Exception e) {}

        return res;
    }
}
