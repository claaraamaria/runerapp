

package org.runnerup.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.runnerup.R;
import org.runnerup.common.util.Constants.DB;

import java.util.ArrayList;


class AudioSchemeListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private SQLiteDatabase mDB = null;
    private boolean createNewItem = true;
    private final ArrayList<String> audioSchemes = new ArrayList<>();

    public AudioSchemeListAdapter(SQLiteDatabase db, LayoutInflater inflater, boolean createNew) {
        super();
        this.mDB = db;
        this.inflater = inflater;
        this.createNewItem = createNew;
    }

    @Override
    public int getCount() {
        return audioSchemes.size() + 2; // default + newItem or settings
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return inflater.getContext().getString(R.string.Default);
        }

        position -= 1;

        if (position < audioSchemes.size())
            return audioSchemes.get(position);

        Context context = inflater.getContext();

        if (createNewItem)
            return context.getString(R.string.New_audio_scheme);
        else
            return String.format(context.getString(R.string.dialog_ellipsis), context.getString(R.string.Manage_audio_cues));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,
                    false);
        }

        TextView ret = (TextView) convertView.findViewById(android.R.id.text1);
        ret.setText(getItem(position).toString());
        return ret;
    }

    public int find(String name) {
        for (int i = 0; i < getCount(); i++) {
            if (name.contentEquals(getItem(i).toString()))
                return i;
        }
        return 0;
    }

    public void reload() {
        audioSchemes.clear();
        String[] from = new String[]{
                DB.AUDIO_SCHEMES.NAME
        };

        Cursor c = mDB.query(DB.AUDIO_SCHEMES.TABLE, from, null, null, null, null,
                DB.AUDIO_SCHEMES.SORT_ORDER + " desc");
        if (c.moveToFirst()) {
            do {
                audioSchemes.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        this.notifyDataSetChanged();
    }
}
