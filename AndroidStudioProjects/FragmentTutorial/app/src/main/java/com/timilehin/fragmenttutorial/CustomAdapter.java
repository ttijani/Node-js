package com.timilehin.fragmenttutorial;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by tijanioluwatimilehin on 8/14/17.
 */

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(@NonNull Context context, String[] resource) {
        super(context, R.layout.custom_type, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View customView = mInflater.inflate(R.layout.custom_type, parent, false);

        String item = getItem(position);
        TextView mText = (TextView) customView.findViewById(R.id.profileName);
        ImageView mImage = (ImageView) customView.findViewById(R.id.displayPicture);

        mText.setText(item);
        mImage.setImageResource(R.drawable.ic_menu_gallery);

        return customView;
    }
}
