package com.example.cineclubreservasventas.shopping.service.inter;

import com.example.cineclubreservasventas.shopping.entity.Promotion;

public interface PromotionService {
    public Promotion createPromotion(Promotion promotion);
    public Promotion modifyPromotion(Long id, Promotion promotion);
    public void deletePromotion(Long id);
    public Promotion getPromotion(Long id);
    public boolean checkIfUserExist(Long userId);
}
