package me.amvse.bin.models;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "bins")
public class Bin {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(updatable = false, nullable = false, unique = true)
  private String uid;

  @Column(updatable = false, nullable = false)
  private String title;

  @Column(updatable = false, nullable = false)
  private String value;

  @Column(updatable = false)
  private String password;

  @Column(updatable = false)
  private LocalDate expireAt;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  public Bin () {}
  public Bin (String uid, String title, String value, String password, LocalDate expireAt) {
    this.uid = uid;
    this.title = title;
    this.value = value;
    this.password = password;
    this.expireAt = expireAt;
  }

  public Long getId () { return this.id; }
  public String getUid () { return this.uid; }
  public String getTitle () { return this.title; }
  public String getValue () { return this.value; }
  public String getPassword () { return this.password; }
  public LocalDate getExpireAt () { return this.expireAt; }
  public LocalDateTime getCreatedAt () { return this.createdAt; }
}
