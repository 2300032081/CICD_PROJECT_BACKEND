package com.klu.CICDPROJECT.service;

import com.klu.CICDPROJECT.dto.PortfolioDTO;
import com.klu.CICDPROJECT.entity.Portfolio;
import com.klu.CICDPROJECT.entity.User;
import com.klu.CICDPROJECT.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    // Save portfolio
    public Portfolio addPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    // Get portfolios by user id
    public Portfolio findByUserId(Long userId) {
        User user = new User();
        user.setId(userId);
        return portfolioRepository.findByUser(user);
    }

    public Portfolio findByUsername(String username) {
    return portfolioRepository.findByUserUsername(username).orElse(null);
}


    // Map Portfolio entity to DTO
    public PortfolioDTO mapToDTO(Portfolio portfolio) {
        PortfolioDTO dto = new PortfolioDTO();
        dto.setId(portfolio.getId());
        dto.setUserId(portfolio.getUser().getId());
        dto.setUsername(portfolio.getUser().getUsername());
        dto.setBio(portfolio.getBio());
        dto.setSkills(portfolio.getSkills());
        dto.setProjects(portfolio.getProjects());
        dto.setSocialLinks(portfolio.getSocialLinks());
        dto.setImageUrl(portfolio.getImageUrl());
        return dto;
    }
}
