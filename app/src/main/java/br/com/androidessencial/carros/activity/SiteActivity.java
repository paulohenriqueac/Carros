package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import br.com.androidessencial.carros.R;

public class SiteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.site_livro);
    }
}
