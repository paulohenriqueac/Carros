package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import br.com.androidessencial.carros.R;

public class DetalheCarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_carro);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalhe do Carro");
    }
}
