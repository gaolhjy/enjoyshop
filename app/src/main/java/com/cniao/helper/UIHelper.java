package com.cniao.helper;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cniao.R;



/**
 * 应用程序Activity帮助类
 *
 * @author mhdt
 * @version 1.0
 * @created 2014-1-2
 */
public class UIHelper {

    private static Toast toast;

    public static void showMessage(Context context, int stringId) {
        showMessage(context, context.getString(stringId));
    }

    /**
     * 带选择的对话框，默认显示控件底部
     *
     * @param c        上下文
     * @param data     填充的内容，默认一行文字
     * @param mView    显示在这个控件底部
     * @param textView 选中之后，改变显示文字的Textview
     */
//    public static void showPopupwindow(Context c, final List<String> data, View mView, final
//    TextView textView) {
//        final PopupWindow window = new PopupWindow(c);
//        View view = LayoutInflater.from(c).inflate(R.layout.item_popupwindow, null);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(c));
//        recyclerView.addItemDecoration(new DividerItemDecoration(c, DividerItemDecoration
//                .VERTICAL));
//        CommonAdapter<String> adapter = new CommonAdapter<String>(c, R.layout.simple_list, data) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//                holder.setText(R.id.tv_Province, s);
//            }
//        };
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                textView.setText(data.get(position));
//                window.dismiss();
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int
//                    position) {
//                return false;
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        //设置SelectPicPopupWindow的View
//        window.setContentView(view);
//        //设置SelectPicPopupWindow弹出窗体的宽
//        window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体的高
//        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体可点击
//        window.setFocusable(true);
//        //设置SelectPicPopupWindow弹出窗体动画效果
////        popupWindow.setAnimationStyle(R.style.AnimBottom);
//        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(Color.parseColor("#FFFFFF"));
//        //设置SelectPicPopupWindow弹出窗体的背景
//        window.setBackgroundDrawable(dw);
//        window.showAsDropDown(mView, 0, 0);
//    }

    /**
     * @Name: ShowMessage
     * @Description: 弹出提示信息
     * @Author: 黄俊彬
     * @Version: V1.00
     * @Create 2013-8-9
     * @Parameters: context 上下文 meesage提示的信息
     * @Return: 无
     */
    public static void showMessage(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static ProgressDialog showAppProgressBar(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
        return progressDialog;
    }

    public static void dismissAppProgressBar(ProgressDialog progressDialog) {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * 显示提示文本框
     *
     * @param toast 需要显示的提示语句
     */
    public static void setMyCustomToastVisibility(TextView view, String toast) {
        view.setText(toast);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏提示框
     */
    public static void setMyCustomToastInvisible(TextView view) {
        view.setVisibility(View.INVISIBLE);
    }

    private static Dialog dialog;

    /**
     * 自定义dialog,风格参照UI设计图。
     *
     * @param context     上下文
     * @param titleValus  标题
     * @param submitValus 按钮文字
     * @param okListener  点击事件
     */
//    public static void showMyCustomDialog(final Context context, String titleValus, String
//            submitValus, final View.OnClickListener okListener) {
//
//        dialog = new Dialog(context, R.style.dialogActivity);
//        View view = LayoutInflater.from(context).inflate(R.layout.my_custom_dialog_layout,
//                null);
//        TextView title = (TextView) view.findViewById(R.id.tv_custom_title);
//        TextView submit = (TextView) view.findViewById(R.id.tv_custom_ok);
//        ImageView delete = (ImageView) view.findViewById(R.id.iv_delete);
//        title.setText(titleValus);
//        submit.setText(submitValus);
//        dialog.setContentView(view);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                okListener.onClick(v);
//                dialog.dismiss();
//            }
//        });
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialog.show();
//    }

    /**
     * 分享对话框，默认底部弹出
     *
     * @param c
     */
    public static void showShareDialogOnBottom(Context c) {
        dialog = new Dialog(c, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(c).inflate(R.layout.dialog_main_share_bottom, null);
        ImageView close = (ImageView) inflate.findViewById(R.id.iv_dismiss);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.y = 20;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
}
