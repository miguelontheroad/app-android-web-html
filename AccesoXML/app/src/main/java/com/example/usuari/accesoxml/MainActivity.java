package com.example.usuari.accesoxml;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    TextView tvDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDatos = (TextView)this.findViewById(R.id.tvDatos);
    }

    public void RecogerDatos(View v){
        CommunicationTask ct = new CommunicationTask();
        ct.execute("http://www.aemet.es/xml/municipios/localidad_28079.xml");
    }

    //Creamos una tarea asíncrona
    private class CommunicationTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvDatos.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            try {
                URL url = new URL(strings[0]);
                URLConnection conx = url.openConnection();
                InputStream is = conx.getInputStream();
                Document doc;

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dbuilder;
                dbuilder = factory.newDocumentBuilder();
                doc = dbuilder.parse(is);


                NodeList listaNombre =
                        doc.getElementsByTagName("nombre");
                resultado+="Localidad: " +listaNombre.item(0).getTextContent()+"\n";

                NodeList tempMaxima =
                        doc.getElementsByTagName("maxima");

                NodeList tempMinima =
                        doc.getElementsByTagName("minima");

                NodeList listaPrecipitaciones =
                        doc.getElementsByTagName("prob_precipitacion");

                double tempMax = 0;
                for (int s = 0; s < tempMaxima.getLength(); s++) {
                    String val = tempMaxima.item(s).getTextContent();
                        if (val==null||val.equals(""))
                            val = "0";
                    tempMax+=Double.parseDouble(val);
                }

                double tempMin = 0;
                for (int z = 0; z < tempMinima.getLength(); z++) {
                    String valo = tempMinima.item(z).getTextContent();
                        if (valo==null||valo.equals(""))
                            valo = "0";
                    tempMin+=Double.parseDouble(valo);
                }

                double media = 0;
                for (int i = 0; i < listaPrecipitaciones.getLength(); i++) {
                    String valor = listaPrecipitaciones.item(i).getTextContent();
                        if (valor==null||valor.equals(""))
                            valor = "0";
                    media+=Double.parseDouble(valor);
                }
                media = media / listaPrecipitaciones.getLength();

                tempMin = tempMin / tempMinima.getLength();

                tempMax = tempMax / tempMaxima.getLength();

                resultado+="Media probabilidad precipitaciones : "+media + "\n" + "Temperatura máxima media : "+ tempMax +
                    "\n" + "Temperatura mínima media : " + tempMin;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return resultado;
        }
    }


}
