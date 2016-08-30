package br.com.androidessencial.carros;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import br.com.androidessencial.carros.domain.Usuario;

public class CarrosApplication extends Application{

    private CarrosApplication instance = null;
    public static Usuario usuario = null;

    public static final String TAG_CARROS = "carros";
    public static final String TAG_CLASSICOS = "classicos";
    public static final String TAG_ESPORTIVOS = "esportivos";
    public static final String TAG_LUXO = "luxo";
    public static final String TAG_FAVORITOS = "favoritos";

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

        //WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        //Toast.makeText(context, "Conectado á: " + connectionInfo.getSSID(), Toast.LENGTH_SHORT).show();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        } else {
            return false;
        }
    }

}
