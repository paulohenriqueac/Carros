package br.com.androidessencial.carros.service;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import br.com.androidessencial.carros.R;
import br.com.androidessencial.carros.domain.Carro;

public class CarroService {
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

            /*
              getElementsByTag retorna um NodeList com a tag especificada, sendo necessario
              pegar a primeira posição no .item(0), para então pegar o conteudo da tag
            */
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
}
