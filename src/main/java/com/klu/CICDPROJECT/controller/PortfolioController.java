package com.klu.CICDPROJECT.controller;

import com.klu.CICDPROJECT.dto.PortfolioDTO;
import com.klu.CICDPROJECT.entity.Portfolio;
import com.klu.CICDPROJECT.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(
    origins = "https://cicd-project-frontend-phi.vercel.app/", // your frontend
    allowCredentials = "true"
)
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    // Add new portfolio
    @PostMapping("/add")
    public ResponseEntity<Portfolio> addPortfolio(@RequestBody Portfolio portfolio) {
        Portfolio saved = portfolioService.addPortfolio(portfolio);
        return ResponseEntity.ok(saved);
    }

    // Get portfolio by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<PortfolioDTO> getPortfolioByUser(@PathVariable Long userId) {
        Portfolio portfolio = portfolioService.findByUserId(userId);
        if (portfolio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(portfolioService.mapToDTO(portfolio));
    }
}
