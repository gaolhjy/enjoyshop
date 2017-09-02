package com.cniao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cniao.CNiaoApplication;
import com.cniao.R;
import com.cniao.bean.Campaign;
import com.cniao.bean.HomeCampaignBean;
import com.cniao.utils.GlideUtils;
import com.cniao.utils.LogUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/06
 *     desc   : 首页商品分类的适配器
 *              涉及到多样式的条目的话,不能直接继承CommonAdapter  要不然构造方法中super必须布局这个参数
 *     version: 1.0
 * </pr>
 */

public class HomeCatgoryAdapter extends MultiItemTypeAdapter<HomeCampaignBean> {

    private LayoutInflater          mInflater;
    private List<HomeCampaignBean>  mDatas;
    private Context                 mContext;
    private OnCampaignClickListener mListener;
    private int position = 0;

    public HomeCatgoryAdapter(Context context, List<HomeCampaignBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.mDatas = datas;

        addItemViewDelegate(new LeftItemDelagate());     //添加所有涉及到的样式
        addItemViewDelegate(new RightItemDelagate());
    }


    public void setOnCampaignClickListener(OnCampaignClickListener listener) {
        this.mListener = listener;
    }

    private class LeftItemDelagate implements ItemViewDelegate<HomeCampaignBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.template_home_cardview;
        }

        @Override
        public boolean isForViewType(HomeCampaignBean item, int position) {
            return position % 2 == 0;             //这里是根据位置进行判断具体是哪种条目.如果是非常复杂的,item数据模型中会有type类型
        }

        @Override
        public void convert(ViewHolder holder, HomeCampaignBean bean, final int position) {

            LogUtil.e(TAG, position + "左边", false);

            holder.setText(R.id.text_title, bean.getTitle());
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpOne().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_big));
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpTwo().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_small_top));
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpThree().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_small_bottom));

            //TODO 必须写成内部类的形式,因为使用view.onClick的形式,position传不过去,
            // TODO 因为没有  getLayoutPosition 方法.
            //TODO 奇怪, MultiItemTypeAdapter 是继承 RecyclerView.Adapter<ViewHolder>的,为毛父类有的方法,子类却没有
            holder.getView(R.id.imgview_big).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpOne());
                }
            });

            holder.getView(R.id.imgview_small_top).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpTwo());
                }
            });


            holder.getView(R.id.imgview_small_bottom).setOnClickListener(new View.OnClickListener
                    () {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpThree());
                }
            });
        }

        //        @Override
        //        public void onClick(View v) {
        //
        //            HomeCampaignBean campaign = mDatas.get(position);
        //
        //            switch (v.getId()) {
        //                case R.id.imgview_big:
        //                    mListener.onClick(v, campaign.getCpOne());
        //                    break;
        //                case R.id.imgview_small_top:
        //                    mListener.onClick(v, campaign.getCpTwo());
        //                    break;
        //                case R.id.imgview_small_bottom:
        //                    mListener.onClick(v, campaign.getCpThree());
        //                    break;
        //            }
        //        }


    }


    private class RightItemDelagate implements ItemViewDelegate<HomeCampaignBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.template_home_cardview2;
        }

        @Override
        public boolean isForViewType(HomeCampaignBean item, int position) {
            return !(position % 2 == 0);
        }

        @Override
        public void convert(ViewHolder holder, HomeCampaignBean bean, final int position) {

            LogUtil.e(TAG, position + "右边", false);               //哪个可见,显示的就是哪个


            holder.setText(R.id.text_title, bean.getTitle());
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpOne().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_big));
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpTwo().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_small_top));
            GlideUtils.load(CNiaoApplication.sContext, bean.getCpThree().getImgUrl(), (ImageView)
                    holder.getView(R.id.imgview_small_bottom));

            holder.getView(R.id.imgview_big).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpOne());
                }
            });

            holder.getView(R.id.imgview_small_top).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpTwo());
                }
            });


            holder.getView(R.id.imgview_small_bottom).setOnClickListener(new View.OnClickListener
                    () {
                @Override
                public void onClick(View v) {
                    LogUtil.e(TAG, position + "左边", false);
                    HomeCampaignBean campaign = mDatas.get(position);
                    mListener.onClick(v, campaign.getCpThree());
                }
            });
        }
    }


    public interface OnCampaignClickListener {
        void onClick(View view, Campaign campaign);
    }
}
