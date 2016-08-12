package br.com.androidessencial.carros.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

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
        getSupportActionBar().setTitle(c.modelo);

        ImageView imageView = (ImageView) findViewById(R.id.imgToolbar);
        Picasso.with(this).load(c.urlFoto).fit().into(imageView,
                new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onError(){}
                });


        if (savedInstanceState == null) {
            DetalheFragment detalheFragment = DetalheFragment.novaInstancia();
            detalheFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detalheContent, detalheFragment, TAG)
                .commit();
        }
    }
}
