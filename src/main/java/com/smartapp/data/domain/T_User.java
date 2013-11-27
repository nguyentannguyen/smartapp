package com.smartapp.data.domain;

import javax.persistence.*;

@Entity @Table(name="T_USER")
public class T_User {
	
	@Id
	private long id;

    @Column(unique=true)
	private String email;

    private String firstname;
	private String lastname;
    private String openid;

    @ManyToOne(optional=false)
    @JoinColumn(name="account_id", nullable=false, referencedColumnName = "id")
    private T_Account account;


    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public T_Account getAccount() {
        return account;
    }

    public void setAccount(T_Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return  "email: " + email + ", name: " + firstname +" "+ lastname + ", openid: " + openid;
    }
}
