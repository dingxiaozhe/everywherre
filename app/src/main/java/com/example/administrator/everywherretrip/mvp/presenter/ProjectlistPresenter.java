package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.ProjectlistBean;
import com.example.administrator.everywherretrip.mvp.model.ProjectlistModel;
import com.example.administrator.everywherretrip.mvp.view.ProjectlistView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class ProjectlistPresenter extends BasePresenter<ProjectlistView>{

    private ProjectlistModel projectlistModel;

    @Override
    protected void initModel() {
        projectlistModel = new ProjectlistModel();
        mModels.add(projectlistModel);
    }
    public  void  getProject(){
        projectlistModel.getData(new ResultCallBack<ProjectlistBean>() {
            @Override
            public void onSuccess(ProjectlistBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
