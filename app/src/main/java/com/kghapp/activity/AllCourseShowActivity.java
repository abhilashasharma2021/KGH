package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.databinding.ActivityAllCourseShowBinding;
import com.kghapp.databinding.FragmentHomeStartBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.others.Api;
import com.kghapp.others.CustomDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.kghapp.others.Api.CourseList;
import static com.kghapp.others.Api.login;

public class AllCourseShowActivity extends AppCompatActivity {
ActivityAllCourseShowBinding binding;

    private HomeCourseListAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAllCourseShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AllCourseShowActivity.this,2,RecyclerView.VERTICAL,false);
        binding.rvHomeStart.setLayoutManager(mLayoutManager);


        getData();

    }
    private void getData() {

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, AllCourseShowActivity.this);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",CourseList)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("egregh", response.toString());
                        dialog.hideDialog();
                        courseList=new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")){
                              String image_path=response.getString("image_path");
                              String data=response.getString("data");
                                JSONArray jsonArray=new JSONArray(data);
                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    HomeCourseListModel model=new HomeCourseListModel();
                                    model.setCourseId(object.getString("cid"));
                                    model.setCourseName(object.getString("coursename"));
                                    model.setCoursePrice(object.getString("coursecost"));
                                    model.setCourseMedium(object.getString("medium"));
                                    model.setCourseDuration(object.getString("duration"));
                                    model.setCourseImage(object.getString("thumbnail"));
                                    model.setCoursePath(response.getString("image_path"));
                                    courseList.add(model);
                                }
                                adapter = new HomeCourseListAdapter(AllCourseShowActivity.this, courseList);
                                binding.rvHomeStart.setAdapter(adapter);
                            }
                        }


                        catch (Exception e){
                            dialog.hideDialog();
                            Log.e("AllCourseShowActivity", "e: " +e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.hideDialog();
                        Log.e("AllCourseShowActivity", "anError: " +anError);
                    }
                });

    }
}