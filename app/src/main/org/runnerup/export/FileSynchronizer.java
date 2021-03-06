package org.runnerup.export;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.runnerup.R;
import org.runnerup.common.util.Constants;
import org.runnerup.common.util.Constants.DB;
import org.runnerup.db.PathSimplifier;
import org.runnerup.export.format.GPX;
import org.runnerup.export.format.TCX;
import org.runnerup.workout.FileFormats;
import org.runnerup.workout.Sport;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;


public class FileSynchronizer extends DefaultSynchronizer {

    public static final String NAME = "File";

    private long id = 0;
    private String mPath;
    private FileFormats mFormat;
    private PathSimplifier simplifier;

    FileSynchronizer() {}

    FileSynchronizer(Context context, PathSimplifier simplifier) {
        this();
        this.simplifier = simplifier;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getPublicUrl() {
        return "file://" + mPath;
    }

    @Override
    public int getIconId() {return R.drawable.service_file;}

    @Override
    public int getColorId() {return R.color.colorPrimary;}

    static public String contentValuesToAuthConfig(ContentValues config) {
        FileSynchronizer f = new FileSynchronizer();
        f.mPath = config.getAsString(DB.ACCOUNT.URL);
        return f.getAuthConfig();
    }

    @Override
    public void init(ContentValues config) {
        String authConfig = config.getAsString(DB.ACCOUNT.AUTH_CONFIG);
        if (authConfig != null) {
            try {
                mFormat = new FileFormats(config.getAsString(DB.ACCOUNT.FORMAT));
                JSONObject tmp = new JSONObject(authConfig);
                mPath = tmp.optString(DB.ACCOUNT.URL, null);
            } catch (JSONException e) {
                Log.w(getName(), "init: Dropping config due to failure to parse json from " + authConfig + ", " + e);
            }
        }
        id = config.getAsLong("_id");
    }

    @Override
    public String getAuthConfig() {
        JSONObject tmp = new JSONObject();
        if (isConfigured()) {
            try {
                tmp.put(DB.ACCOUNT.URL, mPath);
            } catch (JSONException e) {
                Log.w(getName(), "getAuthConfig: Failure to create json for " + mPath + ", " + e);
    }
        }
        return tmp.toString();
    }

    @Override
    public boolean isConfigured() {
        return !TextUtils.isEmpty(mPath);
    }

    @Override
    public void reset() {
        mPath = null;
    }

    @Override
    public Status connect() {
        Status s = Status.NEED_AUTH;
        s.authMethod = AuthMethod.FILEPERMISSION;
        if (TextUtils.isEmpty(mPath))
            return s;
        try {
            File dstDir = new File(mPath);
            //noinspection ResultOfMethodCallIgnored
            dstDir.mkdirs();
            if (dstDir.isDirectory()) {
                s = Status.OK;
            }
        } catch (SecurityException e) {
            //Status is NEED_AUTH
        }
        return s;
    }

    @Override
    public Status upload(SQLiteDatabase db, final long mID) {
        Status s = Status.ERROR;
        s.activityId = mID;
        if ((s = connect()) != Status.OK) {
            return s;
        }

        Sport sport = Sport.RUNNING;
        try {
            String[] columns = {
                    Constants.DB.ACTIVITY.SPORT
            };
            Cursor c = null;
            try {
                c = db.query(Constants.DB.ACTIVITY.TABLE, columns, "_id = " + mID,
                        null, null, null, null);
                if (c.moveToFirst()) {
                    sport = Sport.valueOf(c.getInt(0));
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
            String fileBase = new File(mPath).getAbsolutePath() + File.separator +
                    String.format(Locale.getDefault(), "RunnerUp_%04d_%s.", mID, sport.TapiriikType());
            
            if (mFormat.contains(FileFormats.TCX)) {
                TCX tcx = new TCX(db, simplifier);
                File file = new File(fileBase + FileFormats.TCX.getValue());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                tcx.export(mID, new OutputStreamWriter(out));
                s.externalId = Uri.fromFile(file).toString();
                s.externalIdStatus = ExternalIdStatus.NONE; //Not working yet
            }
            if (mFormat.contains(FileFormats.GPX)) {
                GPX gpx = new GPX(db, true, true, simplifier);
                File file = new File(fileBase + FileFormats.GPX.getValue());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                gpx.export(mID, new OutputStreamWriter(out));
            }
            s = Status.OK;
        } catch (IOException e) {
            //Status is ERROR
        }
        return s;
    }

    @Override
    public boolean checkSupport(Feature f) {
        switch (f) {
            case UPLOAD:
            case FILE_FORMAT:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void logout() {
    }
}
