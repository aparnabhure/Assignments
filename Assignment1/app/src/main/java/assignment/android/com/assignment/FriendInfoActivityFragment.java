package assignment.android.com.assignment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import assignment.android.com.assignment.model.Friend;
import assignment.android.com.assignment.utils.JSONParser;

/**
 * A placeholder fragment containing a simple view.
 */
public class FriendInfoActivityFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    Friend mFriend;

    public FriendInfoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_info, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFriend = parseFriendInfo();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFriend != null && mFriend.getStories() != null){
            updateFriendsinfo(getView(), mFriend);
            StoriesAdapter adapter = new StoriesAdapter(getActivity(), mFriend.getStories());
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void updateFriendsinfo(View view, final Friend friend){
        TextView name = (TextView)view.findViewById(R.id.tv_name);
        name.setText(friend.getName());

        ImageView contactUrl = (ImageView)view.findViewById(R.id.iv_contacturl);
        if(friend.getContactUrl() != null) {
            contactUrl.setVisibility(View.VISIBLE);
            contactUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUrl(friend.getContactUrl());
                }
            });
        }else{
            contactUrl.setVisibility(View.GONE);
        }

        contactUrl = null;
        ImageView phone = (ImageView)view.findViewById(R.id.iv_makeCall);
        if(friend.getPhone() != null){
            phone.setVisibility(View.VISIBLE);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeCall(friend.getPhone());
                }
            });
        }else{
            phone.setVisibility(View.GONE);
        }
        phone = null;
        ImageView email = (ImageView)view.findViewById(R.id.iv_sendMail);
        if(friend.getEmail() != null){
            email.setVisibility(View.VISIBLE);
            email.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    sendMail(friend.getEmail());
                }
            });
        }else{
            email.setVisibility(View.GONE);
        }
        email = null;
        ImageView photo = (ImageView)view.findViewById(R.id.iv_photo);
        if(friend.getPhotoUrl() != null){
            photo.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(Uri.parse(friend.getPhotoUrl())).into(photo);
        }else{
            photo.setVisibility(View.GONE);
        }
        photo = null;
    }

    private void sendMail(String email){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        if(emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        }
    }
    private void makeCall(String phone){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void openUrl(String urlToOpen){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlToOpen));
        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private Friend parseFriendInfo(){
        InputStream inputStream = getResources().openRawResource(R.raw.sample_response);
        String sxml = readTextFile(inputStream);
        if(sxml != null){
            try {
                JSONObject jsonObject = new JSONObject(sxml);
                JSONParser parser = new JSONParser();
                Friend friend = parser.parseFriendsInfo(jsonObject);
                return friend;
            }catch(JSONException e){

            }
        }
        return null;
    }


    public String readTextFile(InputStream inputStream) {
        if (inputStream == null) return null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
