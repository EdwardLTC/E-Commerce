package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.myapplication.AppCustomer.views.MainActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button btSignup;
    private EditText edtname;
    private EditText edtEmailsignup;
    private EditText edtPassword;
    private TextView loi3 ,loi4,loi5,txtlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btSignup = findViewById(R.id.btSignUp);
        edtname =findViewById(R.id.edtName);
        edtEmailsignup = findViewById(R.id.edtEmailSignup);
        edtPassword = findViewById(R.id.edtPassword);
        loi3=findViewById(R.id.textView18);
        loi4=findViewById(R.id.textView19);
        loi5=findViewById(R.id.textView20);
        txtlogin=findViewById(R.id.txtlogin);

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = edtEmailsignup.getText().toString();
                String password = edtPassword.getText().toString();
                String name = edtname.getText().toString();

                if (name.trim().equals("")) {
//                    loi3.setError("");
//                    loi3.setText("UserName cannot be blank!");
                    Toast.makeText(SignUpActivity.this, "UserName cannot be blank!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();

                }


                if (emaill.trim().equals("")) {
//                    loi4.setError("");
//                    loi4.setText("Email cannot be blank!");
                    Toast.makeText(SignUpActivity.this, "Email cannot be blank!", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(emaill)) {
                    Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();

                } else {
                    User user = new User();
                    user.setEmail(edtEmailsignup.getText().toString());
                    user.setName(edtname.getText().toString());
                    user.setPass(edtPassword.getText().toString());
                }
                if (password.trim().equals("")) {
//                    loi5.setError("");
//                    loi5.setText("PassWord cannot be blank!");
                    Toast.makeText(SignUpActivity.this, "PassWord cannot be blank!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}