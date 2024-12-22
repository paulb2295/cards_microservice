package com.bpx.cards_microservice.service.impl;


import com.bpx.cards_microservice.constants.CardsConstants;
import com.bpx.cards_microservice.dto.CardDTO;
import com.bpx.cards_microservice.entity.Card;
import com.bpx.cards_microservice.exception.CardAlreadyExistsException;
import com.bpx.cards_microservice.exception.ResourceNotFoundException;
import com.bpx.cards_microservice.mapper.CardMapper;
import com.bpx.cards_microservice.repository.CardRepository;
import com.bpx.cards_microservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }


    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardDTO fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardsDto(card, new CardDTO());
    }


    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDTO.getCardNumber()));
        CardMapper.mapToCards(cardDTO, card);
        cardRepository.save(card);
        return true;
    }


    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(card.getCardId());
        return true;
    }


}
