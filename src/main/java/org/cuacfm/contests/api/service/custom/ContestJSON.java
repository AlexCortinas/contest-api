package org.cuacfm.contests.api.service.custom;

import java.util.ArrayList;
import java.util.List;

public class ContestJSON {
    private String id;
    private String name;
    private String desc = "";
    private boolean voting = false;

    private List<CategoryJSON> categories = new ArrayList<>();
    
    public ContestJSON(String id, String name, String desc, boolean voting, List<CategoryJSON> categories) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.voting = voting;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isVoting() {
        return voting;
    }

    public void setVoting(boolean voting) {
        this.voting = voting;
    }

    public List<CategoryJSON> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryJSON> categories) {
        this.categories = categories;
    }

}
