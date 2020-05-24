

package org.runnerup.workout.feedback;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.runnerup.util.Formatter;
import org.runnerup.workout.Dimension;
import org.runnerup.workout.Feedback;
import org.runnerup.workout.Scope;
import org.runnerup.workout.Workout;

import java.util.HashMap;


public class CountdownFeedback extends Feedback {

    private Scope scope;
    private Dimension dimension;
    private TextView textView = null;
    private Formatter formatter;

    public CountdownFeedback(Scope s, Dimension d) {
        this.scope = s;
        this.dimension = d;
    }

    @Override
    public void onBind(Workout s, HashMap<String, Object> bindValues) {
        super.onBind(s, bindValues);
        if (bindValues.containsKey(Workout.KEY_FORMATTER))
            formatter = (Formatter) bindValues.get(Workout.KEY_FORMATTER);
        if (bindValues.containsKey(Workout.KEY_COUNTER_VIEW))
            textView = (TextView) bindValues.get(Workout.KEY_COUNTER_VIEW);
    }

    @Override
    public void onStart(Workout s) {
        if (textView != null) {
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEnd(Workout s) {
        if (textView != null) {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean equals(Feedback _other) {
        return _other instanceof CountdownFeedback;
    }

    @Override
    public void emit(Workout w, Context ctx) {
        if (textView == null)
            return;

        double remaining = w.getRemaining(scope, dimension);
        if (remaining > 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(formatter.formatRemaining(Formatter.Format.TXT_SHORT, dimension, remaining));
        }
        else {
            textView.setVisibility(View.INVISIBLE);
        }
    }
}
