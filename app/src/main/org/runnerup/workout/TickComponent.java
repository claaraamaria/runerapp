

package org.runnerup.workout;

interface TickComponent extends WorkoutComponent {
    boolean onTick(Workout w);
}
