package com.example.expansionfaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpandableListActivity extends AppCompatActivity {
    private static final String TAG = "ExpandableList";
    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListExpand mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        parent_view = findViewById(android.R.id.content);

        initComponent();
    }


    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LineItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Social> items = getSocialData(this);

        //set data and list adapter
        mAdapter = new AdapterListExpand(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListExpand.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Social obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.questions + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    public static List<Social> getSocialData(Context ctx) {
        List<Social> items = new ArrayList<>();

        String ques_arr[] = ctx.getResources().getStringArray(R.array.questions);
        String ans_arr[] = ctx.getResources().getStringArray(R.array.answers);


        for (int i = 0; i < ques_arr.length; i++) {
            //Log.d(TAG, "getSocialData: "+ques_arr.length+"  "+ques_arr[i] );

            Social obj = new Social();
            obj.questions = ques_arr[i];
            obj.answers = ans_arr[i];
            items.add(obj);
        }
        Collections.shuffle(items);
        return items;
    }


}
