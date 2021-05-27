package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
import com.kghapp.adapter.TecherProCourseDetailsAdapter;
import com.kghapp.adapter.VideoCourseDetailsAdapter;
import com.kghapp.databinding.ActivityCourseDetailsBinding;
import com.kghapp.model.TeacherProCourseDetailsModel;
import com.kghapp.model.VideoCourseDetailsModel;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.kghapp.others.Api.CourseDetails;

public class CourseDetailsActivity extends AppCompatActivity {
    ActivityCourseDetailsBinding binding;
    ArrayList<VideoCourseDetailsModel>videoList;
    ArrayList<TeacherProCourseDetailsModel>teacherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CourseDetailsActivity.this,RecyclerView.HORIZONTAL,false);
        binding.rvVideo.setLayoutManager(mLayoutManager);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(CourseDetailsActivity.this,RecyclerView.VERTICAL,false);
        binding.rvProfile.setLayoutManager(mLayoutManager1);

        binding.llPurchaseNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(CourseDetailsActivity.this, PurchaseNowActivity.class));
            }
        });
        courseDetails();
        teacher_Profile();
    }

    private void courseDetails(){
        String courseId = SharedHelper.getKey(getApplicationContext(), AppConstats.COURSEID);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, CourseDetailsActivity.this);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",CourseDetails)
                .addBodyParameter("id","32")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hideDialog();
                        videoList=new ArrayList<>();
                        Log.e("CourseDetailsActivity", "onResponse: " +response);
                        try {
                            if (response.getString("result").equals("true")){
                                String image_path=response.getString("image_path");
                                String data=response.getString("data");
                                JSONObject object=new JSONObject(data);
                                    String course_details=object.getString("course_details");
                                    String plainText = Html.fromHtml(course_details).toString();
                                    binding.txDetails.setText(plainText);

                                    String duration_new=object.getString("duration");
                                    String video=object.getString("video");

                                    int duration = Integer.parseInt(duration_new);

                                    if(duration<30){
                                       binding.txDuration.setText(duration_new + "Days");
                                    }
                                    else  {
                                        int month=duration/30;
                                        String durations=String.valueOf(month);
                                        binding.txDuration.setText(durations + "Months");
                                    }



                                    binding.txCourseName.setText(object.getString("coursename"));
                                    binding.txMedium.setText(object.getString("medium"));
                                    binding.txCoursePrice.setText("₹" + object.getString("coursecost"));


                                    try {
                                        Glide.with(CourseDetailsActivity.this).load(response.getString("image_path")+object.getString("thumbnail"))
                                                .placeholder(R.drawable.dummy).override(250, 250)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(binding.ivCourse);
                                    } catch (Exception e) {

                                    }


                                    JSONArray array=new JSONArray(video);
                                for (int i = 0; i <array.length() ; i++) {
                                    JSONObject jsonObject=array.getJSONObject(i);

                                    VideoCourseDetailsModel model=new VideoCourseDetailsModel();
                                    model.setCourseName(jsonObject.getString("title"));
                                    model.setCoursePath(response.getString("image_path"));
                                    videoList.add(model);
                                }



                                VideoCourseDetailsAdapter adapter = new VideoCourseDetailsAdapter(CourseDetailsActivity.this, videoList);
                                binding.rvVideo.setAdapter(adapter);

                                }


                        } catch (JSONException e) {

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


    private void teacher_Profile(){

        String courseId = SharedHelper.getKey(getApplicationContext(), AppConstats.COURSEID);

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",CourseDetails)
                .addBodyParameter("id","32")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("vdsvdcvc", "profile: " +response);
                        teacherList=new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String image_path = response.getString("image_path");
                                String data = response.getString("data");
                                JSONObject object = new JSONObject(data);
                                String teacher = object.getString("teacher");
                                JSONArray jsonArray =new JSONArray(teacher);
                                Log.e("rtyhthb", "onResponse: " +teacher);

                                for (int j = 0; j <jsonArray.length() ; j++) {

                                    JSONObject object1=jsonArray.getJSONObject(j);


                                    TeacherProCourseDetailsModel modelNew=new TeacherProCourseDetailsModel();

                                        Log.e("dgvdfbfbvc", "onResponse: " +object1.getString("name"));
                                        modelNew.setTeacherId(object1.getString("name"));
                                        modelNew.setImage(object1.getString("photo"));
                                        modelNew.setPath(response.getString("image_path"));
                                        modelNew.setExeprience(object1.getString("description"));
                                        modelNew.setQualification(object1.getString("qualification"));
                                        modelNew.setTeacherName(object1.getString("name"));
                                        teacherList.add(modelNew);

                                }



                              TecherProCourseDetailsAdapter adapter = new TecherProCourseDetailsAdapter(CourseDetailsActivity.this, teacherList);
                                binding.rvProfile.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Log.e("dsgvdfb", "e: " +e);
                        }



                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("dsgvdfb", "anError: " +anError);
                    }
                });

    }
}

