package assignment.android.com.assignment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import assignment.android.com.assignment.model.Card;
import assignment.android.com.assignment.model.CheckinCard;
import assignment.android.com.assignment.model.SimpleCard;

/**
 * Created by Aparna_Bhure on 12/13/2015.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    List<Card> mItems;
    Context mContext;

    public StoriesAdapter(Context context, List<Card> cards) {
        super();
        this.mItems = cards;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = null;
        Card card = mItems.get(i);
        if (card.getCardType() == Card.CARD_TYPE.SIMPLE){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.simple_card_view, viewGroup, false);
            viewHolder = new ViewHolder(v);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.checkin_card_view, viewGroup, false);
            viewHolder = new ViewHolder(v);
            viewHolder.ivLocationIcon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if(tag instanceof String){
                        String locationUrl = (String)tag;
                        openUrl(locationUrl);
                    }
                }
            });
            viewHolder.tvMoreImages.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if(tag instanceof String){
                        String locationUrl = (String)tag;
                        openUrl(locationUrl);
                    }
                }
            });
        }
        return viewHolder;
    }

    private void openUrl(String urlToOpen){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlToOpen));
        mContext.startActivity(i);
    }
    @Override
    public int getItemViewType(int position) {
        Card card = mItems.get(position);
        return card.getCardType();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Card nature = mItems.get(i);
        viewHolder.tvTitle.setText(nature.getTitle());
        if(nature.getCardType() == Card.CARD_TYPE.SIMPLE){
            SimpleCard simpleCard = (SimpleCard)nature;
            if(viewHolder.tvContent != null)
                viewHolder.tvContent.setText(simpleCard.getContent());
        }else{
            CheckinCard checkinCard = (CheckinCard)nature;
            boolean isLocationUrlPresent = ((checkinCard.getLocationUrl() != null && checkinCard.getLocationUrl().trim().length() > 0) ?true:false);
            if (viewHolder.ivLocationIcon != null) {
                viewHolder.ivLocationIcon.setVisibility(isLocationUrlPresent ? View.VISIBLE : View.GONE);
                viewHolder.ivLocationIcon.setTag(checkinCard.getLocationUrl());
            }
            if(viewHolder.tvMoreImages != null){
                if (checkinCard.getMoreImagesUrl() != null && checkinCard.getMoreImagesUrl().trim().length() > 0) {
                    viewHolder.tvMoreImages.setTag(checkinCard.getMoreImagesUrl());
                    viewHolder.tvMoreImages.setVisibility(View.VISIBLE);
                    viewHolder.tvMoreImages.setText(Html.fromHtml("<U>" + mContext.getResources().getString(R.string.more_images) + "</U>"));
                }else{
                    viewHolder.tvMoreImages.setVisibility(View.GONE);
                }
            }
            if(viewHolder.ivPhoto != null){
                Picasso.with(mContext).load(checkinCard.getImageUrl()).into(viewHolder.ivPhoto);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivLocationIcon;
        public TextView tvTitle;
        public TextView tvContent;
        public TextView tvMoreImages;
        public ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLocationIcon = (ImageView)itemView.findViewById(R.id.locationIcon);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            tvMoreImages = (TextView)itemView.findViewById(R.id.tv_moreimages);
            ivPhoto = (ImageView)itemView.findViewById(R.id.iv_photo);
        }
    }
}