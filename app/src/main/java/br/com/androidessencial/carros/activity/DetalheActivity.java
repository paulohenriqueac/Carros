package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;
import br.com.androidessencial.carros.fragment.DetalheFragment;

public class DetalheActivity extends BaseActivity {
    private static final String CARRO = "carro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Carro c = (Carro) getIntent().getSerializableExtra(CARRO);
        getSupportActionBar().setTitle(c.getNome());

        if (savedInstanceState == null) {
            DetalheFragment detalheFragment = DetalheFragment.novaInstancia();
            detalheFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, detalheFragment)
                .commit();

        }


    }
}
