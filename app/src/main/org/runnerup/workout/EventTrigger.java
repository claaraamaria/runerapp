

package org.runnerup.workout;


public class EventTrigger extends Trigger {

    Scope scope = Scope.STEP;
    Event event = Event.STARTED;
    private int counter = 0;
    int skipCounter = 0;
    int maxCounter = Integer.MAX_VALUE;

    @Override
    public void onInit(Workout s) {
        counter = 0;
    }

    @Override
    public boolean onTick(Workout w) {
        return false;
    }

    @Override
    void fire(Workout s) {
        if (counter >= skipCounter && (counter < maxCounter)) {
            super.fire(s);
        }
        counter++;
    }

    @Override
    public void onRepeat(int current, int limit) {
        counter = 0;
    }

    @Override
    public void onStart(Scope what, Workout s) {
        if (this.scope == what && this.event == Event.STARTED) {
            fire(s);
        }
    }

    @Override
    public void onPause(Workout s) {
        if (this.event == Event.PAUSED) {
            fire(s);
        }
    }

    @Override
    public void onStop(Workout s) {
        if (this.event == Event.STOPPED) {
            fire(s);
        }
    }

    @Override
    public void onResume(Workout s) {
        if (this.event == Event.RESUMED) {
            fire(s);
        }
    }

    @Override
    public void onComplete(Scope what, Workout s) {
        if (this.scope == what && this.event == Event.COMPLETED) {
            fire(s);
        }
    }

    @Override
    public String toString() {
        return "[ EventTrigger: " + this.scope + " " + this.event + " skipCounter: " + skipCounter
                + " ]";
    }
}
