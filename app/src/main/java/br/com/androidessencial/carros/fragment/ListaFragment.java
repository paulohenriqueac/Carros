package br.com.androidessencial.carros.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.List;

import br.com.androidessencial.carros.CarrosApplication;
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

        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        if (!CarrosApplication.verificarConexao(getContext())) {
            Snackbar.make(getView(), getString(R.string.problema_conexao),Snackbar.LENGTH_INDEFINITE).show();
        } else {
            //Buscar Lista de Carros
            taskCarros(this.tipo);
        }
    }

    private void taskCarros(String tipo){
        new GetCarrosTask().execute(tipo);
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

    private class GetCarrosTask extends AsyncTask<String, Void, List<Carro>>{
        private ProgressDialog progressDialog = null;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.carregando));
            progressDialog.show();
        }

        @Override
        protected List<Carro> doInBackground(String... params) {
            List<Carro> carros = null;

            try {
                return CarroService.getCarros(getContext(), params[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            if(carros != null){
               ListaFragment.this.carros = carros;
               recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
            }

            progressDialog.dismiss();
        }
    }
}
