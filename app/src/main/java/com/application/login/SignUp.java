package com.application.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.application.login.data.MyDbHandler;
import com.application.login.model.SignUpData;

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText email=(EditText) findViewById(R.id.email);
        EditText username=(EditText) findViewById(R.id.username);
        EditText password=(EditText) findViewById(R.id.password);

        AppCompatButton signUpBtn= (AppCompatButton) findViewById(R.id.signup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username1 = username.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                if (username1 != null && !username1.isEmpty() && email1 != null && !email1.isEmpty() && password1 != null && !password1.isEmpty()) {

                try {
                    SharedPreferences shrd = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();

                    editor.putString("str", username1);
                    editor.apply();
                    username.setText(username1);

//                RadioGroup radioGroup = findViewById(R.id.gender);
//                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
//
//                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
//                String gender = selectedRadioButton.getText().toString();

                    Toast.makeText(SignUp.this, "Username: " + username1 + "\nEmail: " + email1, Toast.LENGTH_SHORT).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Start the new activity after the delay
                            Intent intent = new Intent(SignUp.this, SignIn.class);
                            startActivity(intent);
                        }
                    }, 3000);

                    MyDbHandler db = new MyDbHandler(SignUp.this);
                    SignUpData user = new SignUpData();
                    user.setUsername(username1);
                    user.setEmail(email1);
                    user.setPassword(password1);

                    db.addUser(user);
                }
                catch (NumberFormatException e){
                    Toast.makeText(SignUp.this, "Invalid data type", Toast.LENGTH_SHORT).show();
                }


                }
                else {
                    Toast.makeText(SignUp.this, "Please fill all the fields above", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SharedPreferences getshared = getSharedPreferences("user", MODE_PRIVATE);
        String value=  getshared.getString("str",null);
        username.setText(value);

        }


}
