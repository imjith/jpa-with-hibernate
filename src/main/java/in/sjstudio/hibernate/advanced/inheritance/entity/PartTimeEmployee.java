package in.sjstudio.hibernate.advanced.inheritance.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

  private BigDecimal hourlyWage;

  public PartTimeEmployee() {
    super();
  }

  public PartTimeEmployee(String name, BigDecimal hourlyWage) {
    super(name);
    this.hourlyWage = hourlyWage;
  }

  public BigDecimal getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(BigDecimal hourlyWage) {
    this.hourlyWage = hourlyWage;
  }

  @Override
  public String toString() {
    return String.format("id:%d, name:%s, hourly-wage:%s\n", id, name, hourlyWage);
  }

}
