
    // Name: Tran Le
    // JAV1 - 1808
    // File name: PersonAdapter.java

package com.sunny.android.letran_ce04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class PersonAdapter extends BaseAdapter {
    private static final long BASE_ID = 0x00001;
    private Context pContext = null;
    private ArrayList<Person> people = null;

    // Constructor
    public PersonAdapter(Context _context, ArrayList<Person> _people) {
        pContext = _context;
        people = _people;
    }

    // Override functions
    @Override
    public int getCount() {
        if (people != null) {
            return people.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (people != null && position >= 0 || position < people.size()) {
            return people.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return BASE_ID+position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(pContext).inflate(R.layout.baseadapter_custom_view,
                    parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        Person humanBeing = (Person)getItem(position);
        if (humanBeing != null) {
            vh.txt_FullName.setText(humanBeing.toString());
            vh.txt_Birthday.setText(humanBeing.getBirthday());
            vh.ivw_Avatar.setImageResource(humanBeing.getImageID());
        }

        return convertView;
    }

    // Optimize with ViewHolder
    static class ViewHolder {
        TextView txt_FullName;
        TextView txt_Birthday;
        ImageView ivw_Avatar;

        public ViewHolder(View _layout) {
            txt_FullName = (TextView) _layout.findViewById(R.id.txt_BaseAdapter_FullName);
            txt_Birthday = (TextView) _layout.findViewById(R.id.txt_BaseAdapter_Birthday);
            ivw_Avatar = (ImageView) _layout.findViewById(R.id.ivw_Avatar);
        }
    }
}
