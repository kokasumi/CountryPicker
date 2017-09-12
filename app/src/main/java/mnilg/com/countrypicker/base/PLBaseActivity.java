package mnilg.com.countrypicker.base;

/**
 * PLBaseActivity
 * @author ligang
 * @date 16/10/24 15:58
 * @email gang.li@mengqitech.com
 * @description 项目中的基类Activity，与BaseActivity最主要的区别在于，在该Activity中会统一做一些逻辑处理
 */
public abstract class PLBaseActivity<T extends BasePresenter> extends BaseActivity<T> {

    @Override
    protected T createPresenter() {
        return null;
    }
}
