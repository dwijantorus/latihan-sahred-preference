package com.example.latihansharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.latihansharedpreference.databinding.ActivityLoginBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username, password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.edUsername.getText().toString();
                password = binding.edPassword.getText().toString();

                if(formIsEmpty()){
                    Toast.makeText(LoginActivity.this, getString(R.string.error_field_required), Toast.LENGTH_SHORT).show();
                }else{
                    //Login Process
                    loginProcess();
                }
            }
        });
    }

    private boolean formIsEmpty(){
        return username.isEmpty() || password.isEmpty();
    }


    private void loginProcess(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseApi> call = service.login(username,password);
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                progressDialog.dismiss();
                boolean status = response.body().isStatus();
                toastMessage(response.body().getMessage());
                if(status)
                    goToProfileActivity();
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                progressDialog.dismiss();
                toastMessage("Something went wrong...Please try later!");
            }
        });
    }

    private void goToProfileActivity(){
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void toastMessage(String value){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}