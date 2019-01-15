package com.emretemur.muzeist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(final View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }
        public void setDetails(Context ctx,String title, String description, String image, String koordinat, String address, String visitor, String telno)
        {
            TextView nMuzeNameTxt = mView.findViewById(R.id.muzeNameTxt);
            TextView nDetailTxt= mView.findViewById(R.id.muzeDetailTxt);
            TextView nKoordTxt= mView.findViewById(R.id.koordinatTxt);
            TextView nVisitorTxt= mView.findViewById(R.id.visitorTxt);
            TextView nTelnoTxt= mView.findViewById(R.id.telnoTxt);
            TextView nAddressTxt= mView.findViewById(R.id.addressTxt);
            ImageView nMuzeImage = mView.findViewById(R.id.muzeImage);
            nMuzeNameTxt.setText(title);
            nDetailTxt.setText(description);
            nVisitorTxt.setText(visitor);
            nTelnoTxt.setText(telno);
            nAddressTxt.setText(address);
            nKoordTxt.setText(koordinat);
            Picasso.get().load(image).into(nMuzeImage);
        }
        private ViewHolder.ClickListener mClickListener;
        public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

    }
    void  setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
