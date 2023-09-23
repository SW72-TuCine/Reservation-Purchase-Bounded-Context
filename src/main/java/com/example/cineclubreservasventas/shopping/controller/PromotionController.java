package com.example.cineclubreservasventas.shopping.controller;

import com.example.cineclubreservasventas.shopping.entity.Promotion;
import com.example.cineclubreservasventas.shopping.service.inter.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/TuCine/v1")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotions/{promotionId}")
    public ResponseEntity<Promotion> getPromotion(Long promotionId){
        return new ResponseEntity<>(promotionService.getPromotion(promotionId), org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/promotions")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion){
        return new ResponseEntity<>(promotionService.createPromotion(promotion), org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/promotions/{promotionId}")
    public ResponseEntity<Promotion> modifyPromotion(@PathVariable Long promotionId, @RequestBody Promotion updatedPromotion){
        return new ResponseEntity<>(promotionService.modifyPromotion(promotionId, updatedPromotion), org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/promotions/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId){
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>(org.springframework.http.HttpStatus.NO_CONTENT);
    }
}