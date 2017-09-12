package mnilg.com.countrypicker.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import mnilg.com.countrypicker.R;


/**
 * CustomProgressDialog
 * 自定义ProgressDialog
 * @author Li Gang
 *         Date:17/12/2015 16:59
 *         Email:gang.li@mengqitech.com
 */
public class CustomProgressDialog extends Dialog {
    private int anim=0;

    public CustomProgressDialog(Context context){
        super(context);
        initCommProgressDialog();
    }

    public CustomProgressDialog(Context context, int anim) {
        this(context, R.style.CommProgressDialog, anim);
        this.anim=anim;
        initCommProgressDialog();
    }

    private CustomProgressDialog(Context context, int theme, int anim) {
        super(context, theme);
        this.anim=anim;
        initCommProgressDialog();
    }

    private void initCommProgressDialog() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.comm_progress_dialog);
        try {
            getWindow().getAttributes().gravity = Gravity.CENTER;
        }catch (NullPointerException e) {

        }
    }

    public void onWindowFocusChanged(boolean hasFocus){
        ImageView imageView = (ImageView) findViewById(R.id.iv_loading);
        if(anim!=0) {
            imageView.setBackgroundResource(anim);
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * 设置提示内容
     * @param strMessage
     * @return
     */
    public void setMessage(String strMessage){
        TextView tvMsg = (TextView) findViewById(R.id.tv_loading_msg);
        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }
    }

    /**屏蔽返回键**/
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        return keyCode==KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}

