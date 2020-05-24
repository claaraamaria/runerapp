

package org.runnerup.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class SimpleCursorLoader extends android.support.v4.content.CursorLoader {

    private final SQLiteDatabase mDB;
    private final String mTable;
    private final ForceLoadContentObserver mObserver;

    public SimpleCursorLoader(final Context context, final SQLiteDatabase db, final String table,
            final String[] projection,
            final String selection, final String[] selectionArgs, final String sortOrder) {
        super(context, null, projection, selection, selectionArgs, sortOrder);
        mDB = db;
        mTable = table;
        mObserver = new ForceLoadContentObserver();
    }

    @Override
    public Cursor loadInBackground() {
        final Cursor cursor = mDB.query(mTable, getProjection(), getSelection(),
                getSelectionArgs(), null, null, getSortOrder());
        if (cursor != null) {
            // Ensure the cursor window is filled
            cursor.getCount();
            cursor.registerContentObserver(mObserver);
        }
        return cursor;
    }
}
