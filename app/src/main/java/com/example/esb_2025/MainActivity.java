package com.example.esb_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Récupérer les boutons par son ID
        Button btnconnect1java = findViewById(R.id.btnt);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        // 2. Ajouter un écouteur d'événements onclickListener au boutons
        btnconnect1java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code à exécuter lorsque le bouton est cliqué
                EditText userjava = (EditText) findViewById(R.id.editTextfullname);
                EditText pwdjava = (EditText) findViewById(R.id.editTextPassword);
                String userstr = userjava.getText().toString();
                String pwdstr = pwdjava.getText().toString();
                TextView txtuserjava = (TextView) findViewById(R.id.txtusername);
                TextView txtpwdjava = (TextView) findViewById(R.id.txtpwd);

                Boolean valid = Boolean.TRUE;
                if (userstr.matches("")) {
                    txtuserjava.setText(R.string.log_err_requise);
                    valid = Boolean.FALSE;
                } else {
                    txtuserjava.setText("");
                }

                if (pwdstr.matches("")) {
                    txtpwdjava.setText(R.string.log_err_requise);
                    valid = Boolean.FALSE;
                } else {
                    txtpwdjava.setText("");
                }

                if (valid) {
                    User u1 = new User();
                    u1.setUsername(userstr);
                    u1.setPwd(pwdstr);
                    UserDB userdb1 = new UserDB(view.getContext());
                    userdb1.open();
                    int userexist = userdb1.getUserWithlog(u1);

                    userdb1.close();

                    if (userexist > 0) {
                        Intent dashboardActivity = new Intent(MainActivity.this, Dashboard.class);
                        dashboardActivity.putExtra("user", u1.getUsername());
                        startActivity(dashboardActivity);
                        finish();
                    } else {
                        TextView txtformaljava = (TextView) findViewById(R.id.txtformal);
                        txtformaljava.setText(R.string.log_err_cnx);
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}