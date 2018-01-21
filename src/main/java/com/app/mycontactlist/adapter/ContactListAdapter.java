package com.app.mycontactlist.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mycontactlist.R;
import com.app.mycontactlist.model.ContactListParent;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactAdapterHolder>{

    Context mContext;
    ArrayList<ContactListParent> mContacList;
    OnItemClickListener listener;

    public ContactListAdapter(Context mContext, ArrayList<ContactListParent> mContacList,OnItemClickListener listener){
        this.mContext=mContext;
        this.mContacList=mContacList;
        this.listener=listener;
    }

    public class ContactAdapterHolder extends RecyclerView.ViewHolder {
        TextView mTxtViewName,mTxtViewNumber;
        RelativeLayout mRelativeLayoutMain;
        public ContactAdapterHolder(View itemView) {
            super(itemView);
            mTxtViewName=(TextView) itemView.findViewById(R.id.text_view_contact_name);
            mTxtViewNumber=(TextView) itemView.findViewById(R.id.text_view_contact_num);
            mRelativeLayoutMain=(RelativeLayout)itemView.findViewById(R.id.rel_layout_main);
            mRelativeLayoutMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= (int) mRelativeLayoutMain.getTag();
                    listener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public ContactAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_contact_item,parent,false);
        return new ContactAdapterHolder(view);
    }
    @Override
    public void onBindViewHolder(ContactAdapterHolder holder, final int position) {
        holder.mTxtViewName.setText(mContacList.get(position).getName());
        holder.mTxtViewNumber.setText(mContacList.get(position).getPhone());

        holder.mRelativeLayoutMain.setTag(position);
    }

    public void setPosition(int position){
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return mContacList.size();
    }
}