package br.com.androidessencial.carros.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import br.com.androidessencial.carros.R;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        setupNavDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
