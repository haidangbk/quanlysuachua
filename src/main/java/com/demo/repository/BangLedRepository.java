package com.demo.repository;

import java.util.List;
import com.demo.entities.BangLed;

public interface BangLedRepository {
  public void addBangLed(BangLed bangLed);

  public List<BangLed> getAllBangLed();
}
