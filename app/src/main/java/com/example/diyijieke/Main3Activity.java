package com.example.diyijieke;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private ImageView img;
    private TextView t1;
    private LinearLayout activity_main3;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img1 = intent.getStringExtra("img");

//        Target<Drawable> into = Glide.with(Main3Activity.this).load(img1).into(img);
//        t1.setText(name);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://image.yunifang.com/yunifang/images/goods/121/goods_img/17062610568378169043195978.jpg");
        strings.add("https://image.yunifang.com/yunifang/images/goods/5/goods_img/170626112553213363513709796.jpg");
        banner.setImages(strings)//添加图片集合或图片url集合
                .setDelayTime(2000)//设置轮播时间
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())//加载图片
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                .start();

}

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        t1 = (TextView) findViewById(R.id.t1);
        banner = (Banner) findViewById(R.id.banner);

    }
}
