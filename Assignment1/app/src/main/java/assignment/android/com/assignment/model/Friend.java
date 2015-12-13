package assignment.android.com.assignment.model;

import java.util.ArrayList;

/**
 * Created by Aparna_Bhure on 12/13/2015.
 */
public class Friend {
    private String mName = null;
    private String mPhotoUrl = null;
    private String mPhone = null;
    private String mEmail = null;
    private String mContactUrl = null;
    private ArrayList<Card> mStories = null;

    public void setName(String name){
        this.mName = name;
    }
    public String getName(){
        return this.mName;
    }
    public void setPhotoUrl(String photoUrl){
        this.mPhotoUrl = photoUrl;
    }
    public String getPhotoUrl(){
        return this.mPhotoUrl;
    }
    public void setPhone(String phone){
        this.mPhone = phone;
    }
    public String getPhone(){
        return mPhone;
    }
    public void setEmail(String email){
        this.mEmail = email;
    }
    public String getEmail(){
        return this.mEmail;
    }
    public void setContactUrl(String contactUrl){
        this.mContactUrl = contactUrl;
    }
    public String getContactUrl(){
        return this.mContactUrl;
    }
    public void setStories(ArrayList<Card> stories){
        this.mStories = stories;
    }
    public ArrayList<Card> getStories(){
        return this.mStories;
    }
}
