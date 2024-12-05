package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

/**
* Item class.
*/

public class Item {
  private String category;
  private String name;
  private String description;
  private int creationDate;
  private int costPerDay;
  private Member owner;
  private Member loaner;
  private List<Contract> contracts = new ArrayList<>();

  /**
   * Item constructor.
   */
  
  public Item(Member owner, String name, String category, String description, int costPerDay, int creationDate) {
    setOwner(owner);
    owner.addOwnedItem(this);
    setName(name);
    setCategory(category);
    setDescription(description);
    setCostPerDay(costPerDay);
    setcreationdate(creationDate);
    setLoaner(null);
  }
  
  
  void setcreationdate(int creationDate) {
    this.creationDate = creationDate;
  }
  
  public int getcreationDate() {
    return creationDate;
  }
  
  
  public String getCategory() {
    return category;
  }
  
  void setCategory(String category) {
    this.category = category;
  }
  
  public String getName() {
    return name;
  }
  
  void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }
  
  void setDescription(String description) {
    this.description = description;
  }
  
  public int getCostPerDay() {
    return costPerDay;
  }
  
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }
  
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "owner of the item.")
  public Member getOwner() {
    return owner;
  }
  
  void setOwner(Member owner) {
    this.owner = owner;
  }
  
  void setContracts(Contract contract) {
    this.contracts.add(contract);
  }
  
  public ArrayList<Contract> getContracts() {
    return new ArrayList<>(contracts);
  }
  
  void setLoaner(Member loaner) {
    this.loaner = loaner;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "item needed for contract.")
  public Member getLoaner() {
    return loaner;
  }
  
}
