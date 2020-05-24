package org.runnerup.workout;
import android.location.Location;

import org.junit.Test;
import org.runnerup.tracker.Tracker;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//Hint: Local Unit Test can use System.out.print for printouts

public class AutoPauseTriggerTest {
    @Test
    public void shouldSetPauseIfSpeedIsLow() {
        float autoPauseAfterSeconds = 1;
        float autoPauseMinSpeed = 10;

        Location location = mock(Location.class);
        when(location.getTime()).thenReturn(1000L);

        Workout workout = mock(Workout.class);
        workout.tracker = mock(Tracker.class);
        when(workout.getLastKnownLocation()).thenReturn(location);
        when(workout.tracker.getCurrentSpeed()).thenReturn(9d);

        AutoPauseTrigger sut = new AutoPauseTrigger(autoPauseAfterSeconds, autoPauseMinSpeed);
        sut.onTick(workout);

        when(location.getTime()).thenReturn(3000L);
        sut.onTick(workout);

        verify(workout).onPause(workout);
    }

    @Test
    public void shouldNotSetPauseIfSpeedIsHigh() {
        float autoPauseAfterSeconds = 1;
        float autoPauseMinSpeed = 10;

        Location location = mock(Location.class);
        when(location.getTime()).thenReturn(1000L);

        Workout workout = mock(Workout.class);
        workout.tracker = mock(Tracker.class);
        when(workout.getLastKnownLocation()).thenReturn(location);
        when(workout.tracker.getCurrentSpeed()).thenReturn(11d);

        AutoPauseTrigger sut = new AutoPauseTrigger(autoPauseAfterSeconds, autoPauseMinSpeed);
        sut.onTick(workout);

        when(location.getTime()).thenReturn(3000L);
        sut.onTick(workout);

        verify(workout, never()).onPause(workout);
    }

    @Test
    public void shouldSetResumeIfSpeedIsHigh() {
        float autoPauseAfterSeconds = 1;
        float autoPauseMinSpeed = 10;

        Location location = mock(Location.class);
        when(location.getTime()).thenReturn(1000L);

        Workout workout = mock(Workout.class);
        workout.tracker = mock(Tracker.class);
        when(workout.getLastKnownLocation()).thenReturn(location);
        when(workout.tracker.getCurrentSpeed()).thenReturn(9d);

        AutoPauseTrigger sut = new AutoPauseTrigger(autoPauseAfterSeconds, autoPauseMinSpeed);
        sut.onTick(workout);

        when(location.getTime()).thenReturn(3000L);
        sut.onTick(workout);

        when(workout.tracker.getCurrentSpeed()).thenReturn(11d);
        when(location.getTime()).thenReturn(5000L);
        sut.onTick(workout);

        verify(workout).onResume(workout);
    }

    @Test
    public void shouldNotSetPauseIfLoosingGps() {
        float autoPauseAfterSeconds = 1;
        float autoPauseMinSpeed = 10;

        Location location = mock(Location.class);
        when(location.getTime()).thenReturn(1000L);

        Workout workout = mock(Workout.class);
        workout.tracker = mock(Tracker.class);
        when(workout.getLastKnownLocation()).thenReturn(location);
        when(workout.tracker.getCurrentSpeed()).thenReturn(9d);

        AutoPauseTrigger sut = new AutoPauseTrigger(autoPauseAfterSeconds, autoPauseMinSpeed);
        sut.onTick(workout);
        sut.onTick(workout);

        when(workout.tracker.getCurrentSpeed()).thenReturn(11d);
        when(location.getTime()).thenReturn(5000L);
        sut.onTick(workout);

        verify(workout, never()).onPause(workout);
    }
}