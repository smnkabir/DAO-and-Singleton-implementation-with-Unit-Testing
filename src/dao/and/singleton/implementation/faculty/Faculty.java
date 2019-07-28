/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.faculty;

import java.util.Objects;

/**
 *
 * @author nk
 */
public class Faculty {
    private String initials;
    private String name;
    private String rank;

    public Faculty(String initials, String name, String rank) {
        this.initials = initials;
        this.name = name;
        this.rank = rank;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Faculty other = (Faculty) obj;
        if (!Objects.equals(this.initials, other.initials)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Faculty{" + "initials=" + initials + ", name=" + name + ", rank=" + rank + '}';
    }

    
    
}
