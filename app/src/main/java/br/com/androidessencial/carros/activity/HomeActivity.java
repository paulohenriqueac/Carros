package br.com.androidessencial.carros.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.adapter.TabsAdapter;
import br.com.androidessencial.carros.dialog.SobreDialog;


public class HomeActivity extends BaseActivity {
    private static final String IND_TAB = "indTab";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        setupNavDrawer();
        setupViewPager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            Context context = getBaseContext();

            @Override
            public void onClick(View view){
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();

                Toast.makeText(context, "Conectado á: " + connectionInfo.getSSID(), Toast.LENGTH_SHORT).show();
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
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new TabsAdapter(getBaseContext(), getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        final SharedPreferences pref = getBaseContext().getSharedPreferences("carros", 0);
        final int indTab = pref.getInt(IND_TAB, 0);

        viewPager.setCurrentItem(indTab);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt(IND_TAB, viewPager.getCurrentItem());
                editor.commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
