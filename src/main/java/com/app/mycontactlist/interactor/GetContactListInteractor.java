package com.app.mycontactlist.interactor;

import com.app.mycontactlist.common.net.APIResponseListener;
import com.app.mycontactlist.model.ContactListParent;

import java.util.List;

public interface GetContactListInteractor {
        void getContactList(final APIResponseListener<List<ContactListParent>> listener);
}
