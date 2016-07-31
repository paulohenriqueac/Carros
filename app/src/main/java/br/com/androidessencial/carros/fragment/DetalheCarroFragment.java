package br.com.androidessencial.carros.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.androidessencial.carros.R;

public class DetalheCarroFragment extends BaseFragment {

    public static DetalheCarroFragment newInstance(String param1, String param2) {
        DetalheCarroFragment fragment = new DetalheCarroFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalhe_carro, container, false);
    }
}
