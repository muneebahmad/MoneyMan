package com.muneebahmad.moneyman;

import java.util.ArrayList;

/*
 * @author:MuneebAhmad
 * */
public class Parent {
	private String mTitle;
	private ArrayList<String> mArrayChildren;

	// -- getters
	public String getTitle() {
		return mTitle;
	}

	public ArrayList<String> getArrayChildren() {
		return mArrayChildren;
	}

	// -- setters
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public void setArrayChildren(ArrayList<String> mArrayChildren) {
		this.mArrayChildren = mArrayChildren;
	}
}// end class
