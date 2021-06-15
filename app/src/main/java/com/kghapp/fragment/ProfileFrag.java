package com.kghapp.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.kghapp.R;
import com.kghapp.activity.AllPurchaseHistoryActivity;
import com.kghapp.activity.ChangePasswordActivity;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.kghapp.others.Api.my_purchase_course;
import static com.kghapp.others.Api.show_profile;
import static com.kghapp.others.Api.update_profile;


public class ProfileFrag extends Fragment {

    FragmentProfileBinding binding;
    private View view;
    private Context context;
    private PurchaseHistoryAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList;
    public static Dialog dialog;
    Button btnprofile;
    EditText etName, etMobile, etGender;
    ImageView ivProfile;

    String strUserName="",str_contact="",str_gender="";
    private static final String IMAGE_DIRECTORY = "/directory";
    private int GALLERY = 1, CAMERA = 2;
    File f;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();
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

        binding.txViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AllPurchaseHistoryActivity.class));
            }
        });



        String textNew = "<font color=#BED3CBCB>Do you want to ?</font> <font color=#FF9801>Change Password</font>";
        binding.txtChange.setText(Html.fromHtml(textNew));

        binding.txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });


        binding.btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileDialog();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.rvHistory.setLayoutManager(mLayoutManager);

        show_Profile();
        getHistory();
        return view;
    }

    private void getHistory() {
        String userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);


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
                        Log.e("ProfileFrag", "onResponse: " + response);

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

                                adapter = new PurchaseHistoryAdapter(context, courseList);
                                binding.rvHistory.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            Log.e("ProfileFrag", "onResponse: " + e);

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("ProfileFrag", "onError: " + anError);

                    }
                });


    }

    private void logout() {


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


    private void show_Profile() {

        String userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);

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

                                JSONObject jsonObject = new JSONObject(response.getString("data"));

                                String userID = jsonObject.getString("userid");
                                String email = jsonObject.getString("email");
                                String username = jsonObject.getString("name");
                                String mobile = jsonObject.getString("mobile");
                                String gender = jsonObject.getString("gender");
                                String profile_image = jsonObject.getString("image");
                                String path = jsonObject.getString("path");


                                Log.e("dfhhjhgfjhg", "image: " + path + profile_image);


                                binding.etName.setText(username);
                                binding.etMobile.setText(mobile);
                                binding.txEmail.setText(email);
                                binding.txUserId.setText("KGH" + userID);

                                if (gender.equals("0")){
                                    binding.etGender.setText("Male");
                                }else if (gender.equals("1")){
                                    binding.etGender.setText("Female");
                                }
                                else {
                                    binding.etGender.setText("Gender");
                                }


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


    public void profileDialog() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogprofilelayout);


        btnprofile = dialog.findViewById(R.id.btnprofile);
        etName = dialog.findViewById(R.id.etName);
        etMobile = dialog.findViewById(R.id.etMobile);
        etGender = dialog.findViewById(R.id.etGender);
        ivProfile = dialog.findViewById(R.id.ivProfile);

        dialogProfileShow();

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPictureDialog();
            }
        });


        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                strUserName=etName.getText().toString().trim();
                str_contact=etMobile.getText().toString().trim();
                str_gender=etGender.getText().toString().trim();

                update_Profile(strUserName,str_contact,str_gender);


                dialog.dismiss();
            }
        });

        dialog.show();

    }



    private void dialogProfileShow() {

        String userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);


        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", show_profile)
                .addBodyParameter("userid", userId)
                .setTag("SHOW profile successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("vgbfdbvgf", response.toString());


                        try {
                            if (response.getString("result").equals("true")) {

                                JSONObject jsonObject = new JSONObject(response.getString("data"));

                                String userID = jsonObject.getString("userid");
                                String email = jsonObject.getString("email");
                                String username = jsonObject.getString("name");
                                String mobile = jsonObject.getString("mobile");
                                String gender = jsonObject.getString("gender");
                                String profile_image = jsonObject.getString("image");
                                String path = jsonObject.getString("path");


                                Log.e("gvfdgvbfdb", "image: " + path + profile_image);


                                etName.setText(username);
                                etMobile.setText(mobile);
                                if (jsonObject.getString("gender").equals("0")){
                                    etGender.setText("Male");
                                }
                                else if (jsonObject.getString("gender").equals("1")){
                                    etGender.setText("Female");
                                }
                                else {
                                    etGender.setText("Gender");
                                }





                                if (!profile_image.equals("")) {
                                    try {

                                        Glide.with(getActivity()).load(path + profile_image).into(ivProfile);
                                    } catch (Exception e) {
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            Log.e("rtyrtyhtr", e.getMessage());

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("regrtht", anError.getMessage());


                    }
                });

    }


    public void showPictureDialog() {

        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Action");
        String[] pictureDialogItems = {"Select photo from gallery", "Capture image from camera"};

        builder.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case 0:
                        choosePhotoFromGallery();
                        break;

                    case 1:
                        captureFromCamera();
                        break;
                }

            }
        });

        builder.show();
    }


    public void choosePhotoFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, GALLERY);
    }

    public void captureFromCamera() {

        Intent intent_2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent_2, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    String path = saveImage(bitmap);

                    Log.e("check", "path: " + path);
                    Log.e("check", "bitmap: " + bitmap);
                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    ivProfile.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ivProfile.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);

        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");

            Log.e("check", "f: " + f);
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.e("check", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";

    }

    private void update_Profile(String strUserName, String str_contact, String str_gender){

        String userId = SharedHelper.getKey(getActivity(), AppConstats.USERID);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());

        AndroidNetworking.upload(Api.BASE_URL)
                .addMultipartParameter("control",update_profile)
                .addMultipartParameter("id",userId)
                .addMultipartParameter("full_name",strUserName)
                .addMultipartParameter("mobile",str_contact)
                .addMultipartParameter("gender",str_gender)
               .addMultipartFile("image",f)
                .setPriority(Priority.HIGH)
                .setTag("Sussessful")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("sdgfvdhbfdh ",response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");

                                if (!data.isEmpty()){
                                    JSONObject jsonObject=new JSONObject(data);


                                    etName.setText(jsonObject.getString("name"));
                                    etMobile.setText(jsonObject.getString("mobile"));

                                    if (jsonObject.getString("gender").equals("0")){
                                        etGender.setText("Male");
                                    }
                                    else if (jsonObject.getString("gender").equals("1")){
                                        etGender.setText("Female");
                                    }
                                    else {
                                        etGender.setText("Gender");
                                    }



                                    if (!jsonObject.getString("").equals("")) {
                                        try {

                                            Glide.with(getActivity()).load(jsonObject.getString("path") + jsonObject.getString("image")).into(ivProfile);
                                        } catch (Exception e) {
                                        }
                                    }


                                    Toasty.success(getActivity(),response.getString("message"),Toasty.LENGTH_SHORT).show();

                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFrag()).commit();
                                    getActivity().finish();

                                }

                            }
                            else {
                                Toasty.success(getActivity(),response.getString("message"),Toasty.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("dvfdvbf",e.getMessage());
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("grfthgftb",anError.getMessage());
                        dialog.hideDialog();


                    }
                });

    }

}

