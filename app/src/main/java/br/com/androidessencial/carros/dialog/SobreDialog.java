package br.com.androidessencial.carros.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.androidessencial.carros.R;


public class SobreDialog extends DialogFragment {
    public static final String DIALOG_SOBRE = "dialog_sobre";

    public static void showSobreDialog(FragmentManager fm){
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(DIALOG_SOBRE);

        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);

        new SobreDialog().show(ft, DIALOG_SOBRE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_fragment_sobre, container, false);
        Button buttonFechar = (Button) view.findViewById(R.id.sobre_btn_fechar);

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }
}
