package br.edu.ifsp.scl.sdm.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityActionBinding;
import br.edu.ifsp.scl.sdm.intents.databinding.ActivityMainBinding;

public class ActionActivity extends AppCompatActivity {
    private ActivityActionBinding activityActionBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityActionBinding = ActivityActionBinding.inflate(getLayoutInflater());
        setContentView(activityActionBinding.getRoot());

        Bundle parametro = getIntent().getExtras();
        if (parametro != null) {
            String parametroTelaPrincipal = parametro.getString("chave_parametro");
            activityActionBinding.parameterTv.setText(parametroTelaPrincipal);
        }



    }

    public void onClickBtnVoltar (View view) {

        Intent intentPrincipal = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intentPrincipal);
        this.finish();

    }
}