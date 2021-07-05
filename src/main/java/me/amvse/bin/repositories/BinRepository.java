package me.amvse.bin.repositories;

import me.amvse.bin.models.Bin;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
  public Optional<Bin> findOneByUid (String uid);
}
