package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
import com.kghapp.databinding.ActivityAllCourseShowBinding;
import com.kghapp.databinding.ActivityCourseDetailsBinding;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.kghapp.others.Api.CourseDetails;
import static com.kghapp.others.Api.CourseList;

public class CourseDetailsActivity extends AppCompatActivity {
    ActivityCourseDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseDetails();
    }

    private void courseDetails(){
        String courseId = SharedHelper.getKey(getApplicationContext(), AppConstats.COURSEID);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, CourseDetailsActivity.this);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",CourseDetails)
                .addBodyParameter("id",courseId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hideDialog();
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
}