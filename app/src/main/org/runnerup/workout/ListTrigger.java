

package org.runnerup.workout;

import java.util.ArrayList;


public class ListTrigger extends Trigger {

    private Scope scope;
    private Dimension dimension;

    private int pos;
    private ArrayList<Double> triggerTimes;

    ListTrigger(Dimension d, Scope s, ArrayList<Double> triggerTimes){
        this.dimension = d;
        this.scope = s;

        if (triggerTimes == null) {
            triggerTimes = new ArrayList<>();
        }
        this.triggerTimes = triggerTimes;
        pos = 0;
    }

    @Override
    public boolean onTick(Workout w) {
        // add a bit of margin, NOTE: less than 0.5s
        // For distance 4:00 /km is just over 4 m/s
        final double margin = dimension == Dimension.TIME ? 0.4d : 2d;

        double now = w.getRemaining(scope, dimension) - margin;
        if (pos < triggerTimes.size() && now <= triggerTimes.get(pos)) {
            scheduleNext(w, now);
            fire(w);
        }
        return false;
    }

    private void scheduleNext(Workout w, double now) {
        while (pos < triggerTimes.size() && now <= triggerTimes.get(pos)) {
            pos++;
        }

        if (pos >= triggerTimes.size()) {
            for (Feedback f : triggerAction) {
                f.onEnd(w);
            }
        }
    }

    @Override
    public void onRepeat(int current, int limit) {
    }

    @Override
    public void onStart(Scope what, Workout s) {
        if (this.scope == what) {
            pos = 0;
            for (Feedback f : triggerAction) {
                f.onStart(s);
            }
        }
    }

    @Override
    public void onPause(Workout s) {
    }

    @Override
    public void onStop(Workout s) {
    }

    @Override
    public void onResume(Workout s) {
    }

    @Override
    public void onComplete(Scope what, Workout s) {
    }
}
