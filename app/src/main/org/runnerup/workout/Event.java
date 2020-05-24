

package org.runnerup.workout;

import org.runnerup.R;

/**
 * This is just constant
 */
public enum Event {

    STARTED(1, R.string.cue_started),
    PAUSED(2, R.string.cue_paused),
    STOPPED(3, R.string.cue_stopped),
    RESUMED(4, R.string.cue_resumed),
    COMPLETED(5, R.string.cue_completed);

    int value = 0;
    final int cueId;

    Event(int val, int cueId) {
        this.value = val;
        this.cueId = cueId;
    }

    /**
     * @return the eventValue
     */
    public int getValue() {
        return value;
    }

    public boolean equal(Event what) {
        return !(what == null || what.value != this.value);
    }

    public int getCueId() {
        return cueId;
    }
}
