package com.indocyber.mvvmindocyber.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.indocyber.mvvmindocyber.MainActivity;
import com.indocyber.mvvmindocyber.model.ResponseLogin;
import com.indocyber.mvvmindocyber.network.InitRetrofit;
import com.indocyber.mvvmindocyber.network.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    Context mcontext;
    public ObservableInt loginView;
    public ObservableInt progressBar;

    public LoginViewModel(Context context) {
        this.mcontext = context;
        progressBar = new ObservableInt(View.GONE);
        loginView = new ObservableInt(View.VISIBLE);
    }

    public void sendLoginRequest(final String email, final String password) {
        progressBar.set(View.VISIBLE);
        loginView.set(View.GONE);

        RestApi api = InitRetrofit.getInstance();
        Call<ResponseLogin> loginCall = api.loginUser(
                email, password
        );
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    progressBar.set(View.GONE);
                    loginView.set(View.VISIBLE);

                    String result = response.body().getResponse();

                    if (result.equals("success")) {
                        mcontext.startActivity(new Intent(mcontext, MainActivity.class));
                    } else if (result.equals("failed")) {
                        progressBar.set(View.GONE);
                        loginView.set(View.VISIBLE);
                        Toast.makeText(mcontext, "Email dan Password Anda invalid", Toast.LENGTH_SHORT).show();
                    } else {
                        progressBar.set(View.GONE);
                        loginView.set(View.VISIBLE);
                        Toast.makeText(mcontext, "Cek koneksi Anda", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(mcontext, "Silahkan cek koneksi Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
