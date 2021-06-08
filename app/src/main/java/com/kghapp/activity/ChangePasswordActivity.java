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
import com.kghapp.databinding.ActivityChangePasswordBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

import static com.kghapp.others.Api.change_password;

public class ChangePasswordActivity extends AppCompatActivity {
ActivityChangePasswordBinding binding;
String strConfirm="",strNewPassword="",strOld="",stUserOldPassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strConfirm=binding.etCfmPassword.getText().toString().trim();
                strNewPassword=binding.etNewPassword.getText().toString().trim();
                strOld=binding.etOldPassword.getText().toString().trim();

                if (strOld.equals("")){
                    Toast.makeText(ChangePasswordActivity.this,"Please Enter Correct Old Password",Toast.LENGTH_LONG).show();

                }
                else if (strNewPassword.equals("")){
                    Toast.makeText(ChangePasswordActivity.this, "Please enter your new password", Toast.LENGTH_SHORT).show();
                }else if (!strConfirm.equals(strNewPassword)){
                    Toast.makeText(ChangePasswordActivity.this, "Password not match !! try again", Toast.LENGTH_SHORT).show();
                }else {

                    change_password (strOld,strNewPassword,strConfirm);
                }
            }
        });
    }


    private void change_password(String strOld, String strNewPassword, String strConfirm){

        String  userId = SharedHelper.getKey(ChangePasswordActivity.this, AppConstats.USERID);
        CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);

        Log.e("dlvkdlv", userId );
        Log.e("dlvkdlv", strOld);
        Log.e("dlvkdlv",strNewPassword);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",change_password)
                .addBodyParameter("id",userId)
                .addBodyParameter("old_password", strOld)
                .addBodyParameter("new_password",strNewPassword)
                .addBodyParameter("password",strConfirm)
                .setTag("")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("regrt", response.toString() );
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")){


                                binding.etNewPassword.setText("");
                                binding.etCfmPassword.setText("");
                                binding.etOldPassword.setText("");

                                Toasty.success(ChangePasswordActivity.this,response.getString("message"), Toast.LENGTH_SHORT).show();


                           }
                            else {
                                Toasty.error(ChangePasswordActivity.this,response.getString("message"), Toast.LENGTH_SHORT).show();
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



}