

package org.runnerup.workout;

import org.runnerup.R;
import org.runnerup.common.util.Constants.DB.DIMENSION;

/**
 * This is just constant
 */
public enum Dimension {

    TIME(DIMENSION.TIME, R.string.time),
    DISTANCE(DIMENSION.DISTANCE, R.string.distance),
    SPEED(DIMENSION.SPEED, R.string.speed),
    PACE(DIMENSION.PACE, R.string.pace),
    HR(DIMENSION.HR, R.string.Heart_rate),
    HRZ(DIMENSION.HRZ, R.string.heart_rate_zone),
    CAD(DIMENSION.CAD, R.string.cadence),
    TEMPERATURE(DIMENSION.CAD, R.string.temperature),
    PRESSURE(DIMENSION.CAD, R.string.pressure);

    // TODO
    public static final boolean SPEED_CUE_ENABLED = true;

    int value = 0;
    int textId = 0;

    Dimension(int val, int textId) {
        this.value = val;
        this.textId = textId;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public int getTextId() {
        return textId;
    }

    public boolean equal(Dimension what) {
        return !(what == null || what.value != this.value);
    }

    public static Dimension valueOf(int val) {
        switch(val) {
            case -1:
                return null;
            case DIMENSION.TIME:
                return TIME;
            case DIMENSION.DISTANCE:
                return DISTANCE;
            case DIMENSION.SPEED:
                return SPEED;
            case DIMENSION.PACE:
                return PACE;
            case DIMENSION.HR:
                return HR;
            case DIMENSION.HRZ:
                return HRZ;
            case DIMENSION.CAD:
                return CAD;
            case DIMENSION.TEMPERATURE:
                return TEMPERATURE;
            case DIMENSION.PRESSURE:
                return PRESSURE;
            default:
                return null;
        }
    }
}
