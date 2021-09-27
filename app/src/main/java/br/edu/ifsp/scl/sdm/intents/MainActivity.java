package br.edu.ifsp.scl.sdm.intents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String numero = activityMainBinding.parameterEt.getText().toString();
        Uri uri = Uri.parse("tel:" + numero);

        switch (item.getItemId()) {
            // chamar número informado
            case R.id.callMi:
                Intent ligacaoIntent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                    return true;
                }
                startActivity(ligacaoIntent);
                return true;
            // abrir discador com número passado
            case R.id.dialMi:
                Intent chamadorIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(chamadorIntent);
                return true;
            // abrir navegador
            case R.id.viewMi:
                Uri url = Uri.parse("http://" + numero);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(browserIntent);
            // abrir outra activity
            case R.id.actionMi:
                Intent i = new Intent(MainActivity.this,ActionActivity.class);
                Bundle parametro = new Bundle();

                parametro.putString("chave_parametro", numero);

                i.putExtras(parametro);

                startActivity(i);
            // fechar app-
            case R.id.exitMi:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}