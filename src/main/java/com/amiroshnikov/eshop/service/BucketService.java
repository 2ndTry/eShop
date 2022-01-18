package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.domain.Bucket;
import com.amiroshnikov.eshop.domain.User;
import com.amiroshnikov.eshop.dto.BucketDto;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProducts(Bucket bucket, List<Long> productIds);
    BucketDto getBucketByUserName(String name);
    void commitBucketToOrder(String username);
}
