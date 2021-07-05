package me.amvse.bin.typings;

public class RestException extends RuntimeException {
  private int status;

  public RestException (int status, String message) {
    super(message);
    this.status = status;
  }

  public int getStatus () { return status; }
}
