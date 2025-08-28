package com.example.demo.repository.impl;

import com.example.demo.dto.UserProfileResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserProfileResponse> searchUsers(String fullName, String address) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserProfileResponse> query = cb.createQuery(UserProfileResponse.class);

        Root<User> user = query.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (fullName != null && !fullName.isEmpty()) {
            predicates.add(cb.like(cb.lower(user.get("fullName")), "%" + fullName.toLowerCase() + "%"));
        }

        if (address != null && !address.isEmpty()) {
            predicates.add(cb.like(cb.lower(user.get("address")), "%" + address.toLowerCase() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.select(cb.construct(
                UserProfileResponse.class,
                user.get("username"),
                user.get("email"),
                user.get("fullName"),
                user.get("phone"),
                user.get("address"),
                user.get("position")
        ));

        return entityManager.createQuery(query).getResultList();
    }
}
