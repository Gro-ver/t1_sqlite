package com.tecsup.elazaro.t1_sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class RegistroActivity extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNomUsu, txtDisUsu, txtCorUsu,txtPasUsu;

    //crear instancia de l base de datos
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"DB1",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Enlazar controles fisicos con variables declaradas arriba
        btnGrabarUsu=(Button)findViewById(R.id.btn_registrar);
        txtNomUsu=(EditText)findViewById(R.id.txt_nombre_usu);
        txtDisUsu=(EditText)findViewById(R.id.txt_distrito_usu);
        txtCorUsu=(EditText)findViewById(R.id.txt_correo_usu);
        txtPasUsu=(EditText)findViewById(R.id.txt_password_usu);

        //implementar el evento click del boton
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir la base de datos
                helper.abrirdb();
                helper.insertarReg(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtDisUsu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtPasUsu.getText()));
                helper.cerrardb();

                Toast.makeText(getApplicationContext(),"Registro almacenado con exito"
                        ,Toast.LENGTH_LONG).show();

                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
