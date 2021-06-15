package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.adapter.PurchaseHistoryAdapter;
import com.kghapp.databinding.ActivityAllPurchaseHistoryBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.CustomDialog;
import com.kghapp.others.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.kghapp.others.Api.my_purchase_course;

public class AllPurchaseHistoryActivity extends AppCompatActivity {
    ActivityAllPurchaseHistoryBinding binding;
    private PurchaseHistoryAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllPurchaseHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AllPurchaseHistoryActivity.this, 2, RecyclerView.VERTICAL, false);
        binding.rvPurchase.setLayoutManager(mLayoutManager);
        getHistory();
    }


    private void getHistory() {
        String userId = SharedHelper.getKey(AllPurchaseHistoryActivity.this, AppConstats.USERID);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);

        Log.e("ProfileFrag", "userId: " + userId);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", my_purchase_course)
                .addBodyParameter("userid", "10137")
                .setTag("Show Purchased Course")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("fhfgjhn", "onResponse: " + response);
                       dialog.hideDialog();
                        courseList = new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String course_type = object.getString("course_type");
                                    String course_details = object.getString("course_details");
                                    JSONObject jsonObject = new JSONObject(course_details);
                                    HomeCourseListModel model = new HomeCourseListModel();
                                    model.setCourseId(jsonObject.getString("cid"));
                                    model.setCourseName(jsonObject.getString("coursename"));
                                    model.setCourseMedium(jsonObject.getString("medium"));
                                    model.setCourseType(jsonObject.getString("course_type"));
                                    /*course type me 0 online h 1 offline h*/
                                    model.setCourseDuration(jsonObject.getString("duration"));
                                    model.setCoursePrice(jsonObject.getString("coursecost"));
                                    model.setOffline_cost(jsonObject.getString("offline_cost"));
                                    model.setCourseImage(jsonObject.getString("thumbnail"));
                                    model.setCoursePath(response.getString("path"));
                                    courseList.add(model);

                                }

                                adapter = new PurchaseHistoryAdapter(AllPurchaseHistoryActivity.this, courseList);
                                binding.rvPurchase.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Log.e("tghtryhrt", "onResponse: " + e);
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("fgregregvre", "onError: " + anError);
                        dialog.hideDialog();

                    }
                });

    }
}