package institutosos.org.br.destinocerto.activity.signup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.Application;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.model.Site;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateSiteActivity extends AppCompatActivity {

    @Bind(R.id.create_site_button)
    Button createButton;

    @Bind(R.id.site_name)
    EditText siteName;

    @Bind(R.id.site_address)
    EditText siteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_site);
        ButterKnife.bind(this);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValid()) {
                    return;
                }

                Application
                        .getApiClient()
                        .getService()
                        .createSite(siteName.getText().toString(), siteAddress.getText().toString(),
                                new Callback<Site>() {
                                    @Override
                                    public void success(Site site, Response response) {
                                        finish();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        // TODO show error
                                    }
                                });
            }
        });
    }

    private boolean isValid() {
        // TODO
        return true;
    }

}
