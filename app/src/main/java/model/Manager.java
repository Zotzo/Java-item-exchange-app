package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manager class.
 */

public class Manager {
  private Timer day = new Timer();
  private ArrayList<Member> members = new ArrayList<>();

  private int idLength = 6;
  private String alpha = "ABCDEFGHIJKLMNOPQRSTUVXWYZ0123456789";

  /**
   * Manager constructor.
   */

  public Manager() {
    memberSetUp();
  }

  /**
   * Method to get members.
   */

  public ArrayList<Member> getMembers() {
    ArrayList<Member> copies = new ArrayList<>();
    for (Member member : members) {
      copies.add(member);
    }
    return copies;
  }

  /**
   * Method to get items.
   */

  public ArrayList<Item> getItems() {
    ArrayList<Item> copies = new ArrayList<>();
    for (Member member : members) {
      copies.addAll(member.getAllItems());
    }
    return copies;
  }

  /**
   * Method to get day.
   */

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "needed so that manager can check contract expiration.")
  public Timer getDay() {
    return day;
  }

  /**
   * Method to generate id.
   */

  private String generateid() {
    StringBuilder idBuilder = new StringBuilder(); // the hammer of the id
    Random randomize = new Random(); // the nails we use with the hammer to create the id
    for (int i = 0; i < idLength; i++) {
      int inx = randomize.nextInt(alpha.length()); // it points to a random character in our alpha characters
      char randomChara = alpha.charAt(inx); // we fetch the character
      idBuilder.append(randomChara); // we append it
    }
    return idBuilder.toString(); // we make the builder a tostring so idbuilder can actually be used in the code
  }

  /**
   * Method to check ids.
   */

  private boolean checkId(String id) {
    for (Member mem : members) {
      if (mem.getUniquedId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method to check unique email.
   */

  private boolean checkEmail(String email) {
    for (Member mem : members) {
      if (mem.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method to check phones.
   */

  private boolean checkPhone(int phone) {
    for (Member mem : members) {
      if (mem.getPhoneNumber() == phone) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method to check current day.
   */

  private boolean checkCurrentDay(int days) {
    if (days > day.getDays()) {
      return true;
    }
    return false;
  }

  /**
   * Method to check wallet.
   */

  private boolean checkWallet(Member loaner, Item item, int day) {
    if (loaner.getCredits() < (item.getCostPerDay() * day)) {
      return false;
    }
    return true;
  }

  /**
   * Method to check if item available.
   */

  private boolean checkIfItemAvailable(Item item, int startDay, int endDay) {
    for (Contract contract : item.getContracts()) {
      if (startDay >= contract.getStartDays() && startDay <= contract.getEndDays()
          || (endDay >= contract.getStartDays() && endDay <= contract.getEndDays())
          || startDay < contract.getStartDays() && endDay > contract.getEndDays()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method to set up hardcoded members.
   */

  private void memberSetUp() {
    // Initialize members with sample data
    String[] names = { "Goku", "Haseo", "Kite" };
    String[] emails = { "Saiyanpower@gmail.com", "Skeith@gmail.com", "Theworld@gmail.com" };
    int[] credits = { 500, 100, 100 };
    int[] phoneNumbers = { 0726672243, 0724347562, 0724234354 };

    for (int i = 0; i < names.length; i++) {
      setHardCoded(names[i], emails[i], phoneNumbers[i], credits[i]);
    }

    registerListingItem(searchForMember(emails[0]), "sonic frontiers", "game", "sonic's latest game", 50);
    registerListingItem(searchForMember(emails[0]), "scythe", "tool", "a tool for gardening or killing", 10);

    registerListingItem(searchForMember(emails[1]), "toy car", "toy", "racing car used to play", 10);
    registerListingItem(searchForMember(emails[1]), "toyota AE86 trueno", "vehicle", "the best car for drift", 100);

    registerListingItem(searchForMember(emails[2]), "soccer ball", "sport", "the best ball to play soccer", 20);
    registerListingItem(searchForMember(emails[2]), "toy gun", "toy", "shoot your friends and have fun", 40);

    createContract(searchForMember(emails[2]), getItemNames("scythe"), 5, 7);
  }

  private Item getItemNames(String items) {
    for (Member mem : members) {
      for (Item itemss : mem.getAllItems()) {
        if (itemss.getName().equals(items)) {
          return itemss;
        }
      }
    }
    return null;
  }

  /**
   * Method to create member.
   */

  public boolean setUp(String name, String email, int phoneNumber) {
    String id;
    if (checkEmail(email) || checkPhone(phoneNumber)) {
      return false;
    }

    do {
      id = generateid();
    } while (checkId(id));

    Member me = new Member(name, email, id, phoneNumber, day.getDays());
    members.add(me);
    return true;
  }

  /**
   * Method to fully setup hardcided member.
   */

  public boolean setHardCoded(String name, String email, int phoneNumber, int credits) {
    String id;
    if (checkEmail(email) || checkPhone(phoneNumber)) {
      return false;
    }

    do {
      id = generateid();
    } while (checkId(id));

    Member me = new Member(name, email, id, phoneNumber, credits, day.getDays());
    members.add(me);
    return true;
  }

  /**
   * Method to remove members.
   */

  public void removeMember(Member member) {
    members.remove(member);
  }

  /**
   * Method to update names.
   */

  public void updateName(Member member, String name) {
    member.setName(name);
  }

  /**
   * Method to update email.
   */

  public void updateEmail(Member member, String newEmail) {
    if (checkEmail(newEmail)) {
      throw new IllegalArgumentException("email already exists!");
    }
    member.setEmail(newEmail);
  }

  /**
   * Method to update phone.
   */

  public void updatePhone(Member member, int newPhone) {
    if (checkPhone(newPhone)) {
      throw new IllegalArgumentException("phone is already in use!");
    }
    member.setPhoneNumber(newPhone);
  }

  /**
   * Method to check if owner contains item.
   */

  private boolean containsItem(Member owner, String name) {
    for (Item item : owner.getAllItems()) {
      if (item.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * method to search for members.
   */

  public Member searchForMember(String email) {
    for (Member mem : members) {
      if (mem.getEmail().equals(email)) {
        return mem;
      }
    }
    return null;
  }

  /**
   * Method to create listing.
   */

  public boolean registerListingItem(Member owner, String name, String category, String description, Integer cost) {
    Member original = searchForMember(owner.getEmail());

    if (original == null) {
      return false;
    }

    if (containsItem(original, name)) {
      return false;
    }
    if (cost == null) {
      return false;
    }
    new Item(original, name, category, description, cost, day.getDays());
    return true;
  }

  /**
   * Method to delete listing.
   */

  public void deleteListingItem(Item item) {
    for (Member owner : members) {
      if (owner.getAllItems().contains(item)) {
        owner.removeOwnedItem(item);
      }
    }
    for (Contract contract : item.getContracts()) {
      day.removeObserver(contract);
    }
  }

  /**
   * Method to change item name.
   */

  public void changeItemName(Item item, String newName) {
    if (newName.equals("")) {
      throw new IllegalArgumentException("name is not valid!");
    }
    item.setName(newName);
  }

  /**
   * Method to change item category.
   */

  public void changeItemCategory(Item item, String category) {
    if (category.equals("")) {
      throw new IllegalArgumentException("category is not valid!");
    }
    item.setCategory(category);
  }

  /**
   * Method to change item description.
   */

  public void changeItemDescription(Item item, String description) {
    if (description.equals("")) {
      throw new IllegalArgumentException("description is not valid!");
    }
    item.setDescription(description);
  }

  /**
   * Method to change the price of item.
   */

  public void changeItemPrice(Item item, Integer price) {
    if (price.equals(0)) {
      throw new IllegalArgumentException("price is not valid!");
    }
    item.setCostPerDay(price);
  }

  /**
   * method to create contract.
   */

  public boolean createContract(Member owner, Item item, int startDay, int endDay) {
    if (owner == null || item == null || !checkCurrentDay(startDay) || !checkCurrentDay(endDay)
        || !checkIfItemAvailable(item, startDay, endDay) || !checkWallet(owner, item, (endDay - endDay + 1))) {
      return false;
    }
    day.addObserver(new Contract(owner, item, startDay, endDay));
    return true;
  }

  /**
   * Method to advance day.
   */

  public void advanceDay() {
    day.incrementDays();
  }
}
