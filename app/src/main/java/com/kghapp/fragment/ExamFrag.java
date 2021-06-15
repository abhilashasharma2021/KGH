package com.kghapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.FragmentAboutBinding;
import com.kghapp.databinding.FragmentContactUsBinding;
import com.kghapp.databinding.FragmentExamBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import static com.kghapp.others.Api.startExam;


public class ExamFrag extends Fragment {
    FragmentExamBinding binding;
    private  View view;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentExamBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuesExamFrag())
                        .commit();
            }
        });
        return view;
    }

    private void show_Instruction(){
        String userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",startExam)
                .addBodyParameter("userid",userId)
                .setTag("Show Instruction")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ExamFrag", "onResponse: " +response);

                        try {
                            if (response.getString("result").equals("true")){


                            }
                        } catch (JSONException e) {
                            Log.e("ExamFrag", "e: " +e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ExamFrag", "anError: " +anError);
                    }
                });

    }
}