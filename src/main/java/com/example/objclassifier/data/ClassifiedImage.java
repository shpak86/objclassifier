package com.example.objclassifier.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ClassifiedImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public Long getId() {
        return id;
    }

    String name;

    public String getName() {
        return name;
    }

    @ManyToMany
    @JoinTable(name = "images_groups", joinColumns = @JoinColumn(name = "classified_image_id"), inverseJoinColumns = @JoinColumn(name = "objects_group_id"))
    public Set<ObjectsGroup> groups = new HashSet<>();

    public Set<ObjectsGroup> getGroups() {
        return this.groups;
    }

    protected ClassifiedImage() {
    }

    public ClassifiedImage(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ClassifiedImage[id=%d, name='%s']", id, name);
    }

    public void addGroup(ObjectsGroup group) {
        groups.add(group);
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

        if (!(that instanceof ClassifiedImage)) {
            return false;
        }

        ClassifiedImage it = (ClassifiedImage) that;
        return this.id == it.id && this.name.equals(it.name);
    }
}
