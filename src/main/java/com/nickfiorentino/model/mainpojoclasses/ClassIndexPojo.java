package com.nickfiorentino.model.mainpojoclasses;

import com.nickfiorentino.model.extrapojoclassandraceclasses.Links;

import java.util.List;

public class ClassIndexPojo {

    private Links links;
    private List<OneClassPojo> classes;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<OneClassPojo> getClasses() {
        return classes;
    }

    public void setClasses(List<OneClassPojo> classes) {
        this.classes = classes;
    }

}
