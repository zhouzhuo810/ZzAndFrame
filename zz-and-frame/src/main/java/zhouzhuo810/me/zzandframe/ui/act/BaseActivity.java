package zhouzhuo810.me.zzandframe.ui.act;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.common.rx.ExitEvent;
import zhouzhuo810.me.zzandframe.common.rx.RxBus;
import zhouzhuo810.me.zzandframe.ui.adapter.LvAutoBaseAdapter;

/**
 * BaseActivity
 * Created by zhouzhuo810 on 2017/7/25.
 */
public abstract class BaseActivity extends AutoLayoutActivity implements IBaseActivity{

    private boolean isForeground;

    private CompositeSubscription compositeSubscription;

    private Dialog pd;
    private Dialog twoBtnD;
    private Dialog twoBtnEtD;
    private Dialog lvD;
    private Dialog updateD;


    public boolean isForeground() {
        return isForeground;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        registerExitObserver();

        initView();
        initData();
        initEvent();

    }

    private void addSubscription(Subscription subscription) {
        if (compositeSubscription == null)
            compositeSubscription = new CompositeSubscription();
        if (subscription != null)
            compositeSubscription.add(subscription);
    }


    public void closeAllAct() {
        RxBus.getInstance().post(ExitEvent.getInstance());
    }


