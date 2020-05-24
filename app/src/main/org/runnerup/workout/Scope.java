

package org.runnerup.workout;

import org.runnerup.R;

/**
 * This is just constant
 */
public enum Scope {

    ACTIVITY(1, R.string.cue_activity),
    STEP(2, R.string.cue_interval),
    LAP(3, R.string.cue_lap),
    CURRENT(4, R.string.cue_current);

    int value = 0;
    int cueId = 0;

    Scope(int val, int cueId) {
        this.value = val;
        this.cueId = cueId;
    }

    /**
     * @return the scopeValue
     */
    public int getValue() {
        return value;
    }

    public boolean equal(Scope what) {
        return !(what == null || what.value != this.value);
    }

    public int getCueId() {
        return cueId;
    }
}
