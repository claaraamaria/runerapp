

package org.runnerup.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

import org.runnerup.R;
import org.runnerup.util.GoogleApiHelper;


public class AboutPreference extends DialogPreference {

    public AboutPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AboutPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            try {
                // Use the play application id also for debug (not this.getContext().getPackageName())
                String applicationId = "org.runnerup";
                Uri uri = Uri.parse("market://details?id=" + applicationId);
                this.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        WebView wv = (WebView) view.findViewById(R.id.web_view1);
        wv.loadUrl("file:///android_asset/about.html");
    }

    private void init(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.setDialogTitle(context.getString(R.string.About_RunnerUp) + " v" + pInfo.versionName);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setNegativeButtonText(context.getString(R.string.OK));
        if (GoogleApiHelper.isGooglePlayServicesAvailable(context)) {
            setPositiveButtonText(context.getString(R.string.Rate_RunnerUp));
        } else {
            setPositiveButtonText(null);
        }
        setDialogLayoutResource(R.layout.whatsnew);
    }
}
