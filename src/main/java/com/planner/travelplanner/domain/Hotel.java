package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotelId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("rankingPosition")
    private Integer rankingPosition;

    @JsonProperty("countryCode")
    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "priceBreakdown")
    @JsonProperty("priceBreakdown")
    private PriceBreakdown priceBreakdown;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("checkoutDate")
    private String checkoutDate;

    @JsonProperty("checkinDate")
    private String checkinDate;

    @JsonProperty("reviewScore")
    private Double reviewScore;

    @JsonProperty("reviewScoreWord")
    private String reviewScoreWord;


    @JsonProperty("qualityClass")
    private Integer qualityClass;



    public Hotel() {
    }

    public Hotel(long hotelId, Integer id, String name, Integer position, Integer rankingPosition, String countryCode, PriceBreakdown priceBreakdown, String currency, String checkoutDate, String checkinDate, Double reviewScore, String reviewScoreWord, Integer qualityClass) {
        this.hotelId = hotelId;
        this.id = id;
        this.name = name;
        this.position = position;
        this.rankingPosition = rankingPosition;
        this.countryCode = countryCode;
        this.priceBreakdown = priceBreakdown;
        this.currency = currency;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reviewScore = reviewScore;
        this.reviewScoreWord = reviewScoreWord;
        this.qualityClass = qualityClass;

    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getRankingPosition() {
        return rankingPosition;
    }

    public void setRankingPosition(Integer rankingPosition) {
        this.rankingPosition = rankingPosition;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public PriceBreakdown getPriceBreakdown() {
        return priceBreakdown;
    }

    public void setPriceBreakdown(PriceBreakdown priceBreakdown) {
        this.priceBreakdown = priceBreakdown;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewScoreWord() {
        return reviewScoreWord;
    }

    public void setReviewScoreWord(String reviewScoreWord) {
        this.reviewScoreWord = reviewScoreWord;
    }




    public Integer getQualityClass() {
        return qualityClass;
    }

    public void setQualityClass(Integer qualityClass) {
        this.qualityClass = qualityClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return hotelId == hotel.hotelId && Objects.equals(id, hotel.id) && Objects.equals(name, hotel.name) && Objects.equals(position, hotel.position) && Objects.equals(rankingPosition, hotel.rankingPosition) && Objects.equals(countryCode, hotel.countryCode) && Objects.equals(priceBreakdown, hotel.priceBreakdown) && Objects.equals(currency, hotel.currency) && Objects.equals(checkoutDate, hotel.checkoutDate) && Objects.equals(checkinDate, hotel.checkinDate) && Objects.equals(reviewScore, hotel.reviewScore) && Objects.equals(reviewScoreWord, hotel.reviewScoreWord) && Objects.equals(qualityClass, hotel.qualityClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, id, name, position, rankingPosition, countryCode, priceBreakdown, currency, checkoutDate, checkinDate, reviewScore, reviewScoreWord, qualityClass);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", rankingPosition=" + rankingPosition +
                ", countryCode='" + countryCode + '\'' +
                ", priceBreakdown=" + priceBreakdown +
                ", currency='" + currency + '\'' +
                ", checkoutDate='" + checkoutDate + '\'' +
                ", checkinDate='" + checkinDate + '\'' +
                ", reviewScore=" + reviewScore +
                ", reviewScoreWord='" + reviewScoreWord + '\'' +
                ", qualityClass=" + qualityClass +
                '}';
    }
}
