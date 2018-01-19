package org.cuacfm.contests.api.service.custom;

import java.util.HashSet;
import java.util.Set;

public class CategoryJSON {
    private String id;
    private String name;
    private String desc = "";

    private Set<CandidateJSON> candidates = new HashSet<>();

    public CategoryJSON(String id, String name, String desc, Set<CandidateJSON> candidates) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.candidates = candidates;
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

    public Set<CandidateJSON> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<CandidateJSON> candidates) {
        this.candidates = candidates;
    }

}
