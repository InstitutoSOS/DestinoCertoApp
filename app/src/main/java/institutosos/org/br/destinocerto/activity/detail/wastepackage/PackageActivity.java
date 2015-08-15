package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.Application;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.MainActivity;
import institutosos.org.br.destinocerto.model.Location;
import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PackageActivity extends AppCompatActivity {

    public static final String BARCODE = "BARCODE";
    private static final String TAG = "PACKAGE_ACTIVITY";

    @Bind(R.id.package_material)
    TextView packageMaterial;

    @Bind(R.id.package_cooperative)
    TextView packageCooperative;

    @Bind(R.id.package_picture)
    ImageView packagePicture;

    @Bind(R.id.package_picture_loading)
    ProgressBar progressBar;

    @Bind(R.id.confirm_package_button)
    Button confirmPackageButton;

    @Bind(R.id.decline_package_button)
    Button declinePackageButton;

    private static HashMap<String, WastePackage> _packages = new HashMap<>();
    private WastePackage _package;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        ButterKnife.bind(this);

        hideButtons();

        confirmPackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.getApiClient().getService().updatePackageLocation(_package.getId(), Application.getUser().getSite().getId(), new Callback<WastePackage>() {
                    @Override
                    public void success(WastePackage wastePackage, Response response) {
                        Location l = new Location();
                        l.setSite(Application.getUser().getSite());
                        l.setTimestamp(new Date());
                        _package.getLocationHistory().add(l);
                        setupFragment();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // TODO show error
                    }
                });
                Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_package_root), "Package confirmed!", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(0xFF519827);
                snackbar.show();
                hideButtons();
            }
        });
        declinePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_package_root), "Package declined!", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(0xFFBD1F1F);
                snackbar.show();
                hideButtons();
            }
        });

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
                        .setTitle(getResources().getString(R.string.package_not_found))
                        .setMessage(getResources().getString(R.string.package_not_found_description))
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

    private void hideButtons() {
        confirmPackageButton.setVisibility(View.GONE);
        declinePackageButton.setVisibility(View.GONE);
    }

    private void showButtons() {
        confirmPackageButton.setVisibility(View.VISIBLE);
        declinePackageButton.setVisibility(View.VISIBLE);
    }

    private void setup() {
        setupFragment();
        packageMaterial.setText(_package.getMaterial().getName());
        packageCooperative.setText(_package.getCurrentLocation().getSite().getName());
        new DownloadImageTask(packagePicture, progressBar).execute(_package.getImageUrl());

        // show only if this package is not in the users site (and user is signed in)
        if (Application.getUser() != null && Application.getUser().getSite().getId() != _package.getCurrentLocation().getSite().getId()) {
            showButtons();
        }

        packagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "showing fullscreen");
                Intent intent = new Intent(PackageActivity.this, FullscreenImageActivity.class);
                intent.putExtra(FullscreenImageActivity.IMAGE_URL, _package.getImageUrl());
                startActivity(intent);
            }
        });
    }

    private void setupFragment() {
        ((PackageListFragment) getFragmentManager().findFragmentById(R.id.package_info_list)).setPackage(_package);
    }

}
