package br.com.androidessencial.carros.service;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class CarroService {

    public static List<Carro> getCarros(Context context, String tipo) throws IOException, JSONException {
        List<Carro> carros = new ArrayList<Carro>();

        JSONObject jsonObject = getJSONObject(context, tipo);

        try {
            JSONArray jsonCarros = jsonObject.getJSONArray("carros");
            Carro c = new Carro();

            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jCarro = jsonCarros.getJSONObject(i);

                c.nome = jCarro.getString("nome");
                c.desc = jCarro.getString("desc");
                c.urlInfo = jCarro.getString("url_info");
                c.urlFoto = jCarro.getString("url_foto");
                c.urlVideo = jCarro.getString("url_video");
                c.longitude = jCarro.getString("longitude");
                c.latitude = jCarro.getString("latitude");

                carros.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return carros;
    }

    private static JSONObject getJSONObject(Context context, String tipo)  throws IOException, JSONException {
        InputStream is = null;
        JSONObject jsonObject = null;

        Resources resources = context.getResources();

        if (tipo.equals(context.getString(R.string.classicos))) {
            is = resources.openRawResource(R.raw.carros_classicos);
        } else if (tipo.equals(context.getString(R.string.esportivos))) {
            is = resources.openRawResource(R.raw.carros_esportivos);
        } else if (tipo.equals(context.getString(R.string.luxo))) {
            is = resources.openRawResource(R.raw.carros_luxo);
        }

        String string = bytesParaString(is);

        try {
            jsonObject = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] b = new byte[1024];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesLidos;

        while((bytesLidos = is.read(b)) != -1) {
            buffer.write(b, 0, bytesLidos);
        }

        return new String(buffer.toByteArray(), "UTF-8");
    }
}








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
            Carro c = new Carro();

            //Aqui eu tenho o nó na posicao i que representa um carro da lista
            Node node = nodeList.item(i);
            Element element = (Element) node;


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
