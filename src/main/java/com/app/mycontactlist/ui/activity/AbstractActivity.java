package com.app.mycontactlist.ui.activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.mycontactlist.R;

public class AbstractActivity extends AppCompatActivity {
    protected AbstractActivity currentActivity;
    protected TextView mTextHeader;
    public ImageView mImbBackBtn;

    public void initUI() {
        mTextHeader = findViewById(R.id.text_toolbar_title);
        mImbBackBtn = findViewById(R.id.img_view_back);
        setListenersForBtn();
    }


    public void setListenersForBtn() {
        mImbBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setVisibilityOfBackButton(boolean value){
        if(value){
            mImbBackBtn.setVisibility(View.VISIBLE);
        }else {
            mImbBackBtn.setVisibility(View.INVISIBLE);
        }
    }


    public void setToolbarTitle(String name){
        mTextHeader.setText(name);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}



