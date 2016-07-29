package br.com.androidessencial.carros.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.androidessencial.carros.R;

public class CarroFragment extends BaseFragment {
    public static final String TIPO = "tipo";

    public static CarroFragment novaInstancia(){
        CarroFragment carroFragment = new CarroFragment();
        return carroFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);

        //Obter parametro referente ao item selecionado no menu
        Bundle param = getArguments();

        TextView textView = (TextView) view.findViewById(R.id.textViewTipoCarro);
        textView.setText(param.getString(TIPO));

        return view;
    }
}
