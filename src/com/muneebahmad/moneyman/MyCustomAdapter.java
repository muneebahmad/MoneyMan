package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends BaseExpandableListAdapter {
	
	private LayoutInflater inflater;
	private ArrayList<Parent> mParent;

	public MyCustomAdapter(Context context, ArrayList<Parent> parent) {
		mParent = parent;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		return mParent.get(arg0).getArrayChildren().get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	// this method sets the text in the children in list
	@Override
	public View getChildView(int i, int i1, boolean b, View view,
			ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = inflater.inflate(R.layout.list_item_child, viewGroup, false);
		}
		TextView textView = (TextView) view
				.findViewById(R.id.list_item_text_child);
		// i is the position of the parent/group in the list
		// i1 is the position of the child
		textView.setText(mParent.get(i).getArrayChildren().get(i1));
		
		// return the entire view
		return view;
	}

	// counts the number of children items so the list knows how many times call
	// this method
	@Override
	public int getChildrenCount(int arg0) {
		return mParent.get(arg0).getArrayChildren().size();
	}

	// gets the title of each parnet/group
	@Override
	public Object getGroup(int arg0) {
		return mParent.get(arg0).getTitle();
	}

	@Override
	// counts the number of group/parent items so the list knows how many times
	// call this method
	public int getGroupCount() {
		return mParent.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	// this method displays the text for the parent/group on the list
	@Override
	public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = inflater
					.inflate(R.layout.list_item_parent, viewGroup, false);
		}// end if
		TextView textView = (TextView) view
				.findViewById(R.id.list_item_text_view);
		// i is the position of the parent/group in the list
		textView.setText(getGroup(i).toString());
		// return the entire view
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// used to make the notifyDataSetChanged() method work
		super.registerDataSetObserver(observer);
	}
}// end class
