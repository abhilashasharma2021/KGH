package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.ActivityForgotPassBinding;
import com.kghapp.databinding.ActivityLoginBinding;
import com.kghapp.others.Api;
import com.kghapp.others.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

import static com.kghapp.others.Api.forget_password;

public class ForgotPassActivity extends AppCompatActivity {
    ActivityForgotPassBinding binding;
    String  stEmail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgotPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stEmail=binding.etEmail.getText().toString().trim();

                if (validation()){

                    forgot_password(stEmail);

                }
                else {
                    validation();
                }


            }
        });

    }

    private Boolean validation(){

        Boolean aBoolean=false;
        if (binding.etEmail.getText().toString().isEmpty()){

            binding.etEmail.setError("Registered Email Address Must Required");
        }
        else {
            aBoolean=true;
        }

        return aBoolean;

    }

    public void forgot_password(String stEmail){
        CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", forget_password)
                .addBodyParameter("email", stEmail)
                .setTag("mail sent successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("regrt", response.toString() );
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")){

                                Toasty.success(ForgotPassActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();
                                // startActivity(new Intent(ChangePasswordActivity.this,ChangePasswordActivity.class));

                                binding.etEmail.setText("");
                                dialog_new(response.getString("message")).show();
                            }
                            else {
                                Toasty.error(ForgotPassActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("kckjjc", e.getMessage() );
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("rgtrhbt", anError.getMessage() );
                        dialog.hideDialog();

                    }
                });

    }
    public Dialog dialog_new(String msg) {
        Dialog dialog = new Dialog(ForgotPassActivity.this);
        dialog.setContentView(R.layout.success_dialog);
        TextView text = dialog.findViewById(R.id.text);
        text.setText(msg);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
}