package br.com.androidessencial.carros.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarrosViewHolder> {
    private final Context context;
    private final List<Carro> carros;
    private final CarroOnClickListener carroOnClickListener;

    public CarroAdapter(Context context, List<Carro> carros, CarroOnClickListener carroOnClickListener){
        this.context = context;
        this.carros = carros;
        this.carroOnClickListener = carroOnClickListener;
    }

    @Override
    public int getItemCount(){
        return this.carros != null ? this.carros.size() : 0;
    }

    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carros, viewGroup, false);
        CarrosViewHolder holder = new CarrosViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position){
        Carro c = carros.get(position);

        holder.nome.setText(c.getNome());
        holder.progressBar.setVisibility(View.VISIBLE);

        Picasso.with(context).load(c.getUrlFoto()).fit().into(holder.imagem,
            new com.squareup.picasso.Callback(){
                @Override
                public void onSuccess(){
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(){
                    holder.progressBar.setVisibility(View.GONE);
                }
            });

        if (carroOnClickListener != null){
            holder.itemView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        carroOnClickListener.onClickCarro(holder.itemView, position);
                    }
                });
        }
    }

    public interface CarroOnClickListener{
        public void onClickCarro(View view, int position);
    }

    public static class CarrosViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        ImageView imagem;
        ProgressBar progressBar;
        CardView cardView;

        public CarrosViewHolder(View view){
            super(view);
            nome = (TextView) view.findViewById(R.id.textNomeCarro);
            imagem = (ImageView) view.findViewById(R.id.imageCarro);
            progressBar = (ProgressBar) view.findViewById(R.id.progress);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
