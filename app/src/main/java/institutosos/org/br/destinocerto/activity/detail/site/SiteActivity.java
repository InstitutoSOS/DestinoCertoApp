package institutosos.org.br.destinocerto.activity.detail.site;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.Application;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.MainActivity;
import institutosos.org.br.destinocerto.model.Site;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SiteActivity extends AppCompatActivity {

    public static final String ID = "ID";
    private static Map<Integer, Site> _sites = new HashMap<>();
    private Site _site;

    @Bind(R.id.site_name)
    TextView siteName;

    @Bind(R.id.site_type)
    TextView siteType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int id= intent.getIntExtra(ID, 0);

        if (_sites.get(id) != null) {
            _site = _sites.get(id);
            setup();
            return;
        }

        Application.getApiClient().getService().getSite(id, new Callback<Site>() {
            @Override
            public void success(final Site site, Response response) {
                _site = site;
                _sites.put(id, site);
                setup();
            }

            @Override
            public void failure(RetrofitError error) {
                new AlertDialog.Builder(SiteActivity.this)
                        .setTitle(R.string.site_not_found)
                        .setMessage(R.string.site_not_found_description)
                        .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SiteActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void setup() {
        ((SiteListFragment) getFragmentManager().findFragmentById(R.id.package_info_list)).setSite(_site);
        siteName.setText(_site.getName());
        siteType.setText(_site.getType());
    }

}
