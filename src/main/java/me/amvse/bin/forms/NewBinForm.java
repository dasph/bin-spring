package me.amvse.bin.forms;

import javax.validation.constraints.NotBlank;

public class NewBinForm {
  @NotBlank(message = "Please provide a title")
  private String title;

  @NotBlank(message = "Please provide a value")
  private String value;

  private String password;
  private String confirmation;
  private String expiration;

  public String getTitle () { return this.title; }
  public void setTitle (String title) { this.title = title; }

  public String getValue () { return this.value; }
  public void setValue (String value) { this.value = value; }

  public String getPassword () { return this.password; }
  public void setPassword (String password) { this.password = password; }

  public String getConfirmation () { return this.confirmation; }
  public void setConfirmation (String confirmation) { this.confirmation = confirmation; }

  public String getExpiration () { return this.expiration; }
  public void setExpiration (String expiration) { this.expiration = expiration; }
}
