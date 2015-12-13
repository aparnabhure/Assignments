package assignment.android.com.assignment.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import assignment.android.com.assignment.model.Card;
import assignment.android.com.assignment.model.CheckinCard;
import assignment.android.com.assignment.model.Friend;
import assignment.android.com.assignment.model.SimpleCard;

/**
 * Created by Aparna_Bhure on 12/13/2015.
 */
public class JSONParser {
    public Friend parseFriendsInfo(JSONObject friendsInfo){
        Friend friend = null;
        if(friendsInfo == null)
            return friend;

        friend = new Friend();
        friend.setName(getString(friendsInfo, Constants.JSON_KEYS.NAME));
        friend.setPhotoUrl(getString(friendsInfo, Constants.JSON_KEYS.PHOTO));
        friend.setPhone(getString(friendsInfo, Constants.JSON_KEYS.PHONE));
        friend.setEmail(getString(friendsInfo, Constants.JSON_KEYS.EMAIL));
        friend.setContactUrl(getString(friendsInfo, Constants.JSON_KEYS.CONTACT_URL));
        friend.setStories(parseCards(getJSONArray(friendsInfo, Constants.JSON_KEYS.OUR_STORY)));
        return friend;
    }

    private ArrayList<Card> parseCards(JSONArray storyArray){
        ArrayList<Card> cards = null;
        if(storyArray == null || storyArray.length() <= 0)
            return cards;
        cards = new ArrayList<Card>();
         for(int i=0; i<storyArray.length();i++){
             try {
                 JSONObject card = storyArray.getJSONObject(i);
                 if(card != null){
                     if(card.has(Constants.JSON_KEYS.TYPE)){
                        String type = card.getString(Constants.JSON_KEYS.TYPE);
                         if(type != null && type.trim().length() > 0){
                             if(type.equalsIgnoreCase(Constants.JSON_VALUE.SIMPLE_CARD)){
                                 SimpleCard sCard = new SimpleCard();
                                 sCard.setCardType(Card.CARD_TYPE.SIMPLE);
                                 if (card.has(Constants.JSON_KEYS.TITLE))
                                     sCard.setTitle(card.getString(Constants.JSON_KEYS.TITLE));
                                 if(card.has(Constants.JSON_KEYS.CONTENT))
                                     sCard.setContent(card.getString(Constants.JSON_KEYS.CONTENT));
                                 cards.add(sCard);
                             }else if(type.equalsIgnoreCase(Constants.JSON_VALUE.CHECKIN_CARD)){
                                CheckinCard cCard = new CheckinCard();
                                 cCard.setCardType(Card.CARD_TYPE.CHECKIN);
                                 if (card.has(Constants.JSON_KEYS.TITLE))
                                     cCard.setTitle(card.getString(Constants.JSON_KEYS.TITLE));
                                 if (card.has(Constants.JSON_KEYS.IMAGE_URL))
                                     cCard.setImageUrl(card.getString(Constants.JSON_KEYS.IMAGE_URL));
                                 if (card.has(Constants.JSON_KEYS.LOCATION_URL))
                                     cCard.setLocationUrl(card.getString(Constants.JSON_KEYS.LOCATION_URL));
                                 if (card.has(Constants.JSON_KEYS.MORE_IMAGES))
                                     cCard.setMoreImagesUrl(card.getString(Constants.JSON_KEYS.MORE_IMAGES));
                                 cards.add(cCard);
                             }
                         }
                     }
                 }
             }catch(Exception e){

             }
         }
        return cards;
    }

    private String getString(JSONObject object, String key){
        try{
            if(object != null && object.has(key))
                return object.getString(key);
        }catch(Exception e){}
        return null;
    }
    private JSONArray getJSONArray(JSONObject object, String key){
        try{
            if (object != null && object.has(key))
                return object.getJSONArray(key);
        }catch(Exception e){}
        return null;
    }
}
