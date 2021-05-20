package com.github.SemenovAlex888.VotingRestaurant.to;

public class SumVotes {

    private Integer restaurantId;
    private String restaurantName;
    private Long sumVotes;

    public SumVotes() {
    }

    public SumVotes(Integer restaurantId, String restaurantName, Long sumVotes) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.sumVotes = sumVotes;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getSumVotes() {
        return sumVotes;
    }

    public void setSumVotes(Long sumVotes) {
        this.sumVotes = sumVotes;
    }

    @Override
    public String toString() {
        return "sumVotes{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", sumVotes=" + sumVotes +
                '}';
    }
}
