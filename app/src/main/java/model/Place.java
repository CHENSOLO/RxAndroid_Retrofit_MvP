package model;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/12/26.
 */

public class Place implements Comparable<Place> {

    private Long id;
    private String label;
    private String name;
    private String pinyin;
    private String province;
    public Place() {
    }

    public Place(Long id, String label, String name, String pinyin, String province) {
        this.id = id;
        this.label = label;
        this.name = name;
        this.pinyin = pinyin;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public int compareTo(@NonNull Place place) {
        return 0;
    }
}
