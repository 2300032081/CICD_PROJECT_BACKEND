package com.klu.CICDPROJECT.repository;

import com.klu.CICDPROJECT.entity.Portfolio;
import com.klu.CICDPROJECT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByUser(User user);
}
