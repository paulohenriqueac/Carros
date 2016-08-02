package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import org.parceler.Parcels;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;
import br.com.androidessencial.carros.fragment.DetalheFragment;

public class DetalheActivity extends BaseActivity {
    private static final String TAG = "DetalheFragment";
    private static final String CARRO = "carro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Carro c = Parcels.unwrap(getIntent().getParcelableExtra(CARRO));
        getSupportActionBar().setTitle(c.nome);

        if (savedInstanceState == null) {
            DetalheFragment detalheFragment = DetalheFragment.novaInstancia();
            detalheFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_detalhe_content, detalheFragment, TAG)
                .commit();

        }


    }
}
