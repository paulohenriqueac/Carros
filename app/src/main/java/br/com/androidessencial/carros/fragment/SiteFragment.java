package br.com.androidessencial.carros.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.androidessencial.carros.R;

public class SiteFragment extends Fragment {
    private static final String URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm";
    private WebView webView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site, container, false);

        webView = (WebView) view.findViewById(R.id.webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);

        //Carregar pagina
        setWebViewClient(webView);
        webView.loadUrl(URL_SOBRE);

        //Refresh
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(OnRefreshListener());

        return view;
    }

    public void setWebViewClient(WebView webViewClient) {
        webViewClient.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon){
                super.onPageStarted(webview, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webview, String url){
                progressBar.setVisibility(View.INVISIBLE);
                swipeLayout.setRefreshing(false);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener(){
        return new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                webView.reload();
            }
        };
    }
}
