package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DOWNLOAD_IMAGE_TASK";
    private final ProgressBar progressBar;
    private final ImageView image;

    public DownloadImageTask(ImageView bmImage, ProgressBar progressBar) {
        this.image = bmImage;
        this.progressBar = progressBar;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        Log.d(TAG, "finished downloading");
        image.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        image.setImageBitmap(result);
    }
}
