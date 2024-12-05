package com.example.esb_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnlogin = findViewById(R.id.btnlogin);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.editTextemail);
                EditText username = (EditText) findViewById(R.id.editTextusername);
                EditText password = (EditText) findViewById(R.id.editTextpwd);
                String emailstr = email.getText().toString();
                String usernamestr = username.getText().toString();
                String pwdstr = password.getText().toString();
                TextView txtemail = (TextView) findViewById(R.id.txtemail);
                TextView txtusername = (TextView) findViewById(R.id.txtusername);
                TextView txtpwd = (TextView) findViewById(R.id.txtpwd);

                Boolean valid = Boolean.TRUE;
                if (emailstr.matches("")) {
                    txtemail.setText(R.string.log_err_requise);
                    valid = Boolean.FALSE;
                }
                //test @ prensence
                else if (!emailstr.contains("@")) {
                    txtemail.setText(R.string.log_err_email);
                    valid = Boolean.FALSE;
                } else {
                    txtemail.setText("");
                }

                if (usernamestr.matches("")) {
                    txtusername.setText(R.string.log_err_requise);
                    valid = Boolean.FALSE;
                } else {
                    txtusername.setText("");
                }

                if (pwdstr.matches("")) {
                    txtpwd.setText(R.string.log_err_requise);
                    valid = Boolean.FALSE;
                } else {
                    txtpwd.setText("");
                }

                if (valid) {
                    User u1 = new User();
                    u1.setEmail(emailstr);
                    u1.setUsername(usernamestr);
                    u1.setPwd(pwdstr);

                    UserDB userdb1 = new UserDB(view.getContext());
                    userdb1.open();

                    // Vérifiez si l'utilisateur existe déjà
                    int userexist = userdb1.getUserWithlog(u1);
                    // Si l'utilisateur n'existe pas, insérez-le dans la base de
                    // données sinon message d'érreur est afficher
                    if (userexist > 0) {
                        TextView txtformal = (TextView) findViewById(R.id.txtformal);
                        txtformal.setText(R.string.signup_exist);
                    } else {
                        //ajouter l'utilisateur dans la base de données
                        userdb1.insertUser(u1);
                        //redirect vers dashboard
                        Intent dashboardActivity = new Intent(SignUpActivity.this, Dashboard.class);
                        dashboardActivity.putExtra("user", u1.getUsername());
                        startActivity(dashboardActivity);
                        finish();
                        userdb1.close();
                    }
                }
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}