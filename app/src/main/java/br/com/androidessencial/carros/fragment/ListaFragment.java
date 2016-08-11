package br.com.androidessencial.carros.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.activity.DetalheActivity;
import br.com.androidessencial.carros.adapter.CarroAdapter;
import br.com.androidessencial.carros.domain.Carro;
import br.com.androidessencial.carros.service.CarroService;

public class ListaFragment extends BaseFragment {
    private static final String TIPO = "tipo";
    private String tipo;
    private RecyclerView recyclerView;
    private List<Carro> carros;

    public static ListaFragment novaInstancia(String tipo){
        Bundle param = new Bundle();
        param.putString(TIPO, tipo);

        ListaFragment listaFragment = new ListaFragment();
        listaFragment.setArguments(param);

        return listaFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.tipo = getArguments().getString(TIPO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged (RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    AppCompatActivity activity = (AppCompatActivity) getActivity();
                    ActionBar actionBar = (ActionBar) activity.getSupportActionBar();

                    actionBar.hide();

                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        this.carros = CarroService.getCarros(getContext(), tipo);
        recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener(){
            @Override
            public void onClickCarro(View view, int position){
                Carro c = carros.get(position);

                Intent intent = new Intent(getContext(), DetalheActivity.class);
                intent.putExtra(CARRO, Parcels.wrap(c));
                startActivity(intent);
            }
        };
    }
}
