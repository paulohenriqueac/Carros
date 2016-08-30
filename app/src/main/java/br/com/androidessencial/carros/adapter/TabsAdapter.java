package br.com.androidessencial.carros.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.fragment.ListaFragment;

public class TabsAdapter extends FragmentPagerAdapter {
    private static final String TAG_CLASSICOS = "classicos";
    private static final String TAG_ESPORTIVOS = "esportivos";
    private static final String TAG_LUXO = "luxo";
    private static final String TAG_FAVORITOS = "favoritos";
    private Context context;

    public TabsAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount(){
        //Quantidade de páginas
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position){
        //Título mostrado nas Tabs
        switch (position){
            case 0:
                return context.getString(R.string.classicos);
            case 1:
                return context.getString(R.string.esportivos);
            case 2:
                return context.getString(R.string.luxo);
            default:
                return context.getString(R.string.favoritos);
        }
    }

    @Override
    public Fragment getItem(int position){
        //Cria o fragment para cada página
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = ListaFragment.novaInstancia(TAG_CLASSICOS);
                break;
            case 1:
                fragment = ListaFragment.novaInstancia(TAG_ESPORTIVOS);
                break;
            case 2:
                fragment = ListaFragment.novaInstancia(TAG_LUXO);
                break;
            default:
                fragment = ListaFragment.novaInstancia(TAG_FAVORITOS);
        }

        return fragment;
    }
}
