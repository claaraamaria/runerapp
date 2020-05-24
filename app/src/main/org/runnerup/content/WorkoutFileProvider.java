

package org.runnerup.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;

import org.runnerup.BuildConfig;
import org.runnerup.workout.WorkoutSerializer;

import java.io.File;
import java.io.FileNotFoundException;


public class WorkoutFileProvider extends ContentProvider {

    // The authority is the symbolic name for the provider class
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".workout.file.provider";
    public static final String MIME = "application/vnd.garmin.workout+json";

    // UriMatcher used to match against incoming requests
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // Add a URI to the matcher which will match against the form
        // 'content://com.stephendnicholas.gmailattach.provider/*'
        // and return 1 in the case that the incoming Uri matches this pattern
        uriMatcher.addURI(AUTHORITY, "*", 1);

        return true;
    }

    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode)
            throws FileNotFoundException {

        // Check incoming Uri against the matcher
        switch (uriMatcher.match(uri)) {
        // If it returns 1 - then it matches the Uri defined in onCreate
            case 1:

                // The desired file name is specified by the last segment of the
                // path
                // E.g.
                // 'content://com.stephendnicholas.gmailattach.provider/Test.txt'
                // Take this and build the path to the file
                File file = WorkoutSerializer.getFile(getContext(), uri.getLastPathSegment());

                // Create & return a ParcelFileDescriptor pointing to the file
                // Note: I don't care what mode they ask for - they're only
                // getting
                // read only
                return ParcelFileDescriptor.open(file,
                        ParcelFileDescriptor.MODE_READ_ONLY);

                // Otherwise unrecognised Uri
            default:
                throw new FileNotFoundException("Unsupported uri: "
                        + uri.toString());
        }
    }

    // //////////////////////////////////////////////////////////////
    // Not supported / used / required for this example
    // //////////////////////////////////////////////////////////////

    @Override
    public int update(@NonNull Uri uri, ContentValues contentvalues, String s,
                      String[] as) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] as) {
        return 0;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentvalues) {
        return null;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        if (uriMatcher.match(uri) == 1) {
            return MIME;
        }
        return null;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String s, String[] as1,
                        String s1) {
        return null;
    }
}
