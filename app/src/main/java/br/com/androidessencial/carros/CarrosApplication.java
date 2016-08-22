package br.com.androidessencial.carros;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import br.com.androidessencial.carros.domain.Usuario;

public class CarrosApplication extends Application{

    private CarrosApplication instance = null;
    public static Usuario usuario = null;

    public CarrosApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        //Salvar instancia para acesso Singleton
        instance = this;

        //Obter instancia do Usuário
        usuario = new Usuario();
    }

    public static boolean verificarConexao(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        } else {
            return false;
        }
    }

}
