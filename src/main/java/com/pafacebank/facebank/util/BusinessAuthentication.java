package com.pafacebank.facebank.util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BusinessAuthentication extends AbstractAuthenticationToken {

    private BusinessToken businessToken;

    public BusinessAuthentication(BusinessToken businessToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setAuthenticated(true);
        this.businessToken = businessToken;
    }

    public BusinessToken getBusinessToken() {
        return businessToken;
    }

    public String getCardOrAcctNo() {
        return businessToken.getClientAcct().getCardOrAcctNo();
    }

    public String getBusinessType() {
        return businessToken.getBusinessInfo().getBusinessType();
    }

    public String getBusinessJnlNo() {
        return businessToken.getBusinessInfo().getBusinessJnlNo();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return businessToken;
    }
}
