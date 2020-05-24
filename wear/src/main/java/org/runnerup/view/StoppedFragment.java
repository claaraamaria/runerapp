package org.runnerup.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.runnerup.R;


public class StoppedFragment extends Fragment {

    public StoppedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stopped, container, false);
        super.onViewCreated(view, savedInstanceState);
        return view;
    }
}
