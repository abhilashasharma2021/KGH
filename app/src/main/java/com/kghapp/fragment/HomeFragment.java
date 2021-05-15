package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kghapp.R;
import com.kghapp.databinding.FragmentHomeBinding;
import com.kghapp.databinding.FragmentHomeStartBinding;


public class HomeFragment extends Fragment {
  LinearLayout ll_purchase1;
    FragmentHomeBinding binding;
    private Context context;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

       /* ll_purchase1=view.findViewById(R.id.ll_purchase1);
        ll_purchase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClassesCourseActiv())
                        .commit();
            }
        });*/

        return view;
    }
}