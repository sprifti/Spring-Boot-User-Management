package com.amdtia.usermanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String title;
    @NotBlank
    private String description;
    @ManyToMany
    @JoinTable(name = "groups_permissions", joinColumns = @JoinColumn(name = "permissions_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Groups> groups = new HashSet<>();



    public Permissions(@NotBlank String title) {
        this.title = title;
    }

    public Permissions(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public Permissions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
