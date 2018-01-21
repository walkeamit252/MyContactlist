package com.app.mycontactlist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.mycontactlist.R;
import com.app.mycontactlist.adapter.ContactListAdapter;
import com.app.mycontactlist.adapter.OnItemClickListener;
import com.app.mycontactlist.common.utils.DialogUtil;
import com.app.mycontactlist.model.ContactListParent;
import com.app.mycontactlist.presenter.GetContactListPresenteerImpl;
import com.app.mycontactlist.ui.ContactlistDetailView;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AbstractActivity implements ContactlistDetailView ,OnItemClickListener{

    private static final String TAG = "ContactListActivity";
    TextView mTxtViewNoData;
    RecyclerView mRecyclerViewContact;
    ContactListAdapter mContactAdapter;
    ArrayList<ContactListParent> mContactListParent=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initView();
        if(savedInstanceState!=null){
            mContactListParent= (ArrayList<ContactListParent>) savedInstanceState.getSerializable("X");
            setViewIfNoContactList(mContactListParent);
        }
        setAdapterData();
        if(mContactListParent.size()==0){
            callContactListMethod();
        }
    }

    private  void initView(){
        initUI();
        setVisibilityOfBackButton(false);
        setToolbarTitle(getString(R.string.title_contact_list));
        mTxtViewNoData=findViewById(R.id.txt_view_no_contact);
        mRecyclerViewContact=findViewById(R.id.recycler_view_contact_list);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("X",  mContactListParent);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mContactListParent= (ArrayList<ContactListParent>) savedInstanceState.getSerializable("X");
        mContactAdapter.notifyDataSetChanged();
    }



    private  void setAdapterData(){
        mContactAdapter=new ContactListAdapter(this,mContactListParent,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerViewContact.setLayoutManager(manager);
        mRecyclerViewContact.setAdapter(mContactAdapter);
    }

    private void callContactListMethod() {
        GetContactListPresenteerImpl presenter = new GetContactListPresenteerImpl(this, this);
        presenter.getContactList();
    }

    @Override
    public void onContactListSuccess(List<ContactListParent> mList) {
        setViewIfNoContactList(mList);
        mContactListParent.addAll(mList);
        mContactAdapter.notifyDataSetChanged();
    }

    @Override
    public void onContactListFail(String message) {
        DialogUtil.showDebugToast(this,message);
    }

    private void setViewIfNoContactList(List<ContactListParent> mList){
        if(mList.size()>0){
            mTxtViewNoData.setVisibility(View.GONE);
            mRecyclerViewContact.setVisibility(View.VISIBLE);
        }else {
            mTxtViewNoData.setVisibility(View.VISIBLE);
            mRecyclerViewContact.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClick(int position) {
        ContactListParent model=mContactListParent.get(position);
        showContactDetail(model);
    }

    private void showContactDetail(ContactListParent model) {
        Intent contactDetailIntent=new Intent(this,ContactDetailActivity.class);
        contactDetailIntent.putExtra("model",model);
        startActivity(contactDetailIntent);
    }


}
