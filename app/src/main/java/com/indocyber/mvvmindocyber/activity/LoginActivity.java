package com.indocyber.mvvmindocyber.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andreabaccega.widget.FormEditText;
import com.indocyber.mvvmindocyber.MainActivity;
import com.indocyber.mvvmindocyber.R;
import com.indocyber.mvvmindocyber.databinding.ActivityLoginBinding;
import com.indocyber.mvvmindocyber.view.LoginView;
import com.indocyber.mvvmindocyber.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        activityLoginBinding.setLoginview(loginViewModel);

        activityLoginBinding.setPresenter(new LoginView() {
            @Override
            public void LoginData() {
                FormEditText fdtEmail = (FormEditText) findViewById(R.id.et_email);
                FormEditText fdtPassword = (FormEditText) findViewById(R.id.password);

                final String email = activityLoginBinding.etEmail.getText().toString();
                final String password = activityLoginBinding.password.getText().toString();

                FormEditText[] allFields = {fdtEmail, fdtPassword};
                boolean allValid = true;
                for (FormEditText field : allFields) {
                    allValid = field.testValidity() && allValid;
                }

                if (allValid) {
                    loginViewModel.sendLoginRequest(email, password);

                    //ere
                }
            }
        });

    }
}
