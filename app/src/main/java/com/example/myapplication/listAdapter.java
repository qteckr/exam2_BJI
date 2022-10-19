package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class listAdapter extends ListView {

    LayoutInflater m_inflater;
    Context m_context;

    public listAdapter(Context context) {
        super(context);

        m_context = context;
        m_inflater = (LayoutInflater) m_context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewItem vItem;

        if (convertView == null) {
            convertView = m_inflater.inflate(R.layout.item_list, parent, false);
            vItem = new ViewItem();
            vItem.m_tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            vItem.m_tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(vItem);

        } else {
            vItem = (ViewItem) convertView.getTag();
        }


        return convertView;
    }


    private class ViewItem {
        TextView m_tv_content;
        TextView m_tv_title;
    }
}
