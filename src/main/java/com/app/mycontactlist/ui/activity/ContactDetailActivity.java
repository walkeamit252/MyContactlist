package com.app.mycontactlist.ui.activity;
import android.os.Bundle;
import android.widget.TextView;
import com.app.mycontactlist.R;
import com.app.mycontactlist.model.ContactListParent;

public class ContactDetailActivity extends AbstractActivity {
    TextView mTextViewName,mTextViewNumber,mTextViewEmail,mTextViewCompany,mTexViewAddress;
    ContactListParent mParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        initView();
        getExtraData();
        setData();
    }

    private void setData() {
        if(mParent!=null){
            mTextViewName.setText(mParent.getName());
            mTextViewNumber.setText(mParent.getPhone());
            mTextViewEmail.setText(mParent.getEmail());
            mTextViewCompany.setText(mParent.getCompany().getName());

            String address=mParent.getAddress().getSuite()+", "+mParent.getAddress().getStreet()+", "+
                    mParent.getAddress().getCity()+", Zip-"+mParent.getAddress().getZipcode();
            mTexViewAddress.setText(address);
        }
    }



    private void initView() {
        initUI();
        setVisibilityOfBackButton(true);
        setToolbarTitle(getString(R.string.title_contact_detail));
        mTextViewName=findViewById(R.id.mTextViewName);
        mTextViewNumber=findViewById(R.id.mTextViewContact);
        mTextViewEmail=findViewById(R.id.mTextViewEmail);
        mTextViewCompany=findViewById(R.id.mTextViewCompany);
        mTexViewAddress=findViewById(R.id.mTextViewAddress);
    }

    private void getExtraData(){
        if(getIntent().getExtras()!=null){
            mParent= (ContactListParent) getIntent().getSerializableExtra("model");
        }
    }
}
