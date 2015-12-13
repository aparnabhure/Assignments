package assignment.android.com.assignment.model;

/**
 * Created by Aparna_Bhure on 12/13/2015.
 */
public class CheckinCard extends Card {
    private String mImageUrl;
    private String mLocationUrl;
    private String mMoreImagesUrl;
    public void setImageUrl(String imageUrl){
        this.mImageUrl = imageUrl;
    }
    public String getImageUrl(){
        return this.mImageUrl;
    }
    public void setLocationUrl(String locationUrl){
        this.mLocationUrl = locationUrl;
    }
    public String getLocationUrl(){
        return this.mLocationUrl;
    }
    public void setMoreImagesUrl(String url){
        this.mMoreImagesUrl = url;
    }
    public String getMoreImagesUrl(){
        return this.mMoreImagesUrl;
    }
}
