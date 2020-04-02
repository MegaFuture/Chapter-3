package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class PlaceholderFragment extends Fragment implements Adapter4Ex3.ListItemClickListener{
    private Adapter4Ex3 mAdapter;
    private RecyclerView mRecv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        final View v = inflater.inflate(R.layout.fragment_placeholder, container, false);
        mRecv = v.findViewById(R.id.list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecv.setLayoutManager(layoutManager);

        mRecv.setHasFixedSize(true);

        mAdapter = new Adapter4Ex3(50, this);
        mRecv.setAdapter(mAdapter);

        mRecv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1)
                        Toast.makeText(v.getContext(), "已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager)
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
              }
        });
        return v;
    }

    AnimatorSet anime;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ObjectAnimator kieru =ObjectAnimator.ofFloat(getActivity().findViewById(R.id.animation_view),"alpha",1f, 0f);
        ObjectAnimator deru =ObjectAnimator.ofFloat(getActivity().findViewById(R.id.list_view),"alpha",0f, 1f);

        anime = new AnimatorSet();
        anime.playTogether(kieru, deru);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            anime.start();
            }
        }, 1000);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(getActivity().getApplicationContext(), "Click on No." + Integer.toString(clickedItemIndex), Toast.LENGTH_SHORT).show();
    }
}
