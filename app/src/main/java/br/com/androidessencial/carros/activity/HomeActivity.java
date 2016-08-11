package br.com.androidessencial.carros.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.adapter.TabsAdapter;
import br.com.androidessencial.carros.dialog.SobreDialog;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        setupNavDrawer();
        setupViewPager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view, "Teste Coordinator, FAB e Snackbar ", Snackbar.LENGTH_SHORT).show();
            }
        });
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

    private void setupViewPager(){
        //ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getBaseContext(), getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
