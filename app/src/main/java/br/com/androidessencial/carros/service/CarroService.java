package br.com.androidessencial.carros.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.androidessencial.carros.domain.Carro;

public class CarroService {
    public static List<Carro> getCarros(Context context, String tipo){
        List<Carro> carros = new ArrayList<Carro>();

        for (int i = 0; i < 20; i++){
            Carro c = new Carro();

            // Nome Dinâmico conforme o tipo escolhido
            c.modelo = "Carro " + tipo + ": " + i;
            c.desc = "Desc " + i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";

            carros.add(c);
        }

        return carros;
    }
}
