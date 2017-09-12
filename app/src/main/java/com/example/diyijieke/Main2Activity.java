package com.example.diyijieke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
private ArrayList<Bean.DataBean> list = new ArrayList<>();
    private RecyclerView recy;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                initData();
            }
        }
    };
    private Adapter adapter;
    private String string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initJinDu();


    }


    private void initData() {
        OkHttpUtlis.getInstance().sendGET("http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                Log.e("TAG", "onResponse: "+ string);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(string, Bean.class);
                        List<Bean.DataBean> list1 = bean.getData();
                        list.addAll(list1);
                        // adapter.notifyDataSetChanged();
                        adapter = new Adapter(list, Main2Activity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(Main2Activity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recy.setLayoutManager(manager);
                        recy.setAdapter(adapter);
                        adapter.setConcli(new Adapter.Oncli() {
                            @Override
                            public void Oncli(int position) {
                                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                                intent.putExtra("img",list.get(position).getGoods_img());
                                intent.putExtra("name",list.get(position).getGoods_name());

                                startActivity(intent);
                            }
                        });
                    }
                });

            }
        });
    }

    private void initJinDu() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(100);
        dialog.setMessage("请稍等");
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<11){
                    try {
                        Thread.sleep(500);
                        dialog.incrementProgressBy(10);
                        i++;
                        if (i==10){
                            dialog.dismiss();
                            handler.sendEmptyMessage(1);

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);


    }
}
