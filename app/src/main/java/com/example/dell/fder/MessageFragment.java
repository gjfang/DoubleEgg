package com.example.dell.fder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.dell.fder.R;

public class MessageFragment extends Fragment {

//    private List<Message> messageList=new ArrayList<>();
//    private MessageAdapter messageAdapter;
//
//
//        private Message[] messages = {
//            new Message(R.drawable.apple,"Apple","I'm an apple,and you? ","14:10"),
//            new Message(R.drawable.banana, "Banana","I'm a banana, and you?","14:11"),
//            new Message(R.drawable.grape,"Grape","I'm a grape, and you?","14:11"),
//            new Message(R.drawable.girl,"Girl","I'm a girl, and you?","14:12"),
//            new Message(R.drawable.pear,"Pear","I'm a pear, and you?","14:12"),
//            new Message(R.drawable.orange,"Orange","I'm an orange, and you?","14:13"),
//            new Message(R.drawable.mango,"Mango","I'm a Mango, and you?","14:14"),
//
//};

    public static MessageFragment newInstance(String name) {

        Bundle args = new Bundle();
        args.putString("name", name);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_page_message, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        tv = (TextView) view.findViewById(R.id.message_view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
//            tv.setText(name);
        }

    }


}
