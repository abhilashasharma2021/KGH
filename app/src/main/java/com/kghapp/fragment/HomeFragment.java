package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.activity.PurchasedCourseDetailsActivity;
import com.kghapp.adapter.HomePurchasedCourseAdapter;
import com.kghapp.adapter.PurchaseHistoryAdapter;
import com.kghapp.databinding.FragmentHomeBinding;
import com.kghapp.databinding.FragmentHomeStartBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.model.HomeCoursePurchasedModel;
import com.kghapp.others.Api;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.kghapp.others.Api.my_purchase_course;


public class HomeFragment extends Fragment {
  LinearLayout ll_purchase1;
    FragmentHomeBinding binding;
    private Context context;
    private View view;
    String getUserName="";
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HomeCoursePurchasedModel>purchaseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        getUserName = SharedHelper.getKey(getActivity(), AppConstats.USERNAME);

        if (! getUserName.equals("")){
            binding.txName.setText("Welcome"+","+ getUserName);

        }
        binding.rvCourse.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        binding.rvCourse.setLayoutManager(layoutManager);



        show_PurchasedCourse();

        return view;
    }



    private void show_PurchasedCourse(){

        String  userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);

        Log.e("dfgfdfb", "userId: " +userId);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",my_purchase_course)
               .addBodyParameter("userid","10137")
               // .addBodyParameter("userid",userId)
                .setTag("Show Purchased Course")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("hbfggn", "onResponse: " +response);
                        purchaseList=new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                JSONArray jsonArray=new JSONArray(data);
                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    String course_details=object.getString("course_details");

                                    Log.e("HomeFragment", "course_details: " +course_details);
                                    JSONObject jsonObject=new JSONObject(course_details);

                                    HomeCoursePurchasedModel model=new HomeCoursePurchasedModel();

                                    Log.e("HomeFragment", "onResponse: " +jsonObject.getString("coursename"));
                                    Log.e("HomeFragment", "onResponse: " +jsonObject.getString("medium"));
                                    Log.e("HomeFragment", "onResponse: " +jsonObject.getString("thumbnail"));
                                    model.setCourseId(jsonObject.getString("cid"));
                                    model.setCourseName(jsonObject.getString("coursename"));
                                    model.setCourseMedium(jsonObject.getString("medium"));
                                    model.setCourseImage(jsonObject.getString("thumbnail"));
                                    model.setCoursePath(response.getString("path"));
                                    purchaseList.add(model);

                                }

                                HomePurchasedCourseAdapter  adapter = new HomePurchasedCourseAdapter(context, purchaseList);
                                binding.rvCourse.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Log.e("rgrg", "onResponse: " +e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("ghfrgtfr", "onError: " +anError);

                    }
                });



    }
}