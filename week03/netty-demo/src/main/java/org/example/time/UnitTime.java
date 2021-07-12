package org.example.time;

import java.util.Date;

/**
 * @author luzuheng
 * @since 2021-07-12 14:51
 */
public class UnitTime {

  private final long value;

  public UnitTime() {
    this(System.currentTimeMillis() / 1000L + 2208988800L);
  }

  public UnitTime(long value) {
    this.value = value;
  }

  public long value() {
    return value;
  }

  @Override
  public String toString() {
    return new Date((value() - 2208988800L) * 1000L).toString();
  }
}
