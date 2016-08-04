package br.com.androidessencial.carros.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.ListaFragment;

public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;

    public TabsAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount(){
        //Quantidade de páginas
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        //Título mostrado nas Tabs
        switch (position){
            case 0:
                return context.getString(R.string.classicos);
            case 1:
                return context.getString(R.string.esportivos);
            default:
                return context.getString(R.string.luxo);
        }
    }

    @Override
    public Fragment getItem(int position){
        //Cria o fragment para cada página
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = ListaFragment.novaInstancia(context.getString(R.string.classicos));
                break;
            case 1:
                fragment = ListaFragment.novaInstancia(context.getString(R.string.esportivos));
                break;
            default:
                fragment = ListaFragment.novaInstancia(context.getString(R.string.luxo));
                break;
        }

        return fragment;
    }
}
