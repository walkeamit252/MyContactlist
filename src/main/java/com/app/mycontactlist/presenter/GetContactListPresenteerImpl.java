package com.app.mycontactlist.presenter;

import android.content.Context;

import com.app.mycontactlist.common.net.APIResponseListener;
import com.app.mycontactlist.common.utils.DialogUtil;
import com.app.mycontactlist.common.utils.NetworkUtil;
import com.app.mycontactlist.interactor.GetContactListInteracotrImpl;
import com.app.mycontactlist.model.ContactListParent;
import com.app.mycontactlist.ui.ContactlistDetailView;

import java.util.List;


public class GetContactListPresenteerImpl implements GetContactListPresenter {
    Context mContext;
    GetContactListInteracotrImpl mContactListInteractor;
    ContactlistDetailView mContactListView;



    public GetContactListPresenteerImpl(Context mContext, ContactlistDetailView mContactListView) {
        this.mContext = mContext;
        this.mContactListView = mContactListView;
        mContactListInteractor = new GetContactListInteracotrImpl(mContext);
    }

    @Override
    public void getContactList() {
        if (!NetworkUtil.isAvailable(mContext)) {
            DialogUtil.showNoNetworkAlert(mContext);
            return;
        }

        mContactListInteractor.getContactList(new APIResponseListener<List<ContactListParent>>() {
            @Override
            public void onSuccessResponse(List<ContactListParent> response) {
                mContactListView.onContactListSuccess(response);
            }
            @Override
            public void onResponseError(String message) {
                mContactListView.onContactListFail(message);
            }
        });
    }
}
