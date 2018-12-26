package com.example.dell.fder.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.fder.R;

public class ContactFragment extends Fragment {

    private TextView tv;

    public static SqureFragment newInstance(String name) {

        Bundle args = new Bundle();
        args.putString("name", name);
        SqureFragment fragment = new SqureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_page_contact, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        tv = (TextView) view.findViewById(R.id.contact_view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            tv.setText(name);
        }

    }
}
