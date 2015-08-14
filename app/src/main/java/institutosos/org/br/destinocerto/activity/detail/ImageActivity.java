package institutosos.org.br.destinocerto.activity.detail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import institutosos.org.br.destinocerto.R;

public class ImageActivity extends Activity {
    public static final String IMAGE_URL = "IMAGE_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);

        final ImageView contentView = (ImageView) findViewById(R.id.fullscreen_image);
        new DownloadImageTask(contentView).execute(getIntent().getStringExtra(IMAGE_URL));
    }

}
