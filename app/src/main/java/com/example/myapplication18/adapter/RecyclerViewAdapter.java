package com.example.myapplication18.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication18.R;
import com.example.myapplication18.model.Bean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 11:25
 * @QQ 1481583730
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private Bean bean;
    private Context context;


    public RecyclerViewAdapter(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
            return new HomeHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
            return new FlistHolder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (holder instanceof HomeHolder) {
            HomeHolder homeHolder = (HomeHolder) holder;
            ArrayList<String> titles = new ArrayList<>();
            for (int j = 0; j < bean.getData().size(); j++) {
                titles.add(bean.getData().get(j).getTitle());
            }
            homeHolder.itemBanner.setImages(bean.getData());
            homeHolder.itemBanner.setBannerTitles(titles);
            homeHolder.itemBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            homeHolder.itemBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Bean.DataBean dataBean = (Bean.DataBean) path;
                    Glide.with(context).load(dataBean.getImage()).into(imageView);
                }
            });
            homeHolder.itemBanner.start();

        } else if (holder instanceof FlistHolder) {
            int i = position;
            if (bean.getData().size() > 0) {
                i = position - 1;
            }
            FlistHolder flistHolder = (FlistHolder) holder;
            Glide.with(context).load(bean.getData().get(i).getImage()).into(flistHolder.itemIv);
            flistHolder.itemTv.setText(bean.getData().get(i).getTitle());
            flistHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });

        }
    }


    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        if (bean.getData().size() > 0) {
            return bean.getData().size() + 1;
        }
        return bean.getData().size();
    }


    class HomeHolder extends RecyclerView.ViewHolder {
        private Banner itemBanner;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            itemBanner = (Banner) itemView.findViewById(R.id.item_banner);
        }
    }

    class FlistHolder extends RecyclerView.ViewHolder {
        private CardView itemCv;
        private ImageView itemIv;
        private TextView itemTv;

        public FlistHolder(@NonNull View itemView) {
            super(itemView);
            itemCv = (CardView) itemView.findViewById(R.id.item_cv);
            itemIv = (ImageView) itemView.findViewById(R.id.item_iv);
            itemTv = (TextView) itemView.findViewById(R.id.item_tv);

        }
    }
}
