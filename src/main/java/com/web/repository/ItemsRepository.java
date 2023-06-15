package com.web.repository;

import com.web.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends RevisionRepository<Items,Long,Integer>, JpaRepository<Items,Long> {
}
