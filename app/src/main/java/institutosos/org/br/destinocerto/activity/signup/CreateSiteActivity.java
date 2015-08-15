package institutosos.org.br.destinocerto.activity.signup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.Application;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.api.ApiService;
import institutosos.org.br.destinocerto.model.Site;

public class CreateSiteActivity extends AppCompatActivity {
    private CreateSiteTask createTask;

    @Bind(R.id.create_site_button)
    Button createButton;

    @Bind(R.id.site_name)
    EditText siteName;

    @Bind(R.id.site_address)
    EditText siteAddress;

    @Bind(R.id.create_site_form)
    View createSiteForm;

    @Bind(R.id.create_site_progress)
    ProgressBar createSiteProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_site);
        ButterKnife.bind(this);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptCreate();
            }
        });
    }

    private void attemptCreate() {
        if (createTask != null) {
            return;
        }

        // Reset errors.
        siteName.setError(null);
        siteAddress.setError(null);

        String name = siteName.getText().toString();
        String address = siteAddress.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for non empty address
        if (TextUtils.isEmpty(address)) {
            siteAddress.setError(getString(R.string.error_field_required));
            focusView = siteAddress;
            cancel = true;
        }

        // Check for non empty name
        if (TextUtils.isEmpty(name)) {
            siteName.setError(getString(R.string.error_field_required));
            focusView = siteName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            createTask = new CreateSiteTask(name, address);
            createTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            createSiteForm.setVisibility(show ? View.GONE : View.VISIBLE);
            createSiteForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    createSiteForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            createSiteProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            createSiteProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    createSiteProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            createSiteProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            createSiteForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class CreateSiteTask extends AsyncTask<Void, Void, Boolean> {
        private final String name;
        private final String address;

        CreateSiteTask(String name, String address) {
            this.name = name;
            this.address = address;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ApiService api = Application.getApiClient().getService();
            Site site = api.createSite(name, address);
            api.updateUser(Application.getUser().getId(), site.getId());

            Application.getUser().setSite(site);
            finish();

            return true;
        }
    }

}
