

package org.runnerup.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;

public class ClassicSpinner extends AppCompatSpinner implements SpinnerInterface {
    SpinnerPresenter mPresenter;

    public ClassicSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPresenter = new SpinnerPresenter(context, attrs, this);
    }

    @Override
    public void setViewPrompt(CharSequence charSequence) {
        setPrompt(charSequence);
    }

    @Override
    public void setViewLabel(CharSequence label) {
        setContentDescription(label);
    }

    @Override
    public void setViewValue(int itemId) {
        setSelection(itemId);
    }

    @Override
    public void setViewText(CharSequence charSequence) { }
    @Override
    public CharSequence getViewValueText() {
        return getSelectedItem().toString();
    }

    @Override
    public void setViewOnClickListener(OnClickListener onClickListener) {
         setOnClickListener(onClickListener);
    }

    @Override
    public void setOnClickSpinnerOpen() {}

    @Override
    public void setViewAdapter(DisabledEntriesAdapter adapter) {
        setAdapter(adapter);
    }

    @Override
    public SpinnerAdapter getViewAdapter() {
        return getAdapter();
    }

    @Override
    public void setViewSelection(int value) {
        setSelection(value);
    }

    @Override
    public void viewOnClose(OnCloseDialogListener listener, boolean b) {
        listener.onClose(this, b);
    }

    @Override
    public void setViewOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        setOnItemSelectedListener(listener);
    }
}

