package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.ActivityLoginBinding;
import com.kghapp.databinding.ActivitySignUpBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import static com.kghapp.others.Api.login;
import static com.kghapp.others.Api.signup;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
String stEmail="",stPassword="",getUserPassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
          getUserPassword = SharedHelper.getKey(getApplicationContext(), AppConstats.USERPASSWORD);
          String text = "<font color=#000>Forgot Password ?</font> <font color=#FF9801>Click here</font>";
            binding.txtforgotPass.setText(Html.fromHtml(text));

      /*  String textNew = "<font color=#000>New User?</font> <font color=#FF9801>Register here</font>";
        binding.txtNew.setText(Html.fromHtml(textNew));*/




        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        binding.txtforgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
            }
        });

        if (getUserPassword.equals("")){
          binding.etPassword.setHint("Password");
        }else {
            binding.cbRember.setChecked(true);
            binding.etPassword.setText(getUserPassword);
        }


        binding.cbRember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String getUserPassword=binding.etPassword.getText().toString().trim();
                    binding.etPassword.setText(getUserPassword);
                    SharedHelper.putKey(LoginActivity.this, AppConstats.USERPASSWORD,binding.etPassword.getText().toString().trim());


                }
            }
        });




        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stEmail=binding.etEmail.getText().toString().trim();
                stPassword=binding.etPassword.getText().toString().trim();


                if (stEmail.isEmpty()){
                    binding.etEmail.setError("Registered Email Address Must Required");
                }else if (stPassword.isEmpty()){
                    binding.etPassword.setError("Password Must Required");
                }
                else {
                    login();
                }


            }
        });

    }


    private  void  login(){
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, LoginActivity.this);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", login)
                .addBodyParameter("email", stEmail)
                .addBodyParameter("password", stPassword)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("grtgfnjgh", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")) {
                                String data= response.getString("data");
                                JSONObject jsonObject=new JSONObject(data);


                                SharedHelper.putKey(LoginActivity.this, AppConstats.UserEmail, jsonObject.getString("email"));
                                SharedHelper.putKey(LoginActivity.this, AppConstats.USERID, jsonObject.getString("userid"));
                                SharedHelper.putKey(LoginActivity.this, AppConstats.USERNAME, jsonObject.getString("name"));
                              /*  SharedHelper.putKey(LoginActivity.this, AppConstats.USERPASSWORD, jsonObject.getString("password"));*/
                                SharedHelper.putKey(LoginActivity.this, AppConstats.UserMobile, jsonObject.getString("mobile"));



                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("fhgfrnhgf", e.getMessage());
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fhgfrnhgf", anError.getMessage());
                        dialog.hideDialog();

                    }
                });


    }
}