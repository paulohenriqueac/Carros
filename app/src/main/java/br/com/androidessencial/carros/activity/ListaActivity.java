package br.com.androidessencial.carros.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.ListaFragment;

public class ListaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle param = getIntent().getExtras();
        String tipo = (String) param.get(TIPO);

        getSupportActionBar().setTitle(tipo);

        adicionarFragment(tipo);
    }

    private void adicionarFragment(String tipo){
        ListaFragment listaFragment = ListaFragment.novaInstancia(tipo);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_lista_content, listaFragment, tipo)
                .commit();
    }
}
