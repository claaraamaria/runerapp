package org.runnerup.workout;

import android.content.res.Resources;

import org.runnerup.R;
import org.runnerup.common.util.Constants.DB;

public enum Sport {
    RUNNING(DB.ACTIVITY.SPORT_RUNNING), BIKING(DB.ACTIVITY.SPORT_BIKING), OTHER(DB.ACTIVITY.SPORT_OTHER), ORIENTEERING(DB.ACTIVITY.SPORT_ORIENTEERING), WALKING(DB.ACTIVITY.SPORT_WALKING);

    final int dbValue;

    Sport(int dbValue) {
        this.dbValue = dbValue;
    }

    public int getDbValue() {
        return dbValue;
    }

    static public String textOf(int dbValue) {
        return textOf(null, dbValue);
    }

    static public String textOf(Resources res, int dbValue) {
        String sportName = null;
        if (res != null) {
            String sports[] = res.getStringArray(R.array.sportEntries);
            if (0 <= dbValue && dbValue < sports.length) {
                sportName = sports[dbValue];
            }
            if (sportName == null) {
                sportName = res.getString(R.string.Unknown);
            }
        }
        if (sportName == null) {
            //Some hardcoded values
            switch (dbValue) {
                case DB.ACTIVITY.SPORT_RUNNING:
                case DB.ACTIVITY.SPORT_ORIENTEERING:
                case DB.ACTIVITY.SPORT_WALKING:
                    sportName = "Running";
                    break;
                case DB.ACTIVITY.SPORT_BIKING:
                    sportName = "Biking";
                    break;
                default:
                    sportName = "Other";
                    break;
            }
        }
        return sportName;
    }

    static public Sport valueOf(int dbValue) {
        switch (dbValue) {
            case DB.ACTIVITY.SPORT_RUNNING:
                return RUNNING;
            case DB.ACTIVITY.SPORT_BIKING:
                return BIKING;
            case DB.ACTIVITY.SPORT_ORIENTEERING:
                return ORIENTEERING;
            case DB.ACTIVITY.SPORT_WALKING:
                return WALKING;
            default:
            case DB.ACTIVITY.SPORT_OTHER:
                return OTHER;
        }
    }

    static public int colorOf(int dbValue) {
        switch (dbValue) {
            case DB.ACTIVITY.SPORT_RUNNING:
                return R.color.sportRunning;
            case DB.ACTIVITY.SPORT_BIKING:
                return R.color.sportBiking;
            case DB.ACTIVITY.SPORT_WALKING:
                return R.color.sportWalking;
            case DB.ACTIVITY.SPORT_ORIENTEERING:
                return R.color.sportOrienteering;
            case DB.ACTIVITY.SPORT_OTHER:
                return R.color.sportOther;
            default:
                return R.color.colorText;
        }
    }

    static public int drawableColored16Of(int dbValue) {
        switch (dbValue) {
            case DB.ACTIVITY.SPORT_RUNNING:
                return R.drawable.sport_running;
            case DB.ACTIVITY.SPORT_BIKING:
                return R.drawable.sport_biking;
            case DB.ACTIVITY.SPORT_WALKING:
                return R.drawable.sport_walking;
            case DB.ACTIVITY.SPORT_ORIENTEERING:
                return R.drawable.sport_orienteering;
            case DB.ACTIVITY.SPORT_OTHER:
                return R.drawable.sport_other;
            default:
                return 0;
        }
    }

    public boolean IsWalking() {
        return dbValue == DB.ACTIVITY.SPORT_WALKING;
    }

    public boolean IsRunning() {
        return dbValue == DB.ACTIVITY.SPORT_RUNNING ||
                dbValue == DB.ACTIVITY.SPORT_ORIENTEERING;
    }

    public boolean IsCycling() {
        return dbValue == DB.ACTIVITY.SPORT_BIKING;
    }

    // part of filename used to determine type for Tapiriik files
    public String TapiriikType() {
        if (IsRunning()) {
            return "Running";
        } else if (IsCycling()) {
            return "Cycling";
        } else if (IsWalking()) {
            return "Walking";
        } else {
            return "Other";
        }
    }
}
