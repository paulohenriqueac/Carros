package br.com.androidessencial.carros;


import android.app.Application;
import android.util.Log;

import br.com.androidessencial.carros.domain.Usuario;

public class CarrosApplication extends Application{

    private static final String TAG = "CarrosApplication";
    private CarrosApplication instance = null;
    public static Usuario usuario = null;

    public CarrosApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Log.d(TAG, "CarroApplication.onCreate()");

        //Salvar instancia para acesso Singleton
        instance = this;

        //Obter instancia do Usuário
        usuario = new Usuario();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();

        Log.d(TAG, "CarrosApplication.onTerminate");
    }
}
