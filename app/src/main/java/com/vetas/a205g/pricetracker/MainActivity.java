package com.vetas.a205g.pricetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.*;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button searchButton = (Button)findViewById(R.id.search_button);
        final EditText productInput = (EditText)findViewById(R.id.product_input);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                searchButton.setText(search());
            }
        });
    }
    public static String savePage(final String URL) throws IOException {
        String line = "", all = "";
        URL myUrl = null;
        BufferedReader in = null;
        try {
            myUrl = new URL(URL);
            in = new BufferedReader(new InputStreamReader(myUrl.openStream()));

            while ((line = in.readLine()) != null) {
                all += line;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return all;
    }
    protected String search(){
        try{Pattern p = Pattern.compile("price\"><b>\\$(\\d+\\.\\d+)");
        Matcher m = p.matcher(savePage("http://www.google.com/search?q=mouse&oq=mouse#q=mouse&safe=strict&tbm=shop"));
            m.find();
        return m.group(1);}
        catch(Exception e){return "error";}
    }

}
