package mx.edu.itsmt.ejemplo_bd2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity
        implements View.OnClickListener {

    Button registrar,consultar,listar;

    public  void vincular() {
        registrar = findViewById(R.id.btregistrar);
        consultar = findViewById(R.id.btconsultar);
        listar = findViewById(R.id.btlistar);
        registrar.setOnClickListener(this);
        consultar.setOnClickListener(this);
        listar.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vincular();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btregistrar:
                intent = new Intent(MenuActivity.this,
                        ContenedorActivity.class);
                intent.putExtra("boton",1);
                startActivity(intent);
                break;
            case R.id.btconsultar:
                intent = new Intent(MenuActivity.this,
                        ContenedorActivity.class);
                intent.putExtra("boton",2);
                startActivity(intent);
                break;
            case R.id.btlistar:
                intent = new Intent(MenuActivity.this,
                        ContenedorActivity.class);
                intent.putExtra("boton",3);
                startActivity(intent);
                break;

        }
    }//fin onclick
}
