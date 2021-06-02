package com.kghapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kghapp.R;
import com.kghapp.databinding.FragmentSelectExamBinding;
import com.kghapp.others.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.kghapp.others.Api.CourseList;
import static com.kghapp.others.Api.exam_list;
import static com.kghapp.others.Api.subject_list;
import static com.kghapp.others.Api.topic_list;


public class SelectExamFrag extends Fragment {
    FragmentSelectExamBinding binding;
    private View view;
    private Context context;

    ArrayList<String> arrayListExamId;
    ArrayList<String> arrayListExam;
    ArrayAdapter<String> adapterExam;

    ArrayList<String> arrayListSubjId;
    ArrayList<String> arrayListSubj;
    ArrayAdapter<String> adapterSubj;

    ArrayList<String> arrayListTopicId;
    ArrayList<String> arrayListTopic;
    ArrayAdapter<String> adapterTopic;



    ArrayList<String> arrayListCourseId;
    ArrayList<String> arrayListCourse;
    ArrayAdapter<String> adapterCourse ;

    String stExamId = "",stSubjectId="",stTopicId="",stCourseId="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSelectExamBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExamFrag())
                        .commit();
            }
        });


        binding.spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (arrayListCourseId.size()==0){

                }
                else {

                    stCourseId = arrayListCourseId.get(position);
                    selectSubject(stCourseId);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        binding.spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (arrayListSubjId.size()==0){

                }
                else {

                    stSubjectId = arrayListSubjId.get(position);
                    selectTopic(stSubjectId);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        binding.spTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stTopicId = arrayListTopicId.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        binding.spExam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stExamId = arrayListExamId.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        selectCourse();

        selectExam();
        return view;


    }

    private void selectCourse(){

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control",CourseList)
                .addBodyParameter("limit","100")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("SelectExamFrag", "onResponse: " +response);
                        arrayListCourse = new ArrayList<>();
                        arrayListCourseId = new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")){

                                String data=response.getString("data");
                                JSONArray jsonArray=new JSONArray(data);

                                for (int i = 0; i <jsonArray.length() ; i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String cid = jsonObject.getString("cid");
                                    String coursename = jsonObject.getString("coursename");

                                    arrayListCourseId.add(cid);
                                    Log.e("fdbhfdnb", cid);
                                    arrayListCourse.add(coursename);

                                }

                            }

                            adapterCourse = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListCourse);
                            adapterCourse.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spCourse.setAdapter(adapterCourse);

                        } catch (JSONException e) {
                            Log.e("SelectExamFrag", "onResponse: " +e);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("SelectExamFrag", "onResponse: " +anError);
                    }
                });

    }





    private void selectSubject(String stCourseId) {

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", subject_list)
                .addBodyParameter("course_id", stCourseId)
                .setTag("Select Subject")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("gfngn", response.toString());

                        arrayListSubj = new ArrayList<>();
                        arrayListSubjId = new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String sid = jsonObject.getString("sid");
                                    String subject_name = jsonObject.getString("subject");

                                    arrayListSubjId.add(sid);
                                    Log.e("dsgvdfgbv", sid);
                                    arrayListSubj.add(subject_name);


                                }
                            }


                            adapterSubj = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListSubj);
                            adapterSubj.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spSubject.setAdapter(adapterSubj);


                        } catch (JSONException e) {
                            Log.e("trhtrh", e.getMessage());

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("hbfgnh", anError.getMessage());

                    }
                });


    }
    private void selectTopic(String stSubjectId) {

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", topic_list)
                .addBodyParameter("subject_id", stSubjectId)
                .setTag("Select Topic")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("trgrftg", response.toString());

                        arrayListTopic = new ArrayList<>();
                        arrayListTopicId = new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String tid = jsonObject.getString("tid");
                                    String title = jsonObject.getString("title");

                                    arrayListTopicId.add(tid);
                                    Log.e("rehgtrfh", tid);
                                    arrayListTopic.add(title);


                                }
                            }


                            adapterTopic = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListTopic);
                            adapterTopic.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spTopic.setAdapter(adapterTopic);


                        } catch (JSONException e) {
                            Log.e("dbvdf", e.getMessage());

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("gfnngn", anError.getMessage());

                    }
                });
    }



        private void selectExam() {

        AndroidNetworking.post(Api.BASE_URL)
                .addBodyParameter("control", exam_list)
                .setTag("Select Exam")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("gvdfgvbcfv", response.toString());

                        arrayListExam = new ArrayList<>();
                        arrayListExamId = new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String id = jsonObject.getString("elid");
                                    String exam_name = jsonObject.getString("exam_name");

                                    arrayListExamId.add(id);
                                    Log.e("dkjfkdj", id);
                                    arrayListExam.add(exam_name);


                                }
                            }


                            adapterExam = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListExam);
                            adapterExam.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spExam.setAdapter(adapterExam);


                        } catch (JSONException e) {
                            Log.e("dsfkdsk", e.getMessage());

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("rtfrgf", anError.getMessage());

                    }
                });


    }




    }
