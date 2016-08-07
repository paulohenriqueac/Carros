package br.com.androidessencial.carros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.androidessencial.carros.CarrosApplication;
import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.ListaFragment;


public class BaseActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public  static final String TIPO = "Tipo";
    private static final String CLASSICOS = "Clássicos";
    private static final String ESPORTIVOS = "Esportivos";
    private static final String LUXO = "Luxo";

    // Configurar a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Configurar o Navigation Drawer
    protected void setupNavDrawer(){
        //Icone hamburguer na Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Obter o Header do Navigation View
        View viewNavDrawerHeader = navigationView.getHeaderView(0);

        TextView viewNome = (TextView) viewNavDrawerHeader.findViewById(R.id.textViewNome);
        viewNome.setText(CarrosApplication.usuario.getNome());

        TextView viewEmail = (TextView) viewNavDrawerHeader.findViewById(R.id.textViewEmail);
        viewEmail.setText(CarrosApplication.usuario.getEmail());

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        selecionarOpcaoMenu(menuItem);
                        return true;
                    }
                }
        );
    }

    private void selecionarOpcaoMenu(MenuItem menuItem) {
        // Seleciona o item de menu clicado
        menuItem.setChecked(true);
        //Fecha o menu
        drawerLayout.closeDrawers();
        //Tratar evento de click no menu

        switch (menuItem.getItemId()){
            case R.id.nav_item_carros_todos:
                break;
            case R.id.nav_item_carros_classicos:
                mostrarListaCarros(CLASSICOS);
                break;
            case R.id.nav_item_carros_esportivos:
                mostrarListaCarros(ESPORTIVOS);
                break;
            case R.id.nav_item_carros_luxo:
                mostrarListaCarros(LUXO);
                break;
            case R.id.nav_item_site_livro:
                startActivity(new Intent(this, SiteActivity.class));
                break;
            case R.id.nav_item_configuracoes:
                Toast.makeText(this, "Clicou em Configurações", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_sair:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void  mostrarListaCarros(String tipo){
        Intent intent = new Intent(this, ListaActivity.class);
        Bundle param = new Bundle();

        param.putString(TIPO, tipo);
        intent.putExtras(param);

        startActivity(intent);
    }
}
