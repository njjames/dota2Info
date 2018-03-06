package com.nj.dota2info.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nj.dota2info.R;

import java.util.List;

/**
 * Created by Administrator on 2018-03-06.
 */

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mCategoryList;

    public CategoryAdapter(Context context, List<String> categoryList) {
        mContext = context;
        mCategoryList = categoryList;
    }


    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public String getItem(int i) {
        return mCategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.category_grid_item, null);
        }
        TextView categoryName = convertView.findViewById(R.id.tv_category_name);
        categoryName.setText(getItem(i));
        return convertView;
    }
}