    private void registerExitObserver() {
        Subscription subscription = RxBus.getInstance()
                .toObserverable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Object event) {
                        if (event instanceof ExitEvent) {
                            closeAct();
                        }
                    }
                });
        addSubscription(subscription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreState(savedInstanceState);
    }

    @Override
    public void startRefresh(final SwipeRefreshLayout refresh) {
        if (refresh != null) {
            refresh.post(new Runnable() {
                @Override
                public void run() {
                    refresh.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void stopRefresh(SwipeRefreshLayout refresh) {
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }

    public void showPd(String msg, boolean cancelable) {
        View convertView = LayoutInflater.from(this).inflate(R.layout.layout_progress_dialog, null);
        AutoUtils.auto(convertView);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tv_msg);
        tvContent.setText(msg);
        pd = new Dialog(this, R.style.transparentWindow);
        Window window = pd.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        pd.onWindowAttributesChanged(wl);
        pd.setCanceledOnTouchOutside(cancelable);
        pd.setContentView(convertView);
        pd.show();
    }

    public void showTwoBtnDialog(String title, String msg, boolean cancelable, final OnTwoBtnClick onTwoBtnClick) {
        View convertView = LayoutInflater.from(this).inflate(R.layout.layout_two_btn_dialog, null);
        AutoUtils.auto(convertView);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tv_msg);
        tvContent.setText(msg);
        twoBtnD = new Dialog(this, R.style.transparentWindow);
        Button btnOk = (Button) convertView.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) convertView.findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTwoBtnDialog();
                if (onTwoBtnClick != null) {
                    onTwoBtnClick.onOk();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTwoBtnDialog();
                if (onTwoBtnClick != null) {
                    onTwoBtnClick.onCancel();
                }
            }
        });
        Window window = twoBtnD.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        twoBtnD.onWindowAttributesChanged(wl);
        twoBtnD.setCanceledOnTouchOutside(cancelable);
        twoBtnD.setContentView(convertView);
        twoBtnD.show();
    }

    //Fixme by zz 2017/7/19 下午12:18 修改内容：添加两个按钮输入对话框
    public void showTwoBtnEditDialog(String title, String hint, String defString, boolean cancelable, final OnTwoBtnEditClick onTwoBtnClick) {
        View convertView = LayoutInflater.from(this).inflate(R.layout.layout_two_btn_et_dialog, null);
        AutoUtils.auto(convertView);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        final EditText etContent = (EditText) convertView.findViewById(R.id.tv_msg);
        etContent.setHint(hint);
        if (defString != null) {
            etContent.setText(defString);
        }
        twoBtnEtD = new Dialog(this, R.style.transparentWindow);
        Button btnOk = (Button) convertView.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) convertView.findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTwoBtnEditDialog();
                if (onTwoBtnClick != null) {
                    String content = etContent.getText().toString().trim();
                    onTwoBtnClick.onOk(content);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTwoBtnEditDialog();
                if (onTwoBtnClick != null) {
                    onTwoBtnClick.onCancel();
                }
            }
        });
        Window window = twoBtnEtD.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        twoBtnEtD.onWindowAttributesChanged(wl);
        twoBtnEtD.setCanceledOnTouchOutside(cancelable);
        twoBtnEtD.setContentView(convertView);
        twoBtnEtD.show();
    }

    public void showUpdateDialog(String title, String msg, boolean cancelable, final OnOneBtnClickListener oneBtnClickListener) {
        hideTwoBtnDialog();
        View convertView = LayoutInflater.from(this).inflate(R.layout.layout_update_dialog, null);
        AutoUtils.auto(convertView);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        final TextView tvPercent = (TextView) convertView.findViewById(R.id.tv_percent);
        tvPercent.setText(msg);
        final ProgressBar pb = (ProgressBar) convertView.findViewById(R.id.dialog_pb);
        pb.setProgress(0);
        if (oneBtnClickListener != null) {
            oneBtnClickListener.onProgress(tvPercent, pb);
        }
        updateD = new Dialog(this, R.style.transparentWindow);
        Button btnOk = (Button) convertView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTwoBtnDialog();
                if (oneBtnClickListener != null) {
                    oneBtnClickListener.onOK();
                }
            }
        });
        Window window = updateD.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        updateD.onWindowAttributesChanged(wl);
        updateD.setCanceledOnTouchOutside(cancelable);
        updateD.setContentView(convertView);
        updateD.show();
    }

    public void hideUpdateDialog() {
        if (updateD != null) {
            updateD.dismiss();
            updateD = null;
        }
    }

    public void showListDialog(final List<String> items, boolean cancelable, DialogInterface.OnDismissListener dismissListener, final OnItemClick onItemClick) {
        hideListDialog();
        View convertView = LayoutInflater.from(this).inflate(R.layout.layout_list_dialog, null);
        AutoUtils.auto(convertView);
        ListView lv = (ListView) convertView.findViewById(R.id.lv);
        lv.setAdapter(new LvAutoBaseAdapter<String>(this, items) {
            @Override
            public int getLayoutId() {
                return R.layout.list_item_lv_dialog;
            }

            @Override
            protected void fillData(ViewHolder holder, String item, int position) {
                holder.setText(R.id.tv_name, item);
            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position, items.get(position));
                }
                hideListDialog();
            }
        });
        lvD = new Dialog(this, R.style.transparentWindow);
        lvD.setOnDismissListener(dismissListener);
        Window window = lvD.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lvD.onWindowAttributesChanged(wl);
        lvD.setCanceledOnTouchOutside(cancelable);
        lvD.setContentView(convertView);
        lvD.show();
    }
    public void hideListDialog() {
        if (lvD != null) {
            lvD.dismiss();
            lvD = null;
        }
    }

    public void hidePd() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    public void hideTwoBtnDialog() {
        if (twoBtnD != null) {
            twoBtnD.dismiss();
            twoBtnD = null;
        }
    }

    public void hideTwoBtnEditDialog() {
        if (twoBtnEtD != null) {
            twoBtnEtD.dismiss();
            twoBtnEtD = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (defaultBack()) {
            super.onBackPressed();
        } else {
            closeAct();
        }
    }

    @Override
    public void closeAct() {
        finish();
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    public int inAnimation() {
        return android.R.anim.slide_in_left;
    }

    @Override
    public int outAnimation() {
        return android.R.anim.slide_out_right;
    }


    @Override
    public void startActWithIntent(Intent intent) {
        startActivity(intent);
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    public void setEditListener(final EditText et, final View ivClear) {
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    ivClear.setVisibility(View.GONE);
                } else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        hidePd();
        hideUpdateDialog();
        hideListDialog();
        hideTwoBtnDialog();
        hideTwoBtnEditDialog();
        if (compositeSubscription != null)
            compositeSubscription.unsubscribe();
    }
}
