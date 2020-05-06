package com.linktech.apigateway.apigateway.Models;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@JsonDeserialize(as = MongoUserDetails.class)
public class MongoUserDetails implements UserDetails
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Integer active;
    private boolean isLocked;
    private boolean isExpired;
    private boolean isEnabled;
    private boolean isAdmin;

    public MongoUserDetails(String username, String password, Integer active, boolean isLocked, boolean isExpired, boolean isEnabled, boolean isAdmin, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.isLocked = isLocked;
        this.isExpired = isExpired;
        this.isEnabled = isEnabled;
        this.isAdmin = isAdmin;
        this.grantedAuthorities = grantedAuthorities;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getActive() {
        return this.active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public boolean isIsLocked() {
        return this.isLocked;
    }

    public boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isIsExpired() {
        return this.isExpired;
    }

    public boolean getIsExpired() {
        return this.isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isIsEnabled() {
        return this.isEnabled;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return this.grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public MongoUserDetails username(String username) {
        this.username = username;
        return this;
    }

    public MongoUserDetails password(String password) {
        this.password = password;
        return this;
    }

    public MongoUserDetails active(Integer active) {
        this.active = active;
        return this;
    }

    public MongoUserDetails isLocked(boolean isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public MongoUserDetails isExpired(boolean isExpired) {
        this.isExpired = isExpired;
        return this;
    }

    public MongoUserDetails isAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public MongoUserDetails grantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", active='" + getActive() + "'" +
            ", isLocked='" + isIsLocked() + "'" +
            ", isExpired='" + isIsExpired() + "'" +
            ", isEnabled='" + isIsEnabled() + "'" +
            ", isAdmin='" + isIsAdmin() + "'" +
            ", grantedAuthorities='" + getGrantedAuthorities() + "'" +
            "}";
    }
    private List<GrantedAuthority> grantedAuthorities;

    public MongoUserDetails(String username, String password,Integer active, boolean isLocked, boolean isExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.isLocked = isLocked;
        this.isExpired = isExpired;
        this.isEnabled = isEnabled;
        // this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }

    public MongoUserDetails(String username,  String [] authorities) {
        this.username = username;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }

    public MongoUserDetails() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}