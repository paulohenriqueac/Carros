package br.com.androidessencial.carros.service;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class CarroService {

    public static List<Carro> getCarros(Context context, String tipo) throws IOException, JSONException {
        List<Carro> carros = new ArrayList<Carro>();

        JSONObject jsonRoot = getJSONObject(context, tipo);
        JSONObject jCarros = jsonRoot.getJSONObject("carros");

        JSONArray jsonArray = jCarros.getJSONArray("carro");

        for (int i = 0; i < jsonArray.length(); i++) {
            Carro c = new Carro();

            JSONObject jo = jsonArray.getJSONObject(i);
            c.nome = jo.getString("nome");
            c.desc = jo.getString("desc");
            c.urlInfo = jo.getString("url_info");
            c.urlFoto = jo.getString("url_foto");
            c.urlVideo = jo.getString("url_video");
            c.latitude = jo.getString("latitude");
            c.longitude = jo.getString("longitude");

            carros.add(c);
        }

        return carros;
    }

    private static JSONObject getJSONObject(Context context, String tipo)  throws IOException, JSONException {
        final int TIMEOUT_MILLIS = 15000;
        String url_string = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
        URL url = new URL(url_string.replace("{tipo}", tipo));

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(TIMEOUT_MILLIS);
        urlConnection.setReadTimeout(TIMEOUT_MILLIS);
        urlConnection.connect();

        InputStream is = null;

        try {
            is = new BufferedInputStream(urlConnection.getInputStream());
        } finally {
            urlConnection.disconnect();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is,  "utf-8"));

        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }

        String result = sb.toString();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jsonObject;
    }
}










/* EXEMPLO DE LEITURA DE ARQUIVO JSON */
/*public class CarroService {

    public static List<Carro> getCarros(Context context, String tipo) throws IOException, JSONException {
        List<Carro> carros = new ArrayList<Carro>();

        JSONObject jsonRoot = getJSONObject(context, tipo);
        JSONObject jCarros = jsonRoot.getJSONObject("carros");

        JSONArray jsonArray = jCarros.getJSONArray("carro");

        for (int i = 0; i < jsonArray.length(); i++) {
            Carro c = new Carro();

            JSONObject jo = jsonArray.getJSONObject(i);
            c.nome = jo.getString("nome");
            c.desc = jo.getString("desc");
            c.urlInfo = jo.getString("url_info");
            c.urlFoto = jo.getString("url_foto");
            c.urlVideo = jo.getString("url_video");
            c.latitude = jo.getString("latitude");
            c.longitude = jo.getString("longitude");

            carros.add(c);
        }

        return carros;
    }

    private static JSONObject getJSONObject(Context context, String tipo)  throws IOException, JSONException {
        InputStream is = null;

        Resources resources = context.getResources();

        if (tipo.equals(context.getString(R.string.classicos))) {
            is = resources.openRawResource(R.raw.carros_classicos);
        } else if (tipo.equals(context.getString(R.string.esportivos))) {
            is = resources.openRawResource(R.raw.carros_esportivos);
        } else if (tipo.equals(context.getString(R.string.luxo))) {
            is = resources.openRawResource(R.raw.carros_luxo);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is,  "utf-8"));

        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }

        String result = sb.toString();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jsonObject;
    }
}*/



/* EXEMPLO DE LEITURA DE ARQUIVO XML */

/*public class CarroService {
    public static List<Carro> getCarros(Context context, String tipo) {
        List<Carro> carros = new ArrayList<Carro>();

        Resources resources = context.getResources();
        InputStream is = null;

        if (tipo.equals(context.getString(R.string.classicos))) {
            is = resources.openRawResource(R.raw.carros_classicos);
        } else if (tipo.equals(context.getString(R.string.esportivos))) {
            is = resources.openRawResource(R.raw.carros_esportivos);
        } else if (tipo.equals(context.getString(R.string.luxo))) {
            is = resources.openRawResource(R.raw.carros_luxo);
        }

        Document document = parseXmlDom(is);

        // Aqui eu tenho a lista com todos os carros
        NodeList nodeList = document.getElementsByTagName("carro");

        for (int i = 0; i < nodeList.getLength(); i++){
            //Aqui eu tenho o nó na posicao i que representa um carro da lista
            Node node = nodeList.item(i);
            Element element = (Element) node;

            Carro c = new Carro();

            // getElementsByTag retorna um NodeList com a tag especificada, sendo necessario
            // pegar a primeira posição no .item(0), para então pegar o conteudo da tag

            c.nome = element.getElementsByTagName("nome").item(0).getTextContent();
            c.desc = element.getElementsByTagName("desc").item(0).getTextContent();
            c.urlInfo = element.getElementsByTagName("url_info").item(0).getTextContent();
            c.urlFoto = element.getElementsByTagName("url_foto").item(0).getTextContent();
            c.urlVideo = element.getElementsByTagName("url_video").item(0).getTextContent();
            c.latitude = element.getElementsByTagName("latitude").item(0).getTextContent().toString();
            c.longitude = element.getElementsByTagName("longitude").item(0).getTextContent().toString();

            carros.add(c);
        }

        return carros;
    }

    private static Document parseXmlDom(InputStream is) {

        Document document = null;

        try {
            DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            // parsing the XML file
            document = builder.parse(is);
            document.normalize();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return document;
    }
}*/
