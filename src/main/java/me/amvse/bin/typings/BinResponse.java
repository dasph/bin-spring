package me.amvse.bin.typings;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BinResponse {
  private String id;
  private String title;
  private String value;
  private LocalDate expireAt;
  private LocalDateTime createdAt;

  public BinResponse (String id, String title, String value, LocalDate expireAt, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.value = value;
    this.createdAt = createdAt;
    this.expireAt = expireAt;
  }

  public String getId () { return this.id; }
  public String getTitle () { return this.title; }
  public String getValue () { return this.value; }
  public LocalDate getExpireAt () { return this.expireAt; }
  public LocalDateTime getCreatedAt () { return this.createdAt; }
}
