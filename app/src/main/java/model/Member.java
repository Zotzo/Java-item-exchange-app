package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Member class.
 */

public class Member {
  private String name;
  private int credits;
  private String email;
  private int phoneNumber;
  private int creationDay;
  private String id;
  
  private List<Item> items = new ArrayList<>();
  
  /**
  * constructors for member.
  */
  
  public Member(String name, String email, String id, int phone, int creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phone);
    setCredits(0);
    setUniqueId(id);
    setCreationDate(creationDay);
  }

  /**
   * Constructor for hardcoded member.
   */
  
  public Member(String name, String email, String id, int phone, int credits, int creationDay) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phone);
    setCredits(credits);
    setUniqueId(id);
    setCreationDate(creationDay);
  }
  
  /**
  * creation date setter.
  */
  
  void setCreationDate(int creationDate) {
    this.creationDay = creationDate;
  }
  
  /**
  * creation date getter.
  */
  
  public int getCreationDate() {
    return creationDay;
  }
  
  /**
  * sets credits for user.
  */
  
  void setCredits(int credits) {
    this.credits = credits;
  }
  
  /**
  * credits getter.
  */
  
  public int getCredits() {
    return credits;
  }
  
  /**
  * method to add credits.
  */
  
  void addCredits(int quantity) {
    this.credits = credits + quantity;
  }   
  
  /**
  * method to subtract credits.
  */
  
  void restCredits(int quantity) {
    this.credits = credits - quantity;
  }
  
  /**
  * method to set unique ID.
  */
  
  void setUniqueId(String id) {
    this.id = id;
  }
  
  /*
  * method to get unique ID.
  */
  
  public String getUniquedId() {
    return id;
  }
  
  /**
  * method to set phone number.
  */
  
  void setPhoneNumber(int phone) {
    this.phoneNumber = phone;
  }
  
  /**
  * method to get phone number.
  */
  
  public int getPhoneNumber() {
    return phoneNumber;
  }
  
  /**
  * method to se email.
  */
  
  void setEmail(String email) {
    this.email = email;
  }
  
  /**
  * method to get email.
  */
  
  public String getEmail() {
    return email;
  }
  
  /**
  * method to set name.
  */
  
  void setName(String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("the provided cannot be null or empty, provide another one");
    } else {
      this.name = name;
    }
  }
  
  /**
  * method to get name.
  */
  
  public String getName() {
    return name;
  }
  
  /**
  * method to get all items.
  */
  
  public ArrayList<Item> getAllItems() {
    return new ArrayList<>(items);
  }
  
  /**
  * method to add credits when listing item.
  */
  
  void addOwnedItem(Item item) {
    setCredits(credits + 100);
    this.items.add(item);
  }
  
  /**
  * method to remove item from owner.
  */
  
  void removeOwnedItem(Item item) {
    items.remove(item);
  }
}
