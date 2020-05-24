

package org.runnerup.feed;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;


public class FeedImageLoader {
    static private final Map<String, Bitmap> imageCache = Collections
            .synchronizedMap(new WeakHashMap<String, Bitmap>());

    public interface Callback {
        void run(final String url, final Bitmap b);
    }

    static public Bitmap LoadImageSync(final String url) {
        final String fixedUrl = FixUrl(url);
        Bitmap b = imageCache.get(fixedUrl);
        if (b != null) {
            return b;
        }
        try {
            InputStream is = (InputStream) new URL(fixedUrl).getContent();
            b = BitmapFactory.decodeStream(is);
            if (b != null) {
                imageCache.put(fixedUrl, b);
            }
            return b;
        } catch (Exception e) {
            Log.e("FeedImageLoader", "url exception for " + fixedUrl + ": " + e.getMessage());
        }
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    static public void LoadImageAsync(final String url, final Callback onLoadingDone) {
        final String fixedUrl = FixUrl(url);
        Bitmap b = imageCache.get(url);
        if (b != null) {
            Log.i("FeedImageLoader", "Found cached image for " + url);
            onLoadingDone.run(url, b);
        } else {
            Log.i("FeedImageLoader", "Downloading image for " + url);
            new AsyncTask<String, String, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    Bitmap b = imageCache.get(fixedUrl);
                    if (b != null) {
                        return b;
                    }
                    try {
                        InputStream is = (InputStream) new URL(fixedUrl).getContent();
                        b = BitmapFactory.decodeStream(is);
                        if (b != null) {
                            imageCache.put(fixedUrl, b);
                        }
                        return b;
                    } catch (Exception e) {
                        Log.e("FeedImageLoader", "url exception for " + fixedUrl + ": " + e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    if (result == null)
                        return;

                    onLoadingDone.run(url, result);
                }
            }.execute(url);
        }
    }

    /**
     * fb redirects from http to https which is not handled automatically by HttpUrlConnection
     */
    static private String FixUrl(String in) {
        return in.replace("http://graph.facebook.com/", "https://graph.facebook.com/");
    }

}
