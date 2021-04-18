package com.ankita.dell.chit_chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    static final String APP_ID="76929";
    static final String AUTH_KEY="xjKKGOTESELPZEE";
    static final String AUTH_SECRET="tTgbBfZY4-j6DO9";
    static final String ACCOUNT_KEY="2xGuv-Z33QKsJbesuFcv";

    Button btnLogin,btnSignup;
    EditText edtUser,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFramework();

        btnLogin=(Button)findViewById(R.id.main_btnLogin);
        btnSignup=(Button)findViewById(R.id.main_btnSignup);

        edtUser=(EditText)findViewById(R.id.main_editLogin);
        edtPassword=(EditText)findViewById(R.id.main_editPassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=edtUser.getText().toString();
                final String password=edtPassword.getText().toString();

                QBUser qbUser=new QBUser(user,password);

                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(MainActivity.this,ChatDialogsActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("password",password);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getApplicationContext(),"Login failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void initializeFramework() {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }
}
