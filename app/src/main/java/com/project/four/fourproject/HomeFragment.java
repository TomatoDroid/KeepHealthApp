package com.project.four.fourproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.four.fourproject.home.EventReminder;
import com.project.four.fourproject.home.HealthData;
import com.project.four.fourproject.home.HealthInformation;
import com.project.four.fourproject.home.HealthMessage;
import com.project.four.fourproject.home.ItemBean;
import com.project.four.fourproject.home.TimelyInquiry;
import com.project.four.fourproject.home.UrgentRescue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sxt on 2016/10/27.
 */

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<ItemBean> mData;
    private ItemBean itemBean;
    private MyAdapter adapter;
    private String[] option = new String[]{"健康数据","养生资讯","健康管理","事件提醒","紧急救援","随时问诊"};
    private int[] icon = new int[]{
            R.drawable.item_health_update,
            R.drawable.item_information,
            R.drawable.item_health_view,
            R.drawable.item_remind,
            R.drawable.item_help,
            R.drawable.item_inquiry};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home,container,false);
        gridView = (GridView) view.findViewById(R.id.main_gridView);

        mData = new ArrayList<>();
        for (int i = 0;i<option.length;i++){
            itemBean = new ItemBean(icon[i],option[i]);
            mData.add(itemBean);
        }
        adapter = new MyAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(),"我是:"+option[position],Toast.LENGTH_SHORT).show();
        Intent intent;
        switch (icon[position]){
            case R.drawable.item_health_update:
                intent = new Intent(getActivity(), HealthData.class);
                startActivity(intent);
                break;
            case R.drawable.item_information:
                intent = new Intent(getActivity(), HealthInformation.class);
                startActivity(intent);
                break;
            case R.drawable.item_health_view:
                intent = new Intent(getActivity(), HealthMessage.class);
                startActivity(intent);
                break;
            case R.drawable.item_remind:
                intent = new Intent(getActivity(), EventReminder.class);
                startActivity(intent);
                break;
            case R.drawable.item_help:
                intent = new Intent(getActivity(), UrgentRescue.class);
                startActivity(intent);
                break;
            case R.drawable.item_inquiry:
                intent = new Intent(getActivity(), TimelyInquiry.class);
                startActivity(intent);
                break;
        }
    }

    private class MyAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.main_item,null);
                viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.item_imageView);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.item_textView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ItemBean bean = mData.get(position);

            viewHolder.ivIcon.setImageResource(bean.iconId);
            viewHolder.tv.setText(bean.option);


            return convertView;
        }
    }

    class ViewHolder{
        public TextView tv;
        public ImageView ivIcon;
    }

}
