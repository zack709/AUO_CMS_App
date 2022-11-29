package com.auo.shelf.cmsapp.bean;

import com.auo.shelf.cmsapp.json.Region;

import java.util.ArrayList;

public class Template {
    public int id;
    public String name;
    public String description;
    public int width;
    public int height;
    public ArrayList<Region> regions = new ArrayList<>();
}
