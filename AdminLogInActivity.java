package com.shopping.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogInActivity extends AppCompatActivity
{
    EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView  NotAdminLink;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);
        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        NotAdminLink=findViewById(R.id.not);
        loadingBar = new ProgressDialog(this);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Login();

            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(AdminLogInActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void Login()
    {

        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(AdminLogInActivity.this, "Plz enter Data", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(AdminLogInActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            if(phone.equals("8053977266") && password.equals("2626"))
            {
                loadingBar.dismiss();
                Intent intent=new Intent(AdminLogInActivity.this,AdminCategoryActivity.class);
                startActivity(intent);
                InputPhoneNumber.setText("");
                InputPassword.setText("");
            }
            else
            {
                loadingBar.dismiss();
                Toast.makeText(AdminLogInActivity.this, "Bad Credential", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
