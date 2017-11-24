package com.vianet.open.demo.rest.request;

import java.io.Serializable;

public class GetAuthorizationInfoReq implements Serializable{

    private static final long serialVersionUID = 1L;

    private String topicId;
    private String scope;

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "GetAuthorizationInfoReq [topicId=" + topicId + ", scope=" + scope + "]";
    }

}
