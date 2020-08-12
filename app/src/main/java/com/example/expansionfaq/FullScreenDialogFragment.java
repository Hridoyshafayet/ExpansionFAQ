package com.example.expansionfaq;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FullScreenDialogFragment extends DialogFragment implements View.OnClickListener {

    private Callback callback;

    private View parent_view;

    private RecyclerView recyclerView;

    private AdapterListExpand mAdapter;

    static FullScreenDialogFragment newInstance() {
        return new FullScreenDialogFragment();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(FullScreenDialogFragment.STYLE_NORMAL, R.style.DialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);

        close.setOnClickListener(this);

        initComponent(view);

        return view;
    }



    private void initComponent(View v) {

        parent_view = v.findViewById(android.R.id.content);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new LineItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<ListModel> items = getListData(getActivity());

        //set data and list adapter
        mAdapter = new AdapterListExpand(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListExpand.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ListModel obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.questions + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    public static List<ListModel> getListData(Context ctx) {
        List<ListModel> items = new ArrayList<>();

        String ques_arr[] = ctx.getResources().getStringArray(R.array.questions);
        String ans_arr[] = ctx.getResources().getStringArray(R.array.answers);


        for (int i = 0; i < ques_arr.length; i++) {
            //Log.d(TAG, "getSocialData: "+ques_arr.length+"  "+ques_arr[i] );

            ListModel obj = new ListModel();
            obj.questions = ques_arr[i];
            obj.answers = ans_arr[i];
            items.add(obj);
        }
        return items;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

        }

    }

    public interface Callback {

        void onActionClick(String name);

    }

}
