package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button m_btn_menu1 = null;
    Button m_btn_menu2 = null;
    Button m_btn_menu3 = null;
    Button m_btn_menu4 = null;
    Button m_btn_menu5 = null;

    TextView m_tv_label = null;

    RecyclerView m_rv_list = null;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        m_btn_menu1 = findViewById(R.id.btn_menu1);
        m_btn_menu2 = findViewById(R.id.btn_menu2);
        m_btn_menu3 = findViewById(R.id.btn_menu3);
        m_btn_menu4 = findViewById(R.id.btn_menu4);
        m_btn_menu5 = findViewById(R.id.btn_menu5);
        m_btn_menu1.setOnClickListener(this);
        m_btn_menu2.setOnClickListener(this);
        m_btn_menu3.setOnClickListener(this);
        m_btn_menu4.setOnClickListener(this);
        m_btn_menu5.setOnClickListener(this);

        m_tv_label = findViewById(R.id.tv_label);

        m_rv_list = findViewById(R.id.rv_list);
        layoutManager= new LinearLayoutManager(this);;
        ExamListAdapter m_adapter = new ExamListAdapter(this);
        m_rv_list.setLayoutManager(layoutManager);
        m_rv_list.setAdapter(m_adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu1:
                break;
            case R.id.btn_menu2:
                break;
            case R.id.btn_menu3:
                break;
            case R.id.btn_menu4:
                break;
            case R.id.btn_menu5:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public class listAdapter extends ListView {

        LayoutInflater m_inflater;
        Context m_context;
        public listAdapter(Context context) {
            super(context);
            // TODO Auto-generated constructor stub

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
}