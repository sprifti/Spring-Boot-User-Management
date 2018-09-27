package com.amdtia.usermanagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Paths {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;

    public Paths(String path) {
        this.path = path;
    }
    @ManyToMany(mappedBy = "paths")
    private Set<Permissions> permissions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
