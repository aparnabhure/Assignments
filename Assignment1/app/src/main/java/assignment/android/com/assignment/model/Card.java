package assignment.android.com.assignment.model;

/**
 * Created by Aparna_Bhure on 12/13/2015.
 */
public class Card {
    public interface CARD_TYPE{
        byte SIMPLE = 0;
        byte CHECKIN = (SIMPLE + 1);
    }
    protected byte mCardType;
    protected String mTitle;

    public void setCardType(byte cardType){
        this.mCardType = cardType;
    }
    public byte getCardType(){
        return this.mCardType;
    }
    public void setTitle(String title){
        this.mTitle = title;
    }
    public String getTitle(){
        return this.mTitle;
    }
}
