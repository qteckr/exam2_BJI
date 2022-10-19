package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button m_btn_stock_report = null;
    Button m_btn_sale_report = null;
    Button m_btn_select_espresso = null;
    Button m_btn_select_latte = null;
    Button m_btn_select_americano = null;

    TextView m_tv_label = null;

    RecyclerView m_rv_list = null;
    RecyclerView m_rv_sale_list = null;
    RecyclerView.LayoutManager layoutManager1;
    RecyclerView.LayoutManager layoutManager2;

    ArrayList<objListStock> stockArray;
    ArrayList<objListReport> reportArray;
    ArrayList<objListSale> saleArray;

    int coffee = 10000;
    int water = 10000;
    int milk = 5000;

    int saled_espresso_cnt = 0;
    int saled_latte_cnt = 0;
    int saled_americano_cnt = 0;

    int espresso_cost = 4000;
    int latte_cost = 5000;
    int americano_cost = 4500;
    objListStock coffee_stock;
    objListStock milk_stock;
    objListStock water_stock;
    ReportListAdapter m_adapter_report;
    StockListAdapter m_adapter_stock;
    SaleReportListAdapter m_adapter_sale;
    objListReport espresso_sale;
    objListReport latte_sale;
    objListReport americano_sale;
    objListSale sale_report;

    SimpleDateFormat date_format = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분");
    Date time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        m_btn_stock_report = findViewById(R.id.btn_stock_report);
        m_btn_sale_report = findViewById(R.id.btn_sale_report);
        m_btn_select_espresso = findViewById(R.id.btn_select_espresso);
        m_btn_select_latte = findViewById(R.id.btn_select_latte);
        m_btn_select_americano = findViewById(R.id.btn_select_americano);
        m_btn_stock_report.setOnClickListener(this);
        m_btn_sale_report.setOnClickListener(this);
        m_btn_select_espresso.setOnClickListener(this);
        m_btn_select_latte.setOnClickListener(this);
        m_btn_select_americano.setOnClickListener(this);

        m_tv_label = findViewById(R.id.tv_label);
        m_rv_list = findViewById(R.id.rv_list);
        m_rv_sale_list = findViewById(R.id.rv_sale_list);

        layoutManager1 = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        reportArray = new ArrayList<objListReport>();
        stockArray = new ArrayList<objListStock>();
        saleArray = new ArrayList<objListSale>();

        m_adapter_report = new ReportListAdapter(reportArray, this);
        m_adapter_stock = new StockListAdapter(stockArray, this);
        m_adapter_sale = new SaleReportListAdapter(saleArray,this);

        m_rv_list.setLayoutManager(layoutManager1);
        m_rv_sale_list.setLayoutManager(layoutManager2);
        m_rv_sale_list.setAdapter(m_adapter_sale);
//        m_rv_list.setAdapter(m_adapter);


    }

    @Override
    public void onClick(View view) {
        listClear();
        switch (view.getId()) {
            case R.id.btn_stock_report:
                stockReport();
                break;
            case R.id.btn_sale_report:
                saleReport();
                break;
            case R.id.btn_select_espresso:
                selectEspresso();
                break;
            case R.id.btn_select_latte:
                selectLatte();
                break;
            case R.id.btn_select_americano:
                selectAmericano();
                break;
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void stockReport() {
        m_tv_label.setText("");
        coffee_stock = new objListStock("원두", coffee);
        water_stock = new objListStock("라떼", water);
        milk_stock = new objListStock("아메리카노", milk);

        stockArray.clear();
        stockArray.add(coffee_stock);
        stockArray.add(water_stock);
        stockArray.add(milk_stock);

        m_rv_list.setAdapter(m_adapter_stock);
        m_adapter_stock.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void saleReport() {
        String profit = "총수익금 : " + (espresso_cost * saled_espresso_cnt + americano_cost * saled_americano_cnt + latte_cost * saled_latte_cnt) + "원";
        espresso_sale = new objListReport("에스프레소 매출", String.valueOf(saled_espresso_cnt * espresso_cost));
        latte_sale = new objListReport("라떼 매출", String.valueOf(saled_latte_cnt * latte_cost));
        americano_sale = new objListReport("아메리카노 매출", String.valueOf(saled_americano_cnt * americano_cost));

        m_tv_label.setText(profit);
        reportArray.clear();
        reportArray.add(espresso_sale);
        reportArray.add(latte_sale);
        reportArray.add(americano_sale);
        m_rv_list.setAdapter(m_adapter_report);
        m_adapter_report.notifyDataSetChanged();
        m_rv_sale_list.setVisibility(View.VISIBLE);
        m_rv_sale_list.scrollToPosition(0);
        m_adapter_sale.notifyDataSetChanged();

    }

    public void selectEspresso() {
        if (coffee < 100)
            m_tv_label.setText("원두가 부족합니다.");
        else if (water < 30)
            m_tv_label.setText("물이 부족합니다.");
        else {
            m_tv_label.setText("주문이 완료되었습니다.");
            coffee -= 100;
            water -= 30;
            saled_espresso_cnt++;
            saleSave(0);
        }
    }

    public void selectLatte() {
        if (coffee < 100)
            m_tv_label.setText("원두가 부족합니다.");
        else if (water < 70)
            m_tv_label.setText("물이 부족합니다.");
        else if (milk < 30)
            m_tv_label.setText("우유가 부족합니다.");
        else {
            m_tv_label.setText("주문이 완료되었습니다.");
            coffee -= 100;
            water -= 70;
            milk -= 30;
            saled_latte_cnt++;
            saleSave(1);
        }
    }

    public void selectAmericano() {
        if (coffee < 100)
            m_tv_label.setText("원두가 부족합니다.");
        else if (water < 100)
            m_tv_label.setText("물이 부족합니다.");
        else {
            m_tv_label.setText("주문이 완료되었습니다.");
            coffee -= 100;
            water -= 100;
            saled_americano_cnt++;
            saleSave(2);
        }
    }

    public void saleSave(int type) {
        time = new Date();
        if (type == 0)
            sale_report = new objListSale("에스프레소", date_format.format(time));
        else if (type == 1)
            sale_report = new objListSale("라떼", date_format.format(time));
        else if (type == 2)
            sale_report = new objListSale("아메리카노", date_format.format(time));
        saleArray.add(sale_report);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void listClear() {
        m_rv_sale_list.setVisibility(View.GONE);
        stockArray.clear();
        reportArray.clear();
        m_adapter_stock.notifyDataSetChanged();
        m_adapter_report.notifyDataSetChanged();
    }
}
