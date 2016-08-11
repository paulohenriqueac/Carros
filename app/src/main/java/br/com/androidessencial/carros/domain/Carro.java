package br.com.androidessencial.carros.domain;

@org.parceler.Parcel
public class Carro{
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String posicao;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString(){
        return "Carro{" + "nome'" + nome + "\'" + "}";
    }

}
