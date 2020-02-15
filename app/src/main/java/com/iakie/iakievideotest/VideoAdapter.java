package com.iakie.iakievideotest;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.JzvdStd;

/**
 * Author: iqiqiya
 * Date: 2020-02-14
 * Time: 21:55
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class VideoAdapter extends BaseAdapter {
    Context context;// 联系上下文
    List<VideoBean.ItemListBean> mDatas;

    public VideoAdapter(Context context, List<VideoBean.ItemListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mainlv,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 获取指定位置的数据源
        VideoBean.ItemListBean.DataBean dataBean = mDatas.get(position).getData();
        VideoBean.ItemListBean.DataBean.AuthorBean author = dataBean.getAuthor();
        holder.nameTv.setText(author.getName());
        holder.descTv.setText(author.getDescription());
        String iconURL = author.getIcon();
        if (!TextUtils.isEmpty(iconURL)) {
            Picasso.get().load(iconURL).into(holder.iconIv);
        }

        // 获取点赞数和评论数
        VideoBean.ItemListBean.DataBean.ConsumptionBean consumptionBean = dataBean.getConsumption();
        holder.heartTv.setText(consumptionBean.getCollectionCount()+"");//加一个空字符串可以转为字符串类型
        holder.replyTv.setText(consumptionBean.getReplyCount()+"");

        // 设置视频播放器的信息  jiaozivideoplayer应用
        holder.jzvdStd.setUp(dataBean.getPlayUrl(),dataBean.getTitle(),JzvdStd.SCREEN_NORMAL);
        String thumbUrl = dataBean.getCover().getFeed();// 缩略图的网络地址
        Picasso.get().load(thumbUrl).into(holder.jzvdStd.thumbImageView);
        holder.jzvdStd.positionInList = position;

        return convertView;
    }
    //堆内存
    //栈内存
    //方法区
    //寄存器

    // 减少fvb的次数,优化栈内存
    class ViewHolder{
        JzvdStd jzvdStd;
        ImageView iconIv;
        TextView nameTv, descTv, heartTv, replyTv;
        public ViewHolder(View view) {
            jzvdStd = view.findViewById(R.id.item_main_jzvd);
            iconIv = view.findViewById(R.id.item_main_iv);
            nameTv = view.findViewById(R.id.item_main_tv_name);
            descTv = view.findViewById(R.id.item_main_tv_des);
            heartTv = view.findViewById(R.id.item_main_tv_heart);
            replyTv = view.findViewById(R.id.item_main_tv_reply);
        }
    }
}
