package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.SiteFragment;

public class SiteActivity extends BaseActivity {
    private static final String TAG = "SiteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.site_livro);

        SiteFragment siteFragment = SiteFragment.novaInstancia();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_site_content,siteFragment, TAG)
                .commit();
    }
}
