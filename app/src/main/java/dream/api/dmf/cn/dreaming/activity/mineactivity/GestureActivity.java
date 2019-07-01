package dream.api.dmf.cn.dreaming.activity.mineactivity;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class GestureActivity extends BaseMvpActivity<presenter> implements Contract.Iview {



    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_gesture;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

    }
}
