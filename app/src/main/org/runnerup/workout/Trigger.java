

package org.runnerup.workout;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Trigger implements TickComponent {

    ArrayList<Feedback> triggerAction = new ArrayList<>();
    final ArrayList<TriggerSuppression> triggerSuppression = new ArrayList<>();

    @Override
    public void onInit(Workout s) {
        for (Feedback f : triggerAction) {
            f.onInit(s);
        }
    }

    @Override
    public void onBind(Workout s, HashMap<String, Object> bindValues) {
        for (Feedback f : triggerAction) {
            f.onBind(s, bindValues);
        }
    }

    @Override
    public void onEnd(Workout s) {
        for (Feedback f : triggerAction) {
            f.onEnd(s);
        }
    }

    void fire(Workout w) {
        for (TriggerSuppression s : triggerSuppression) {
            if (s.suppress(this, w)) {
                Log.e(getClass().getName(), "trigger: " + this + "suppressed by: " + s);
                return;
            }
        }
        for (Feedback f : triggerAction) {
            w.addFeedback(f);
        }
    }
}
