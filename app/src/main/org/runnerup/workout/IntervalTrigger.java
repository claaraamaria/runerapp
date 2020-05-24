

package org.runnerup.workout;


public class IntervalTrigger extends Trigger {

    Scope scope = Scope.ACTIVITY;
    Dimension dimension = Dimension.TIME;

    double first = 120;
    double interval = 120;

    private double next = 0;

    @Override
    public boolean onTick(Workout w) {
        if (next != 0) {
            double now = w.get(scope, dimension);
            if (now >= next) {
                fire(w);
                scheduleNext(w, now);
            }
        }
        return false;
    }

    private void scheduleNext(Workout w, double now) {
        if (interval == 0) {
            // last occurrence (maybe first)
            next = 0;
        } else {
            while (next <= now) {
                next += interval;
            }
            //int count = 0; //endless
            //if (count != 0 && (next > (first + interval * (count - 1)))) {
            //    // no more occurrences
            //    next = 0;
            //}
        }
        if (next == 0) {
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
            next = first;
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

    @Override
    public String toString() {
        return "[ IntervalTrigger: " + this.scope + " " + this.dimension + " first: " + first
                + " interval: " + interval + " ]";
    }
}
