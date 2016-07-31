package br.com.androidessencial.carros.domain;

import java.io.Serializable;

public class Carro implements Serializable {
    private long id;
    private String tipo;
    private String nome;
    private String desc;
    private String urlFoto;
    private String urlInfo;
    private String urlVideo;
    private String latitude;
    private String longitude;

    public void setId(long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public String getUrlInfo() {
        return urlInfo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString(){
        return "Carro{" + "nome'" + nome + "\'" + "}";
    }
}
