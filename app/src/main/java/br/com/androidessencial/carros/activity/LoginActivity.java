package br.com.androidessencial.carros.activity;

import android.os.Bundle;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.LoginFragment;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null){
            LoginFragment loginFragment = LoginFragment.novaInstancia();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_login_content, loginFragment, TAG)
                    .commit();
        }
    }
}
