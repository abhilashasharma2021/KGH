package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.FragmentExamBinding;
import com.kghapp.databinding.FragmentTermsConditionBinding;
import com.kghapp.others.Api;
import com.kghapp.others.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.kghapp.others.Api.page_content;


public class TermsConditionFrag extends Fragment {

FragmentTermsConditionBinding binding;
private  View view;
private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentTermsConditionBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        terms();
        return view;
    }

    private  void  terms(){
       /* CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());*/
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", page_content)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("grtgfnjgh", response.toString());
                      //  dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")) {
                                String data= response.getString("data");
                                JSONObject jsonObject=new JSONObject(data);
                                String terms=jsonObject.getString("term_condition");

                                String plainText = Html.fromHtml(terms).toString();
                                binding.txTerm.setText(plainText);

                               // dialog.hideDialog();


                            } else {
                                Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                              //  dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("fhgfrnhgf", e.getMessage());
                           //dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fhgfrnhgf", anError.getMessage());
                       // dialog.hideDialog();

                    }
                });


    }
}