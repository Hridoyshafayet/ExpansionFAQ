package com.example.expansionfaq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class AdapterListExpand extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListModel> items = new ArrayList<>();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ListModel obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListExpand(Context context, List<ListModel> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_ques;
        public TextView tv_ans;
        public ImageButton bt_expand;
        public View lyt_expand;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            tv_ques = (TextView) v.findViewById(R.id.tv_ques);
            tv_ans = (TextView) v.findViewById(R.id.tv_ans);
            bt_expand = (ImageButton) v.findViewById(R.id.bt_expand);
            lyt_expand = (View) v.findViewById(R.id.lyt_expand);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;

            final ListModel p = items.get(position);
            view.tv_ques.setText(p.questions);
            view.tv_ans.setText(p.answers);
            view.bt_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutExpand(!p.expanded, v, view.lyt_expand);
                    items.get(position).expanded = show;
                }
            });


            // void recycling view
            if(p.expanded){
                view.lyt_expand.setVisibility(View.VISIBLE);
            } else {
                view.lyt_expand.setVisibility(View.GONE);
            }
            toggleArrow(p.expanded, view.bt_expand, false);

        }
    }

    private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        toggleArrow(show, view, true);
        if (show) {
            lyt_expand.setVisibility(View.VISIBLE);
        } else {
            lyt_expand.setVisibility(View.GONE);
        }
        return show;
    }

    public static boolean toggleArrow(boolean show, View view, boolean delay) {
        if (show) {
            view.animate().setDuration(delay ? 200 : 0).rotation(180);
            return true;
        } else {
            view.animate().setDuration(delay ? 200 : 0).rotation(0);
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}