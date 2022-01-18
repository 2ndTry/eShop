package com.amiroshnikov.eshop.controller;

import com.amiroshnikov.eshop.dto.BucketDto;
import com.amiroshnikov.eshop.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDto());
        } else {
            BucketDto bucketDto = bucketService.getBucketByUserName(principal.getName());
            model.addAttribute("bucket", bucketDto);
        }
        return "bucket";
    }

    @PostMapping("/bucket")
    public String commitBucket(Principal principal) {
        if (principal != null) {
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }
}
