package com.saisreeharsha.cordova.plugin.DownloadImageToGallery;

/**
 * Created by Sai Sree Harsha Dubagunta on 29-12-2019.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;


public class DownloadImageToGallery extends CordovaPlugin {

    private static CallbackContext gEventCallback = null;
    public static final String LOGTAG = "DownloadImageToGallery";
    public String imageUrl = null;
    public static String photoDir;


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {
            imageUrl = args.getString(0);
            gEventCallback = callbackContext;
            if (imageUrl == null) {
                gEventCallback.error("Failure");
                return false;
            }
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (ActivityCompat.checkSelfPermission(cordova.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Log.v(LOGTAG, "Permission is not granted");
                        ActivityCompat.requestPermissions(cordova.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        new DownloadImage().execute(imageUrl);
                    } else {
                        new DownloadImage().execute(imageUrl);
                    }
                }
            });
            return true;
        }
        return false;
    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(LOGTAG, "Something went wrong!");
                e.printStackTrace();
                gEventCallback.error("Failure");
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }


        @Override
        protected void onPreExecute() {
            Log.i(LOGTAG, "onPreExecute Called");
        }

        protected void onPostExecute(Bitmap result) {
            saveImage(result);
        }
    }

    private static String getGalleryPath() {
        return photoDir = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/Camera/";
    }

    private void saveImage(Bitmap finalBitmap) {

        String path = getGalleryPath();
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
        File myDir = new File(path);
        myDir.mkdirs();
        File file = new File(myDir, fileName);
        if (file.exists()) file.delete();
        Log.i(LOGTAG, path + fileName);
        scanImage(file);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            gEventCallback.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        } catch (Exception e) {
            e.printStackTrace();
            gEventCallback.sendPluginResult(new PluginResult((PluginResult.Status.ERROR)));
        }
    }

    private void scanImage(File image) {
        Log.d(LOGTAG, "Scanning downloaded Image");

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(image);

        mediaScanIntent.setData(contentUri);

        cordova.getActivity().sendBroadcast(mediaScanIntent);
    }
}
