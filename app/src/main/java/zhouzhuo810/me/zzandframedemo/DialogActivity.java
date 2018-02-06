package zhouzhuo810.me.zzandframedemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by zhouzhuo810 on 2017/12/14.
 */

public class DialogActivity extends BaseActivity {

    private TitleBar titleBar;
    private Button btnTwoBtnIos;
    private Button btnTwoBtnEtIos;
    private Button btnBottomSheet;
    private Button btnTwoBtnEtIosSingle;

    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        btnTwoBtnIos = (Button) findViewById(R.id.btn_two_btn_ios);
        btnTwoBtnEtIos = (Button) findViewById(R.id.btn_two_btn_et_ios);
        btnTwoBtnEtIosSingle = (Button) findViewById(R.id.btn_two_btn_et_ios_single);
        btnBottomSheet = (Button) findViewById(R.id.btn_custom_bottom);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView() {
        assignViews();


    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        titleBar.setOnTitleClickListener(new TitleBar.OnTitleClick() {
            @Override
            public void onLeftClick(ImageView ivLeft, MarkView mv, TextView tvLeft) {
                closeAct();
            }

            @Override
            public void onTitleClick(TextView tvTitle) {

            }

            @Override
            public void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight) {

            }
        });

        btnTwoBtnIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwoBtnDialogIOSStyle("模式切换", "确认切换到标准显示模式？", "确定", "取消", -1, -1, false, new OnIOSTwoBtnClick() {
                    @Override
                    public void onLeftClick() {
                        ToastUtils.showCustomBgToast("left");
                    }

                    @Override
                    public void onRightClick() {
                        ToastUtils.showCustomBgToast("right");
                    }
                });
            }
        });
        btnTwoBtnEtIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwoBtnEditDialogIOSStyle("保养完成", "确认已完成所有保养工作?", "保养内容",
                        null, "确认", "取消", -1, -1, true, R.drawable.ic_chat, false, new OnIOSTwoBtnEditClick() {
                            @Override
                            public void onImgClick(EditText et) {
                                ToastUtils.showCustomBgToast("say something");
                            }

                            @Override
                            public void onLeftClick(String content) {
                                ToastUtils.showCustomBgToast("left click, "+"content="+content);
                            }

                            @Override
                            public void onRightClick(String content) {
                                ToastUtils.showCustomBgToast("right click, content="+content);
                            }
                        });
            }
        });
        btnTwoBtnEtIosSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwoBtnEditDialogIOSStyleSmall("保养完成", "确认已完成所有保养工作?", "保养内容",
                        null, "确认", "取消", -1, -1, true, R.drawable.ic_chat, false, new OnIOSTwoBtnSmallEditClick() {
                            @Override
                            public void onLeftClick(String content) {
                                ToastUtils.showCustomBgToast("left click, "+"content="+content);
                            }

                            @Override
                            public void onRightClick(String content) {
                                ToastUtils.showCustomBgToast("right click, content="+content);
                            }
                        });
            }
        });

        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View root = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_custom, null);
                Button btnOK = (Button) root.findViewById(R.id.btn_ok);
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideCustomBottomDialog();
                    }
                });
                showCustomBottomDialog(root, false, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
            }
        });


    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(@Nullable Bundle savedInstanceState) {

    }
}
