package me.amvse.bin.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import me.amvse.bin.forms.NewBinForm;
import me.amvse.bin.models.Bin;
import me.amvse.bin.repositories.BinRepository;
import me.amvse.bin.typings.BinNew;
import me.amvse.bin.typings.BinResponse;
import me.amvse.bin.typings.RestException;

@CrossOrigin
@RestController
public class BinController {
  @Autowired
  private BinRepository binRepository;

  @GetMapping({"/bins/{binId}", "/bins/{binId}/{pass}"})
  public BinResponse getBin (@PathVariable String binId, @PathVariable(required = false) String pass) {
    Bin bin = binRepository.findOneByUid(binId).orElseThrow(() -> new RestException(404, "Bin not found"));

    if (bin.getPassword() != null) {
      if (pass == null) throw new RestException(403, "Bin is password protected");
      else if (!bin.getPassword().equals(getMD5(pass))) throw new RestException(400, "Wrong password");
    }

    return new BinResponse(bin.getUid(), bin.getTitle(), bin.getValue(), bin.getExpireAt(), bin.getCreatedAt());
  }

  @PostMapping("/bins")
  public BinNew createBin (@Valid @RequestBody NewBinForm form, BindingResult br) {
    if (br.hasErrors()) throw new RestException(400, br.getFieldError().getDefaultMessage());

    String hash = form.getPassword() != null ? getMD5(form.getPassword()) : null;
    LocalDate expireAt = form.getExpiration() != null ? LocalDate.parse(form.getExpiration()) : null;

    Bin bin = new Bin(getRandomUid(), form.getTitle().trim(), form.getValue(), hash, expireAt);
    binRepository.save(bin);

    return new BinNew(bin.getUid());
  }

  private static char[] uidTable = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".toCharArray();

  private static String getRandomUid () {
    String uid = "";
    byte[] array = new byte[8];

    new Random().nextBytes(array);
    for (int i = 0; i < 8; i++) uid += uidTable[(array[i] & 0xFF) % 64];

    return uid;
  }

  public static String getMD5 (String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);

      String hashtext = no.toString(16);
      while (hashtext.length() < 32) hashtext = "0" + hashtext;

      return hashtext;
    } catch (Throwable e) {}
    return null;
  }
}
