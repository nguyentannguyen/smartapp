package com.smartapp.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Table(name="T_ACCOUNT")
public class T_Account implements Serializable {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

    @Column(unique=true, name="identifier")
	private String identifier;

    @Column(unique=true,name="uuid")
    private String uuid;

	private String name;

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    private List<T_User> users;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<T_User> getUsers() {
        return users;
    }

    public void setUsers(List<T_User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return  "identifier: " + identifier + ", uuid: " + uuid + ", name: " + name;

    }
}
