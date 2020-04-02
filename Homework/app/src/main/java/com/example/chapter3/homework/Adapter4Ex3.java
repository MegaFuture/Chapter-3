package com.example.chapter3.homework;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter4Ex3 extends RecyclerView.Adapter<Adapter4Ex3.NumberViewHolder> {
    private static final String TAG = "Adapter";
    private final ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private static int viewHolderCount;

    public Adapter4Ex3(int numListItems, ListItemClickListener listener) {
        mNumberItems =numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public Adapter4Ex3.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        ((TextView)viewHolder.itemView.findViewById(R.id.temp)).setText("ViewHolder index: " + viewHolderCount);
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(int bindPosition) {
            ((TextView)itemView.findViewById(R.id.temp)).setText("Friend No. " + Integer.toString(bindPosition));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
