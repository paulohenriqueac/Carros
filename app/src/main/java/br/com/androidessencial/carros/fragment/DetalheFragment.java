package br.com.androidessencial.carros.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.net.URI;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.dao.CarroDAO;
import br.com.androidessencial.carros.domain.Carro;

public class DetalheFragment extends BaseFragment {
    public static View view;
    private Carro carro;
    private FloatingActionButton fabFavorito;

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
        view = inflater.inflate(R.layout.fragment_detalhe, container, false);

        carro = Parcels.unwrap(getArguments().getParcelable(CARRO));

        fabFavorito = (FloatingActionButton) getActivity().findViewById(R.id.fabFavoritos);
        fabFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_fab) {
                CarroDAO db = new CarroDAO(getContext());

                boolean exists = db.exists(carro.nome);

                if (!exists) {
                    db.save(carro);
                    Snackbar.make(view, getString(R.string.add_favoritos),Snackbar.LENGTH_SHORT).show();
                } else {
                    db.delete(carro);
                    Snackbar.make(view, getString(R.string.del_favoritos),Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        TextView txtViewDescr = (TextView) view.findViewById(R.id.txtViewDescr);
        txtViewDescr.setText(carro.desc);

        ImageView imgPlay = (ImageView) view.findViewById(R.id.play);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlVideo = carro.urlVideo;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(urlVideo), "video/*");
                startActivity(intent);
            }
        });

        MapaFragment mapaFragment = new MapaFragment();
        mapaFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.mapaFragment,mapaFragment).commit();

        return view;
    }
}
