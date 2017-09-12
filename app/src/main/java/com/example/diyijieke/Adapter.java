package com.example.diyijieke;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by 马骏 on 2017/9/11.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Bean.DataBean> list;
    private Context context;
 private Oncli oncli;
    public Adapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.img);
        holder.t1.setText(list.get(position).getGoods_name());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oncli.Oncli(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView img;
        private  TextView t1;
        public ViewHolder(View itemView) {
            super(itemView);
            img =(ImageView) itemView.findViewById(R.id.img);
            t1 =(TextView)itemView.findViewById(R.id.t1);
        }
    }
   public interface Oncli {
        void Oncli(int position);
    }

    public void setConcli(Oncli oncli) {
        this.oncli = oncli;
    }

}
