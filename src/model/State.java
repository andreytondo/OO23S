package model;

import persistence.utils.Column;
import persistence.utils.Entity;
import persistence.utils.Id;
import persistence.utils.Table;

@Table(name = "state")
public class State implements Entity<Integer> {

    @Id
    @Column(name = "state_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "uf")
    private String uf;

    @Override
    public String toString() {
        return name + " (" + uf + ")";
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
