package br.com.androidessencial.carros.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class DetalheFragment extends BaseFragment {
    private Carro carro;

    public static DetalheFragment novaInstancia(){
        DetalheFragment detalheFragment = new DetalheFragment();
        return detalheFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe, container, false);

        carro = (Carro)getArguments().getSerializable(CARRO);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewFoto);
        Picasso.with(getContext()).load(carro.getUrlFoto()).fit().into(imageView);

        TextView textViewDescricao = (TextView) view.findViewById(R.id.textViewDescricao);
        //textViewDescricao.setText(carro.getDesc());

        textViewDescricao.setText(carro.getDesc() + "                                         " +
                "                 " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste " +
                "Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste");

        return view;
    }
}
