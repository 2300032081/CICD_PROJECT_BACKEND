package com.klu.CICDPROJECT.repository;

import com.klu.CICDPROJECT.entity.Portfolio;
import com.klu.CICDPROJECT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    // existing method
    Portfolio findByUser(User user);

    // new method - find portfolio by username directly
    Optional<Portfolio> findByUserUsername(String username);
}
