package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.activity.IntroductionActivity;
import com.kghapp.activity.LoginActivity;
import com.kghapp.databinding.FragmentAboutBinding;
import com.kghapp.databinding.FragmentProfileBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import static com.kghapp.others.Api.login;
import static com.kghapp.others.Api.page_content;


public class AboutFragment extends Fragment {


    FragmentAboutBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAboutBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        about();
        return view;
    }

    private  void  about(){
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", page_content)
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
                                String about=jsonObject.getString("about");

                                String plainText = Html.fromHtml(about).toString();
                                 binding.txAbout.setText(plainText);
                                dialog.hideDialog();


                            } else {
                                Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
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