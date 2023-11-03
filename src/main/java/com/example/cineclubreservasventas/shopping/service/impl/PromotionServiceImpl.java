package com.example.cineclubreservasventas.shopping.service.impl;

import com.example.cineclubreservasventas.shopping.entity.Promotion;
import com.example.cineclubreservasventas.shopping.repository.PromotionRepository;
import com.example.cineclubreservasventas.shopping.service.inter.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public Promotion createPromotion(Promotion promotion) {
        List<Promotion> promotions = promotionRepository.findPromotionById(promotion.getId());
        if (promotions != null && !promotions.isEmpty()) {
            for (Promotion promotionDB : promotions) {
                if (promotionDB.getId() == (promotion.getId())) {
                    return promotionDB;
                }
            }
        }
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion modifyPromotion(Long id, Promotion promotion) {
        Promotion promotionDB = getPromotion(promotion.getId());
        if(promotionDB == null){
            return null;
        }
        promotionDB.setTitle(promotion.getTitle());
        promotionDB.setDescription(promotion.getDescription());
        promotionDB.setEndDate(promotion.getEndDate());
        promotionDB.setInitDate(promotion.getInitDate());
        promotionDB.setDiscountPercentage(promotion.getDiscountPercentage());
        return promotionRepository.save(promotionDB);
    }

    @Override
    public void deletePromotion(Long id) {
        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        promotionRepository.delete(existingPromotion);
    }

    @Override
    public Promotion getPromotion(Long id) {
        Promotion promotionDB = promotionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        return promotionDB;
    }

    @Override
    public boolean checkIfUserExist(Long userId){
        return false;
    }
}
