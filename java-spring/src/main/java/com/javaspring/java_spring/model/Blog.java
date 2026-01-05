package com.javaspring.java_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
  private Long id;
  private String title;
  private String content;
  private String author;
  private String createdAt;
  private String updatedAt;
}
