

package org.runnerup.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;

import org.runnerup.R;


public class TextPreference extends android.preference.EditTextPreference {

    public TextPreference(Context context) {
        super(context);
        this.context = context;
    }

    public TextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    private final Context context;

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue,
                                     Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        super.setSummary(super.getPersistedString(""));
    }

    @Override
    protected void onDialogClosed(boolean ok) {
        super.onDialogClosed(ok);
        if (ok) {
            String val = super.getPersistedString("");
            if (TextUtils.isEmpty(val)) {
                //If empty, use the default value
                //This could be a default setting and should not be hardcoded in a widget
                //However, getting the default value in the xml seems hard and a similar
                //onPreferenceChange() in SettingsActivity is not much better
                Resources res = context.getResources();
                if (this.getKey().equals(res.getString(R.string.pref_mapbox_default_style))) {
                    val = res.getString(R.string.mapboxDefaultStyle);
                    super.setText(val);
                } else if (this.getKey().equals(res.getString(R.string.pref_path_simplification_tolerance))) {
                    val = res.getString(R.string.path_simplification_default_tolerance);
                    super.setText(val);
                }
            }
            super.setSummary(val);
        }
    }
}
