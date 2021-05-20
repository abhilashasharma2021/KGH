package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.ActivitySignUpBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kghapp.others.Api.signup;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
String stMobile="",stEmail="",stFullName="",stPassword="",stConfirm="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivitySignUpBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       /* String textNew = "<font color=#000>Already a User?</font> <font color=#FF9801>Login Now</font>";
        binding.txtAlready.setText(Html.fromHtml(textNew));*/


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stFullName = binding.etFull.getText().toString().trim();
                stMobile = binding.etMobile.getText().toString().trim();
                stPassword = binding.etPassword.getText().toString().trim();
                stEmail = binding.etEmail.getText().toString().trim();
                stConfirm = binding.etRePass.getText().toString().trim();


                if (validation()){
                    /*call api*/
                    if (!validateEmailAddress(stEmail)) {
                        Toast.makeText(SignUpActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (!stConfirm.equals(stPassword)){
                            Toast.makeText(SignUpActivity.this, "Password and Confirm password Not Matched !! Inser same password", Toast.LENGTH_SHORT).show();
                        }else {
                            signUp();
                        }

                    }

                }

                else {
                    validation();
                }
            }
        });
    }


    public static boolean validateEmailAddress(String stEmail)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stEmail);
        return matcher.matches();
    }

    private Boolean validation(){
        Boolean boolen=false;
        if (binding.etFull.getText().toString().isEmpty()){

            binding.etFull.setError("Full Name Must Required");
        }
        else if (binding.etEmail.getText().toString().isEmpty()){

            binding.etEmail.setError("Email Address Must Required");
        }else  if (binding.etMobile.getText().toString().isEmpty()){

            binding.etMobile.setError("Mobile Number Must Required");
        }
        else  if (stMobile.length()<10){

            binding.etMobile.setError("Please enter atleast 10 digit mobile number");
        }

        else if (binding.etPassword.getText().toString().isEmpty()){

            binding.etPassword.setError("Password Must Required");
        }

        else if (binding.etRePass.getText().toString().isEmpty()){

            binding.etRePass.setError("Confirm Password Must Required");
        }
        else {
            boolen=true;

        }
        return boolen;
    }

    private  void  signUp(){
      CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, SignUpActivity.this);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", signup)
                .addBodyParameter("mobile", stMobile)
                .addBodyParameter("name", stFullName)
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


                                SharedHelper.putKey(SignUpActivity.this, AppConstats.UserEmail, jsonObject.getString("email"));
                                SharedHelper.putKey(SignUpActivity.this, AppConstats.USERID, jsonObject.getString("userid"));
                                SharedHelper.putKey(SignUpActivity.this, AppConstats.USERNAME, jsonObject.getString("name"));
                                SharedHelper.putKey(SignUpActivity.this, AppConstats.UserMobile, jsonObject.getString("mobile"));



                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                                Toast.makeText(SignUpActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(SignUpActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                              dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("sdgbvdfbh", e.getMessage());
                           dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("gbftn", anError.getMessage());
                       dialog.hideDialog();

                    }
                });


    }

    }