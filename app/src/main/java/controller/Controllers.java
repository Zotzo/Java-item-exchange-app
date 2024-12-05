package controller;

import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Manager;
import model.Member;
import view.Menus;

/**
 * Controllers that work with the view.
 */

public class Controllers {
  private Manager manager = new Manager();
  private Menus ui;

  /**
   * Constructor.
   */
  
  public Controllers(Menus ui) {
    this. ui = ui;
    this.manager = new Manager();
  }
  
  /**
   * Controller for main menu.
   */

  public void displayMenuHandler() {
    boolean begin = true;
    
    while (begin) {
      String choice = ui.displayMenu(manager.getDay());
      
      switch (choice) {
        case "Member":
          memberMenuHandler();
          continue;
        case "Item":
          itemMenuHandler();
          continue;
        case "Contract":
          contractMenuHandler();
          continue;
        case "Time":
          manager.advanceDay();
          continue;
        case "exit":
          begin = false;
          break;
        default:
          ui.errorMsg();
          continue;
      }
    }
  }
  
  private void memberMenuHandler() {
    boolean begin = true;
    while (begin) {
      String choice = ui.menuMember();
      switch (choice) {
        case "register":
          createMember();
          continue;
        case "deleteMem":
          deleteMember();
          continue;
        case "updateInfo":
          updateInfo();
          continue;
        case "memberInfoSimple":
          memberInfoSimple();
          continue;
        case "memberInfoVerbose":
          memberInfoVerbose();
          continue;
        case "exit":
          begin = false;
          break;
        default:
          ui.errorMsg();
          continue;
      }
    }
  }
  
  private void itemMenuHandler() {
    boolean begin = true;
    while (begin) {
      String choice = ui.itemListingsMenu();
      switch (choice) {
        case "createItem":
          createListing();
          continue;
        case "removeItem":
          removeListing();
          continue;
        case "updateItem":
          updateItem();
          continue;
        case "viewAllItem":
          viewAllItems();
          continue;
        case "exit":
          begin = false;
          break;
        default:
          ui.errorMsg();
          continue;
      }
    }
  }
  
  private void contractMenuHandler() {
    boolean begin = true;
    while (begin) {
      String choice = ui.contractMenu();
      switch (choice) {
        case "createContract":
          createTheContract();
          continue;
        case "exit":
          begin = false;
          break;
        default:
          ui.errorMsg();
          continue;
      }
    }
  }
  
  private void viewAllItems() {
    ui.listItems(getEveryItem());
  }
  
  private void updateItem() {
    ui.listItems(getEveryItem());
    ui.itemIndexMsg();
    int indexx = ui.getInput();
    
    ArrayList<Item> items = manager.getItems();
    
    if (indexx >= 1 && indexx <= items.size()) {
      Item update = items.get(indexx - 1);
      
      String name = ui.itemName();
      if (!name.isEmpty()) {
        itemNameUpdate(update, name);
      }
      
      String description = ui.itemDescription();
      if (!description.isEmpty()) {
        updateDescription(update, description);
      }
      
      String category = ui.itemCategory();
      if (!category.isEmpty()) {
        updateCategory(update, category);
      }
      
      int price = ui.itemDailyCost();
      if (price != 0) {
        updateCost(update, price);
      }
      ui.itemUpMsg();
    } else {
      ui.itemIncorrectMsg();
    }
  }
  
  private void createListing() {
    Member owner = ui.memberGets(getEveryMember());
    
    String itemName = ui.itemName();
    
    String category = ui.itemCategory();
    
    String description = ui.itemDescription();
    
    int cost = ui.itemDailyCost();
    
    boolean finished = manager.registerListingItem(owner, itemName, category, description, cost);
    
    if (finished) {
      ui.itemListSuccMsg();
    } else {
      ui.itemListFail();
    }
  }
  
  private void createMember() {
    String name = ui.memberName();
    
    String email = ui.memberEmail();
    
    int phone = ui.memberPhoneNumber();
    
    boolean finished = manager.setUp(name, email, phone);
    
    if (finished) {
      ui.memberSuccessMsg();
    } else {
      ui.memberNoSuccessMsg();
    }
  }
  
  private void removeListing() {
    Item item = ui.itemAsk(getEveryItem());
    if (item != null) {
      manager.deleteListingItem(item);
    }
  }
  
  private void deleteMember() {
    ui.listMembers(getEveryMember());
    String email = ui.memberEmail();
    
    Member removeMem = manager.searchForMember(email);
    if (removeMem != null) {
      manager.removeMember(removeMem);
      ui.memberDeletedMsg();
    } else {
      ui.memberDeletedFailMsg();
    }
  }
  
  private void updateInfo() {
    ui.listSimple(getEveryMember());
    String email = ui.memberEmail();
    
    Member members = manager.searchForMember(email);
    
    if (members != null) {
      boolean continues = true;
      
      while (continues) {
        System.out.println("select option: ");
        System.out.println("1-update name: ");
        System.out.println("2-update email: ");
        System.out.println("3-update phone number: ");
        System.out.println("4-exit ");
        
        int choice = ui.getInput();
        
        switch (choice) {
          case 1:
            String newName = ui.memberName();
            memberNameUpdate(members, newName);
            ui.nameUpdatingMsg();
            break;
          case 2:
            String newemail = ui.memberEmail();
            memberEmailUpdate(members, newemail);
            ui.updateEmailMsg();
            break;
          case 3:
            int newPhone = ui.memberPhoneNumber();
            memberPhoneUpdate(members, newPhone);
            ui.phoneEmailMsg();
            break;
          case 4:
            continues = false;
            break;
          default:
            ui.errorMsg();
        }
      }
      
      ui.infoUpdateMsg();
    } else {
      ui.infoFail();
    }
  }
  
  private void createTheContract() {
    Member loanee = ui.loaneerAsk(getEveryMember());
    Item item = ui.itemAsk(getEveryItem());
    
    int beginDate = ui.startDayAsk();
    int endDate = ui.endDayAsk();
    
    boolean finished = manager.createContract(loanee, item, beginDate, endDate);
    
    if (finished) {
      ui.contractSuccMsg();
    } else {
      ui.contractFailMsg();
    }
  }
  
  public ArrayList<Item> getEveryItem() {
    return manager.getItems();
  }
  
  public ArrayList<Member> getEveryMember() {
    return manager.getMembers();
  }
  
  public ArrayList<Item> getMemberItem(Member member) {
    return member.getAllItems();
  }
  
  public ArrayList<Contract> getEveryContract(Item item) {
    return item.getContracts();
  }
  
  public void itemNameUpdate(Item item, String name) {
    manager.changeItemName(item, name);
  }
  
  public void updateCategory(Item item, String category) {
    manager.changeItemCategory(item, category);
  }
  
  public void updateDescription(Item item, String description) {
    manager.changeItemDescription(item, description);
  }
  
  public void updateCost(Item item, Integer cost) {
    manager.changeItemPrice(item, cost);
  }
  
  public void createTheListing(Member owner, String name, String category, String description, int cost) {
    manager.registerListingItem(owner, name, category, description, null);
  }
  
  public void memberNameUpdate(Member member, String name) {
    manager.updateName(member, name);
  }
  
  public void memberPhoneUpdate(Member member, int phone) {
    manager.updatePhone(member, phone);
  }
  
  public void memberEmailUpdate(Member member, String email) {
    manager.updateEmail(member, email);
  }
  
  private void memberInfoSimple() {
    ui.listSimple(getEveryMember());
  }
  
  private void memberInfoVerbose() {
    ui.listMembers(getEveryMember());
  }
}