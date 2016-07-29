package br.com.androidessencial.carros.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.androidessencial.carros.CarrosApplication;
import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.activity.HomeActivity;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button btnEntrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextSenha = (EditText) view.findViewById(R.id.editTextSenha);
        btnEntrar = (Button) view.findViewById(R.id.buttonEntrar);

        btnEntrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        //if (!email.equals("paulohenrique_ac@hotmail.com")){
        //    editTextEmail.setError(getText(R.string.error_email));
        //    editTextEmail.requestFocus();
        //} else if (!senha.equals("123456")){
        //    editTextSenha.setError(getText(R.string.error_senha));
        //    editTextSenha.requestFocus();
        //else {
            CarrosApplication.usuario.setId(1000);
            CarrosApplication.usuario.setNome("Paulo Andrade");
            CarrosApplication.usuario.setEmail("paulohenrique_ac@hotmail.com");

            startActivity(new Intent(getContext(), HomeActivity.class));
            getActivity().finish();
        //}
    }
}