package com.example.objclassifier.data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ObjectsGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @ManyToMany(mappedBy = "groups")
    Set<ClassifiedImage> images;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ClassifiedImage> getImages() {
        return this.images;
    }

    protected ObjectsGroup() {
    }

    public ObjectsGroup(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Group[id=%d, name='%s']", id, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (!(that instanceof ObjectsGroup)) {
            return false;
        }

        ObjectsGroup it = (ObjectsGroup) that;
        return this.id == it.id && this.name.equals(it.name);
    }
}
