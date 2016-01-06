package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRate implements Serializable{

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RateID id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the contribution that is rated", required = true)
    private Contribution contribution;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "The User that created this rate", required = true)
    private User user;

    @ApiObjectField(description = "value of rate, either +1,-1 or 0", required = false)
    private Integer value;

    public UserRate() {
    }

    public UserRate(Contribution contribution, User user, Integer value) {
        this.id = new RateID(contribution.getId(),user.getUsername());

        this.contribution = contribution;
        this.user = user;
        this.value = value;
    }

    public RateID getId() {
        return id;
    }

    public void setId(RateID id) {
        this.id = id;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Embeddable
    public static class RateID implements Serializable {
        private static final long serialVersionUID = 1L;

        @GeneratedValue
        protected Integer contributionId;

        @GeneratedValue
        protected String userId;

        public RateID() {
        }

        public RateID(Integer contributionId,String userId) {
            this.contributionId = contributionId;
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getContributionId() {
            return contributionId;
        }

        public void setContributionId(Integer contributionId) {
            this.contributionId = contributionId;
        }

        @Override
        public String toString() {
            return userId + "_" + contributionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RateID rateID = (RateID) o;

            if (!contributionId.equals(rateID.contributionId)) return false;
            return userId.equals(rateID.userId);

        }

        @Override
        public int hashCode() {
            int result = contributionId.hashCode();
            result = 31 * result + userId.hashCode();
            return result;
        }
    }
}
