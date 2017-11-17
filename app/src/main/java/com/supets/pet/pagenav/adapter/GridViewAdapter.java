package com.supets.pet.pagenav.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supets.pet.pagenav.model.Model;
import com.supets.pet.pagenav.viewholder.PageNavCardHolder;
import com.supets.pet.pagnav.R;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {
    private List<Model> mDatas;
    private LayoutInflater inflater;
    private int mPosition;

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public GridViewAdapter(Context context, List<Model> mDatas, int curIndex, int pageSize) {
        this.mDatas = mDatas;
        this.pageSize = pageSize;
        this.curIndex = curIndex;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent,false);
            vh = new ViewHolder();
            vh.layout_view = (LinearLayout) convertView.findViewById(R.id.layout_view);
            vh.iv = (ImageView) convertView.findViewById(R.id.imageView);
            vh.tv = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //计算一下位置
        int pos = position + curIndex * pageSize;
        vh.iv.setImageResource(mDatas.get(pos).getIconRes());
        vh.tv.setText(mDatas.get(pos).getName());

        vh.layout_view.setSelected(pos == PageNavCardHolder.posSelect);

        return convertView;
    }

    class ViewHolder {
        public TextView tv;
        public ImageView iv;
        public LinearLayout layout_view;
    }
}
