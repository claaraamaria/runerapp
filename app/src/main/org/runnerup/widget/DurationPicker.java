

package org.runnerup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class DurationPicker extends LinearLayout {

    private final NumberPicker hours;
    private final NumberPicker minutes;
    private final NumberPicker seconds;

    public DurationPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        hours = new NumberPicker(context, attrs);
        minutes = new NumberPicker(context, attrs);
        seconds = new NumberPicker(context, attrs);

        hours.setOrientation(VERTICAL);
        minutes.setOrientation(VERTICAL);
        seconds.setOrientation(VERTICAL);

        setOrientation(HORIZONTAL);
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        addView(hours);
        addView(minutes);
        addView(seconds);
    }

    public long getEpochTime() {
        long ret = 0;
        ret += seconds.getValue();
        ret += (long) minutes.getValue() * 60;
        ret += (long) hours.getValue() * 60 * 60;
        return ret;
    }

    public void setEpochTime(long s) {
        long h = s / 3600;
        s -= h * 3600;
        long m = s / 60;
        s -= m * 60;
        hours.setValue((int) h);
        minutes.setValue((int) m);
        seconds.setValue((int) s);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        hours.setEnabled(enabled);
        minutes.setEnabled(enabled);
        seconds.setEnabled(enabled);
    }
}
