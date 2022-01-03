package com.amiroshnikov.eshop.dao;

import com.amiroshnikov.eshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
