package com.vianet.open.demo.rest.request;

import java.io.Serializable;

public class CreateChannelReq implements Serializable{

    private static final long serialVersionUID = 1L;

    private String version;
    private String scope;

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "CreateChannelReq [version=" + version + ", scope=" + scope + "]";
    }

}
