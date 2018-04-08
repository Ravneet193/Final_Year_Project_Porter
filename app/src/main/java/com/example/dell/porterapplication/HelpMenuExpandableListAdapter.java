package com.example.dell.porterapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class HelpMenuExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> helpQuestion;
    Map<String,List<String>> helpAnswer;

    public HelpMenuExpandableListAdapter(Context context, List<String> helpQuestion, Map<String, List<String>> helpAnswer) {
        this.context = context;
        this.helpQuestion = helpQuestion;
        this.helpAnswer = helpAnswer;
    }

    @Override
    public int getGroupCount() {
        return helpQuestion.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return helpAnswer.get(helpQuestion.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return helpQuestion.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return helpAnswer.get(helpQuestion.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String helpQuestion = (String)getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_helpmenu_list_parent,null);
        }
        TextView txtParent = (TextView) convertView.findViewById(R.id.txtParent);
        txtParent.setText(helpQuestion);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String helpAnswer = (String)getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_helpmenu_list_child,null);
        }
        TextView txtChild = (TextView) convertView.findViewById(R.id.txtChild);
        txtChild.setText(helpAnswer);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
