package br.com.androidessencial.carros.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.dialog.SobreDialog;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        setupNavDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_configuracoes:
                Toast.makeText(this,"Clicou no Menu Configurações", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_sobre:
                SobreDialog.showSobreDialog(getSupportFragmentManager());
                break;
        }
        return true;
    }
}
