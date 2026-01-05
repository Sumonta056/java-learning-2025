package com.javaspring.java_spring.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.javaspring.java_spring.model.Blog;

@Service
public class BlogService {

  private final List<Blog> blogs = new ArrayList<>();
  
}
