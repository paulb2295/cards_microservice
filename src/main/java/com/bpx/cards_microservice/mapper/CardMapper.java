package com.bpx.cards_microservice.mapper;


import com.bpx.cards_microservice.dto.CardDTO;
import com.bpx.cards_microservice.entity.Card;

public class CardMapper {

    public static CardDTO mapToCardsDto(Card card, CardDTO cardDTO) {
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setMobileNumber(card.getMobileNumber());
        cardDTO.setTotalLimit(card.getTotalLimit());
        cardDTO.setAvailableAmount(card.getAvailableAmount());
        cardDTO.setAmountUsed(card.getAmountUsed());
        return cardDTO;
    }

    public static Card mapToCards(CardDTO cardDTO, Card card) {
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAvailableAmount(cardDTO.getAvailableAmount());
        card.setAmountUsed(cardDTO.getAmountUsed());
        return card;
    }

}
