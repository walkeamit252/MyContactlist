package com.app.mycontactlist.ui;

import com.app.mycontactlist.model.ContactListParent;

import java.util.List;

public interface ContactlistDetailView {
    void onContactListSuccess(List<ContactListParent> mContactListParent);
    void onContactListFail(String message);
}
