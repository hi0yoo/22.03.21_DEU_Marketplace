package com.deu.marketplace.query.dto;

import com.deu.marketplace.domain.deal.entity.DealState;
import com.deu.marketplace.domain.item.entity.Classification;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BuyItemDto {
    private Long itemId;
    private Classification classification;
    private String itemImgFile;
    private String title;
    private Integer price;
    private String lastModifiedDate;
    private Long itemDealId;
    private DealState dealState;
    private Long wishedMemberId;

    @QueryProjection
    public BuyItemDto(Long itemId, Classification classification, String itemImgFile, String title, Integer price,
                      LocalDateTime lastModifiedDate, Long itemDealId,
                      DealState dealState, Long wishedMemberId) {
        this.itemId = itemId;
        this.classification = classification;
        this.itemImgFile = itemImgFile;
        this.title = title;
        this.price = price;
        this.lastModifiedDate = lastModifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.itemDealId = itemDealId;
        this.dealState = dealState;
        this.wishedMemberId = wishedMemberId;
    }
}
