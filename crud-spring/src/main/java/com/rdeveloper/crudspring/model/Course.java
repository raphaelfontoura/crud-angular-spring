package com.rdeveloper.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?")
@Where(clause = "status = 'Active'")
public class Course {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private Long id;

  @NotBlank
  @Size(min = 5, max = 100)
  @Column(length = 100, nullable = false)
  private String name;

  @NotNull
  @Size(max = 10)
  @Pattern(regexp = "back-end|front-end")
  @Column(length = 20, nullable = false)
  private String category;

  @NotNull
  @Size(max = 10)
  @Pattern(regexp = "Active|Inactive")
  @Column(length = 10, nullable = false)
  private String status = "Active";
}
