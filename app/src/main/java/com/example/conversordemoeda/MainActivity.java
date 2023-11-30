package com.example.conversordemoeda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static EditText valor;
    private static TextView TextView;
    private static TextView res;

    private static double cotacao;

    @SuppressLint("DefaultLocale")
    private void setCot(){
        AsyncAtv atv = new AsyncAtv();
        CompletableFuture<String> cot =atv.getCotacao();

        try {
            cotacao = Double.parseDouble(cot.get());
            TextView.setText(String.format("R$ %.2f",cotacao));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor = findViewById(R.id.valor);
        TextView = findViewById(R.id.cot);
        res = findViewById(R.id.res);

        setCot();
    }

    @SuppressLint("DefaultLocale")
    public void calcular(View v){
        double val = Double.parseDouble(valor.getText().toString());

        res.setText(String.format("R$ %.2f", cotacao * val));
    }
}