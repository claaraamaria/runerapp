
package org.runnerup.tracker;

import org.runnerup.workout.WorkoutInfo;

public interface WorkoutObserver {
    // @note: type is in Constants.DB.LOCATION.TYPE
    void workoutEvent(WorkoutInfo workoutInfo, int type);
}
