package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u FROM User u " +
            " LEFT JOIN u.solicitation " +
            " WHERE u.solicitation IS NULL" +
            " OR u.solicitation.status <> 'PENDING' AND u.solicitation.status <> 'REFUSED'"
    )
    Page<User> findAllApprovedUsers(Pageable pageable);
}
