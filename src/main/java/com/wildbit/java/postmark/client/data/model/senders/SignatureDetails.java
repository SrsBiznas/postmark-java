package com.wildbit.java.postmark.client.data.model.senders;

import com.wildbit.java.postmark.client.data.model.domains.DomainDetails;

/**
 * Created by bash on 11/2/17.
 */
public class SignatureDetails extends DomainDetails {

    private String domain;
    private String emailAddress;
    private String replyToEmailAddress;
    private Boolean confirmed;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getReplyToEmailAddress() {
        return replyToEmailAddress;
    }

    public void setReplyToEmailAddress(String replyToEmailAddress) {
        this.replyToEmailAddress = replyToEmailAddress;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}