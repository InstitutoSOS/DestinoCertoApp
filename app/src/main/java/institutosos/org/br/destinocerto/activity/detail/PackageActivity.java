package institutosos.org.br.destinocerto.activity.detail;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.Application;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.MainActivity;
import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PackageActivity extends AppCompatActivity {

    public static final String BARCODE = "BARCODE";

    @Bind(R.id.package_material)
    TextView packageMaterial;

    @Bind(R.id.package_cooperative)
    TextView packageCooperative;

    @Bind(R.id.package_picture)
    ImageView packagePicture;

    private static HashMap<String, WastePackage> _packages = new HashMap<>();
    private WastePackage _package;
    private PackageListFragment _fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        ButterKnife.bind(this);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(android.R.id.content) == null) {
            _fragment = new PackageListFragment();
            fm.beginTransaction().add(android.R.id.content, _fragment).commit();
        }


        Intent intent = getIntent();
        final String barcode = intent.getStringExtra(BARCODE);

        if (_packages.get(barcode) != null) {
            _package = _packages.get(barcode);
            setup();
            return;
        }

        Application.getApiClient().getService().getPackage(barcode, new Callback<WastePackage>() {
            @Override
            public void success(final WastePackage wastePackage, Response response) {
                _package = wastePackage;
                _packages.put(barcode, wastePackage);
                setup();
            }

            @Override
            public void failure(RetrofitError error) {
                new AlertDialog.Builder(PackageActivity.this)
                        .setTitle("Package code not found")
                        .setMessage("The scanned code could not be found in our database.")
                        .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(PackageActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    private void setup() {
        _fragment.setPackage(_package);
        packageMaterial.setText(_package.getMaterial().toString());
        packageCooperative.setText(_package.getSite().getName());
        new DownloadImageTask(packagePicture).execute(_package.getImageUrl());

        packagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackageActivity.this, ImageActivity.class);
                intent.putExtra(ImageActivity.IMAGE_URL, _package.getImageUrl());
                startActivity(intent);
            }
        });
    }
}
