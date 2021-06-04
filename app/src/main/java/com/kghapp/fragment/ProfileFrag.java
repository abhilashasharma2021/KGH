package com.kghapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.activity.LoginActivity;
import com.kghapp.activity.SplashActivity;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.adapter.PurchaseHistoryAdapter;
import com.kghapp.databinding.FragmentPrivacyPolicyBinding;
import com.kghapp.databinding.FragmentProfileBinding;
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
import static com.kghapp.others.Api.show_profile;


public class ProfileFrag extends Fragment {

   FragmentProfileBinding binding;
    private  View view;
    private Context context;
    private PurchaseHistoryAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        binding.ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });

        binding.rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        String textNew = "<font color=#BED3CBCB>Do you want to ?</font> <font color=#FF9801>Change Password</font>";
        binding.txtChange.setText(Html.fromHtml(textNew));



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        binding.rvHistory.setLayoutManager(mLayoutManager);

        show_Profile();
        getHistory();
        return view;
    }
    private void getHistory() {
      String  userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);



        Log.e("ProfileFrag", "userId: " +userId);
        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",my_purchase_course)
                .addBodyParameter("userid","10137")
                .setTag("Show Purchased Course")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ProfileFrag", "onResponse: " +response);

                        courseList=new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                JSONArray jsonArray=new JSONArray(data);
                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object=jsonArray.getJSONObject(i);

                                    String course_type=object.getString("course_type");
                                    String course_details=object.getString("course_details");
                                    JSONObject jsonObject=new JSONObject(course_details);
                                    HomeCourseListModel model=new HomeCourseListModel();
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

                                adapter = new PurchaseHistoryAdapter(context, courseList);
                                binding.rvHistory.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Log.e("ProfileFrag", "onResponse: " +e);

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("ProfileFrag", "onError: " +anError);

                    }
                });


    }

    private void logout(){


        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.logout_dialog);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.putKey(getActivity(), AppConstats.USERID, "");
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }


    private void show_Profile(){

        String  userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", show_profile)
                .addBodyParameter("userid", userId)
                .setTag("SHOW profile successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("rtgytfh", response.toString());
                        dialog.hideDialog();

                        try {
                            if (response.getString("result").equals("true")) {

                                JSONObject jsonObject=new JSONObject(response.getString("data"));

                                String userID = jsonObject.getString("userid");
                                String email = jsonObject.getString("email");
                                String username = jsonObject.getString("name");
                                String mobile = jsonObject.getString("mobile");
                                String gender = jsonObject.getString("gender");
                                String profile_image = jsonObject.getString("image");
                                String path = jsonObject.getString("path");


                                Log.e("ProfileActivity", "image: " + path + profile_image);


                                binding.etName.setText(username);
                                binding.etMobile.setText(mobile);
                                binding.txEmail.setText(email);
                                binding.txUserId.setText("KGH"+userID);


                                if (!profile_image.equals("")) {
                                    try {

                                        Glide.with(getActivity()).load(path + profile_image).error(R.drawable.techer_dummy).into(binding.ivUser);
                                    } catch (Exception e) {
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            Log.e("rtyrtyhtr", e.getMessage());
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("regrtht", anError.getMessage());
                        dialog.hideDialog();

                    }
                });


    }

    }
