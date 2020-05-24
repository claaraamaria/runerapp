

package org.runnerup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.runnerup.util.Formatter;


public class DistancePicker extends LinearLayout {

    private long baseUnitMeters;

    private final NumberPicker unitMeters; // e.g km or mi
    private final TextView unitString;
    private final NumberPicker meters;

    public DistancePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        unitMeters = new NumberPicker(context, attrs);
        LinearLayout unitStringLayout = new LinearLayout(context, attrs);
        unitStringLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        unitStringLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT));
        unitString = new TextView(context, attrs);
        unitString.setTextSize(25);
        unitStringLayout.addView(unitString);
        meters = new NumberPicker(context, attrs);

        unitMeters.setOrientation(VERTICAL);
        meters.setDigits(4);
        meters.setOrientation(VERTICAL);

        setOrientation(HORIZONTAL);
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        addView(unitMeters);
        addView(unitStringLayout);
        addView(meters);

        {
            Formatter f = new Formatter(context);
            setBaseUint((long) f.getUnitMeters(), f.getUnitString());
        }
    }

    private void setBaseUint(long baseUnit, String baseString) {
        baseUnitMeters = baseUnit;
        unitString.setText(baseString);
        meters.setRange(0, (int) baseUnitMeters - 1, true);
    }

    public long getDistance() {
        long ret = 0;
        ret += meters.getValue();
        ret += (long) unitMeters.getValue() * baseUnitMeters;
        return ret;
    }

    public void setDistance(long s) {
        long h = s / baseUnitMeters;
        s -= h * baseUnitMeters;
        unitMeters.setValue((int) h);
        meters.setValue((int) s);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        unitMeters.setEnabled(enabled);
        meters.setEnabled(enabled);
    }
}
