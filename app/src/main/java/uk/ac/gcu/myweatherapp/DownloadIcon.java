package uk.ac.gcu.myweatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.security.AccessController;

import static java.security.AccessController.getContext;

public class DownloadIcon extends AsyncTask<String,Void,Bitmap> {
//    ImageView imageView;
    LocationRecyclerAdapter.ViewHolder viewHolder;
    LocationRecyclerAdapter activity;

    public DownloadIcon(LocationRecyclerAdapter.ViewHolder view, LocationRecyclerAdapter activity) {
//        this.imageView = imageView;
        this.activity = activity;
        this.viewHolder = view;
//        Toast.makeText(getContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
//        imageView.setImageBitmap(result);
        activity.callBackImage(viewHolder,result);
    }
}
