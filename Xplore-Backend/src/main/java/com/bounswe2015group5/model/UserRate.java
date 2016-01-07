package com.bounswe2015group5.model;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRate implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * database id of rate
     */
    @EmbeddedId
    private RateID id;

    /**
     * Rated Contribution
     */
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "contributionId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "the contribution that is rated", required = true)
    private Contribution contribution;

    /**
     * Rating User
     */
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    @ApiObjectField(description = "The User that created this rate", required = true)
    private User user;

    /**
     * rate value
     */
    @ApiObjectField(description = "value of rate, either +1,-1 or 0", required = false)
    private Integer value;

    public UserRate() {
    }

    /**
     * Creates UserRate with given contribution, user and rate value
     * @param contribution contribution to be rated
     * @param user rating user
     * @param value rate value
     */
    public UserRate(Contribution contribution, User user, Integer value) {
        this.id = new RateID(contribution.getId(),user.getUsername());

        this.contribution = contribution;
        this.user = user;
        this.value = value;
    }

    /**
     *
     * @return rate id
     */
    public RateID getId() {
        return id;
    }

    /**
     * Sets rate of id
     * @param id rate id
     */
    public void setId(RateID id) {
        this.id = id;
    }

    /**
     *
     * @return contribution of rate
     */
    public Contribution getContribution() {
        return contribution;
    }

    /**
     * Sets contribution of rate
     * @param contribution contribution of rate
     */
    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    /**
     *
     * @return user of rate
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user of rate
     * @param user user of rate
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return value of rate
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets value of rate
     * @param value rate value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Static class that implements composite key of user and contribution
     */
    @Embeddable
    public static class RateID implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Contribution id of rate
         */
        @GeneratedValue
        protected Integer contributionId;

        /**
         * user id of rate
         */
        @GeneratedValue
        protected String userId;

        public RateID() {
        }

        /**
         * Creates rate id given contributionId and userId
         * @param contributionId contribution id
         * @param userId user id
         */
        public RateID(Integer contributionId,String userId) {
            this.contributionId = contributionId;
            this.userId = userId;
        }

        /**
         *
         * @return userId of rate
         */
        public String getUserId() {
            return userId;
        }

        /**
         * Sets user id of rate
         * @param userId user id
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        /**
         *
         * @return contributionId of rate
         */
        public Integer getContributionId() {
            return contributionId;
        }

        /**
         * Sets contributionId of rate
         * @param contributionId of rate
         */
        public void setContributionId(Integer contributionId) {
            this.contributionId = contributionId;
        }

        /**
         * Returns a composite key composed from userId and contributionId
         * @return userId_contributionId
         */
        @Override
        public String toString() {
            return userId + "_" + contributionId;
        }

        /**
         *
         * @param o
         * @return true if given object equals to this rate
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RateID rateID = (RateID) o;

            if (!contributionId.equals(rateID.contributionId)) return false;
            return userId.equals(rateID.userId);

        }

        /**
         *
         * @return hashcode of rateId
         */
        @Override
        public int hashCode() {
            int result = contributionId.hashCode();
            result = 31 * result + userId.hashCode();
            return result;
        }
    }
}
