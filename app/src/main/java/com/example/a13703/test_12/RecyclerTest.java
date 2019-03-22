package com.example.a13703.test_12;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13703 on 2019/3/21.
 */

public class RecyclerTest extends AppCompatActivity {
    private RecyclerView mRcList;
    private RecyclerAdapter mAdapter;
    private Spinner mSpinner;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        mRcList = (RecyclerView) findViewById(R.id.rc_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRcList.setLayoutManager(mLayoutManager);
        mRcList.setHasFixedSize(true);
        mSpinner = (Spinner)findViewById(R.id.spinner);
        //设置显示动画
        mRcList.setItemAnimator(new DefaultItemAnimator());
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //设置为线性布局
                    mRcList.setLayoutManager(new LinearLayoutManager(RecyclerTest.this));
                }else if(position == 1){
                    //设置为表格布局
                    mRcList.setLayoutManager(new GridLayoutManager(RecyclerTest.this,3));
                }else if(position == 2 ){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //增加测试数据
        mData.add("Android Test1");
        mData.add("Android Test2");
        mData.add("Android Test3");
        mAdapter = new RecyclerAdapter(mData);
        mRcList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                parent.animate().translationZ(15f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        parent.animate().translationZ(1f).setDuration(500).start();
                    }
                }).start();
            }

        });

    }
    public void addRecycler(View view){
        int position = mData.size();
        if(position>0){
            mAdapter.notifyDataSetChanged();
        }
    }
    public void delRecycler(View view){
        int position = mData.size();
        if(position>0){
            mData.remove(position-1);
            mAdapter.notifyDataSetChanged();
        }
    }
}
