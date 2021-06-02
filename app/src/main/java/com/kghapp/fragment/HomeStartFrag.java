package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.activity.AllCourseShowActivity;
import com.kghapp.activity.LoginActivity;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.databinding.FragmentHomeStartBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.kghapp.others.Api.CourseList;


public class HomeStartFrag extends Fragment {

    FragmentHomeStartBinding binding;
    private Context context;
    private View view;

    private HomeCourseListAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeStartBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();

        String getUserId = SharedHelper.getKey(getActivity(), AppConstats.USERID);

       if (getUserId.equals("")) {
           binding.llLogin.setVisibility(View.VISIBLE);
        }
       else {
            binding.llLogin.setVisibility(View.GONE);
        }


        binding.llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        binding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AllCourseShowActivity.class));
            }
        });

        adapter = new HomeCourseListAdapter(context, courseList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.rvHomeStart.setLayoutManager(mLayoutManager);
        binding.rvHomeStart.setAdapter(adapter);

        getData();
        return view;
    }

    private void getData() {

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", CourseList)
                .addBodyParameter("limit", "10")
                .addBodyParameter("page", "2")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("egregh", response.toString());
                        dialog.hideDialog();
                        courseList = new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")) {
                                String image_path = response.getString("image_path");
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < 4; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    HomeCourseListModel model = new HomeCourseListModel();
                                    model.setCourseId(object.getString("cid"));
                                    model.setCourseName(object.getString("coursename"));
                                    model.setCourseMedium(object.getString("medium"));
                                    model.setCoursePrice(object.getString("coursecost"));
                                    model.setCourseDuration(object.getString("duration"));
                                    model.setCourseImage(object.getString("thumbnail"));
                                    model.setCoursePath(response.getString("image_path"));
                                    courseList.add(model);
                                }
                                adapter = new HomeCourseListAdapter(getActivity(), courseList);
                                binding.rvHomeStart.setAdapter(adapter);
                            }
                        } catch (Exception e) {
                            dialog.hideDialog();
                            Log.e("AllCourseShowActivity", "e: " + e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.hideDialog();
                        Log.e("AllCourseShowActivity", "anError: " + anError);
                    }
                });

    }


}
