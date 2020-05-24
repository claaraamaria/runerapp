

package org.runnerup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.runnerup.R;

public class TitleSpinner extends LinearLayout implements SpinnerInterface {
    SpinnerPresenter mPresenter;
    LinearLayout mLayout;
    TextView mLabel;
    TextView mValue;
    Spinner mSpinner;

    public TitleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.title_spinner, this);

        mLayout = (LinearLayout)findViewById(R.id.title_spinner);
        mLabel = (TextView)findViewById(R.id.title);
        mValue = (TextView)findViewById(R.id.value);
        mSpinner = (Spinner)findViewById(R.id.spinner);

        mPresenter = new SpinnerPresenter(context, attrs, this);
    }

    @Override
    public void setOnClickSpinnerOpen() {
        setViewOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinner.performClick();
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mLayout.setEnabled(enabled);
        mSpinner.setEnabled(enabled);
    }

    @Override
    public void setViewPrompt(CharSequence charSequence) {
        mSpinner.setPrompt(charSequence);
    }

    @Override
    public void setViewLabel(CharSequence label) {
        mLabel.setText(label);
    }

    @Override
    public void setViewValue(int itemId) {
        Object val = mSpinner.getAdapter().getItem(itemId);
        if (val != null)
            setViewText(val.toString());
        else
            setViewText("");
    }

    @Override
    public void setViewText(CharSequence charSequence) {
        mValue.setText(charSequence);
    }

    @Override
    public CharSequence getViewValueText() {
        return mValue.getText();
    }

    @Override
    public void setViewOnClickListener(OnClickListener onClickListener) {
        mLayout.setOnClickListener(onClickListener);
    }

    @Override
    public void setViewAdapter(DisabledEntriesAdapter adapter) {
        mSpinner.setAdapter(adapter);
    }

    @Override
    public SpinnerAdapter getViewAdapter() {
        return mSpinner.getAdapter();
    }

    @Override
    public void setViewSelection(int value) {
        mSpinner.setSelection(value);
    }

    @Override
    public void viewOnClose(OnCloseDialogListener listener, boolean b) {
        listener.onClose(this, b);
    }

    @Override
    public void setViewOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mSpinner.setOnItemSelectedListener(listener);
    }

    public void setAdapter(SpinnerAdapter adapter) {
        mSpinner.setAdapter(adapter);
        mPresenter.loadValue(null);
    }

    public void setValue(int value) {
        mPresenter.setValue(value);
    }

    public void setValue(String value) {
        mPresenter.setValue(value);
    }

    public CharSequence getValue() {
        return mPresenter.getValue();
    }

    public int getValueInt() {
        return mPresenter.getValueInt();
    }

    public void addDisabledValue(int value) {
        int selection = mPresenter.getSelectionValue(value);
        ((DisabledEntriesAdapter)mSpinner.getAdapter()).addDisabled(selection);
    }

    public void clearDisabled() {
        ((DisabledEntriesAdapter)mSpinner.getAdapter()).clearDisabled();
    }

    public void clear() {
        mPresenter.clear();
    }

    public void setOnSetValueListener(SpinnerInterface.OnSetValueListener listener) {
        mPresenter.setOnSetValueListener(listener);
    }

    public void setOnCloseDialogListener(SpinnerInterface.OnCloseDialogListener listener) {
        mPresenter.setOnCloseDialogListener(listener);
    }
}