package com.timilehin.persistentstorage;

/**
 * Created by tijanioluwatimilehin on 8/15/17.
 */

public class Items
{

    private int _id;
    private String _choreName;

    public Items(String choreName)
    {

        this._choreName = choreName;

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_choreName(String _choreName) {
        this._choreName = _choreName;
    }

    public int get_id() {
        return _id;
    }

    public String get_choreName() {
        return _choreName;
    }
}
