package com.application.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.application.login.model.SignUpData;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        TextView email=(TextView) findViewById(R.id.email);
        TextView password=(TextView) findViewById(R.id.password);

        AppCompatButton signIn=(AppCompatButton) findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if (email1 != null && !email1.isEmpty() && password1 != null && !password1.isEmpty()) {
                try {
                    SignUpData data = getIntent().getParcelableExtra("SignUpData");
                    String userEmail = data.getEmail();
                    String userPassword = data.getPassword();

                    if (userEmail.equals(email1) && userPassword.equals(password1)
                    ) {
                        Toast.makeText(SignIn.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "LOGIN FAILED!", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (NumberFormatException e){
                    Toast.makeText(SignIn.this, "Invalid data format", Toast.LENGTH_SHORT).show();
                }
                }
                else {
                    Toast.makeText(SignIn.this, "Please fill both the fields to login", Toast.LENGTH_SHORT).show();
                }
            }
                                     }
        );
    }

}