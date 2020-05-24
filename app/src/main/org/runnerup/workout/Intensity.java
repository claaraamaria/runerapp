

package org.runnerup.workout;

import org.runnerup.R;
import org.runnerup.common.util.Constants.DB.INTENSITY;

public enum Intensity {

    /**
     * Running
     */
    ACTIVE(INTENSITY.ACTIVE, R.string.active),

    /**
	 *
	 */
    RESTING(INTENSITY.RESTING, R.string.rest),

    /**
     * Warm up
     */
    WARMUP(INTENSITY.WARMUP, R.string.warm_up, R.string.cue_warmup),

    /**
     * Cool down
     */
    COOLDOWN(INTENSITY.COOLDOWN, R.string.cool_down, R.string.cue_cooldown),

    /**
     * Loop (for workout construction/plans)
     */
    REPEAT(INTENSITY.REPEAT, R.string.repeat),

    /**
     *
     */
   RECOVERY(INTENSITY.RECOVERY, R.string.recovery, R.string.recovery);

    final int value;
    final int textId;
    final int cueId;

    Intensity(int val, int textId) {
        this.value = val;
        this.textId = textId;
        this.cueId = textId;
    }

    Intensity(int val, int textId, int cueId) {
        this.value = val;
        this.textId = textId;
        this.cueId = cueId;
    }

    public int getValue() {
        return value;
    }

    public int getTextId() {
        return textId;
    }

    public int getCueId() {
        return cueId;
    }

    public static Intensity valueOf(int valueInt) {
        switch (valueInt) {
            case INTENSITY.ACTIVE:
                return ACTIVE;
            case INTENSITY.RESTING:
                return RESTING;
            case INTENSITY.WARMUP:
                return WARMUP;
            case INTENSITY.COOLDOWN:
                return COOLDOWN;
            case INTENSITY.REPEAT:
                return REPEAT;
            case INTENSITY.RECOVERY:
                return RECOVERY;
            default:
            case -1:
                return null;
        }
    }
}
