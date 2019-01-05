package com.tecsup.elazaro.t1_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrese;
    Button btnLoguin;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrese = (Button) findViewById(R.id.btn_registrese);
        btnRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });


        btnLoguin = (Button) findViewById(R.id.btn_loguin);
        btnLoguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtemail = (EditText) findViewById(R.id.txt_email);
                EditText txtpass = (EditText) findViewById(R.id.txt_password);

                try {
                    Cursor cursor = helper.ConsultarUsuarioPas
                            (txtemail.getText().toString(), txtpass.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "usuario y/o Pass Incorrectos", Toast.LENGTH_LONG).show();
                    }
                    txtemail.setText(""); //vaciando caja de texto
                    txtpass.setText("");
                    txtemail.findFocus();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}