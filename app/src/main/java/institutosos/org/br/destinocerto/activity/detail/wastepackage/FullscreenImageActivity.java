package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.R;

public class FullscreenImageActivity extends AppCompatActivity {
    public static final String IMAGE_URL = "IMAGE_URL";

    @Bind(R.id.fullscreen_image)
    ImageView imageView;

    @Bind(R.id.fullscreen_image_loading)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        new DownloadImageTask(imageView, progressBar).execute(getIntent().getStringExtra(IMAGE_URL));
    }

}
