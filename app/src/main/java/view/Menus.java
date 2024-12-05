package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;
import model.Timer;

/**
 * Menus for the system.
 */

public class Menus {
  private final Scanner scan = new Scanner(System.in, "UTF-8");

  /**
   * Main menu display.
   */

  public String displayMenu(Timer day) {
    String[] choice = { "view member menu", "view items menu", "view contracts menu", "advance day", "exit" };
    System.out.println("welcome to stuff lending system \n");
    System.out.println("time today is: " + day.getDays() + "\n");

    for (int i = 0; i < choice.length; i++) {
      System.out.println((i + 1) + " " + choice[i]);
    }

    int option = scan.nextInt();
    while (option < 1 || option > choice.length) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      option = newchoice;
    }

    String view;
    switch (choice[option - 1]) {
      case "view member menu":
        view = "Member";
        return view;
      case "view items menu":
        view = "Item";
        return view;
      case "view contracts menu":
        view = "Contract";
        return view;
      case "advance day":
        view = "Time";
        return view;
      case "exit":
        view = "exit";
        return view;
      default:
        return "exit";
    }
  }

  /**
   * Member menu display.
   */

  public String menuMember() {
    System.out.println("welcome to member page \n");
    String[] choice = { "register", "update information", "delete member", "view information", "view full information",
        "exit" };

    for (int i = 0; i < choice.length; i++) {
      System.out.println((i + 1) + " " + choice[i]);
    }

    int option = scan.nextInt();
    while (option < 1 || option > choice.length) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      option = newchoice;
    }

    String view;
    switch (choice[option - 1]) {
      case "register":
        view = "register";
        return view;
      case "update information":
        view = "updateInfo";
        return view;
      case "delete member":
        view = "deleteMem";
        return view;
      case "view information":
        view = "memberInfoSimple";
        return view;
      case "view full information":
        view = "memberInfoVerbose";
        return view;
      case "exit":
        view = "exit";
        return view;
      default:
        return "exit";
    }
  }

  /**
   * Item menu display.
   */

  public String itemListingsMenu() {
    String[] choice = { "list an item", "unlist items", "update listing", "view all listings", "exit" };
    System.out.println("welcome to item listing menu \n");

    for (int i = 0; i < choice.length; i++) {
      System.out.println((i + 1) + " " + choice[i]);
    }

    int option = scan.nextInt();
    while (option < 1 || option > choice.length) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      option = newchoice;
    }

    String view;
    switch (choice[option - 1]) {
      case "list an item":
        view = "createItem";
        return view;
      case "unlist items":
        view = "removeItem";
        return view;
      case "update listing":
        view = "updateItem";
        return view;
      case "view all listings":
        view = "viewAllItem";
        return view;
      case "exit":
        view = "exit";
        return view;
      default:
        return "exit";
    }
  }

  /**
   * Contract menu.
   */

  public String contractMenu() {
    System.out.println("welcome to the contracts menu \n");
    String[] choice = { "create contract", "exit" };

    for (int i = 0; i < choice.length; i++) {
      System.out.println((i + 1) + " " + choice[i]);
    }

    int option = scan.nextInt();
    while (option < 1 || option > choice.length) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      option = newchoice;
    }

    String view;
    switch (choice[option - 1]) {
      case "create contract":
        view = "createContract";
        return view;
      case "exit":
        view = "exit";
        return view;
      default:
        return "exit";
    }
  }

  /**
   * Method that connects to controller for listing items.
   */

  public void listItems(ArrayList<Item> items) {
    for (int i = 0; i < items.size(); i++) {
      System.out.println(" index: " + (i + 1) + " name: " + items.get(i).getName()
          + " short description: " + items.get(i).getDescription() + " category: " + items.get(i).getCategory()
          + " cost per day: " + items.get(i).getCostPerDay() + " Day created: " + items.get(i).getcreationDate()
          + " owner: " + items.get(i).getOwner().getName() + "\n");
    }
  }

  /**
   * Display all members.
   */

  public void listMembers(ArrayList<Member> members) {
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + " name: " + members.get(i).getName()
          + " email: " + members.get(i).getEmail() + " phone number: "
          + members.get(i).getPhoneNumber() + " id: " + members.get(i).getUniquedId()
          + " sign up date: " + members.get(i).getCreationDate()
          + " credits: " + members.get(i).getCredits() + "\n");
      for (Item item : members.get(i).getAllItems()) {
        viewContract(item.getContracts());
      }
    }
  }

  /**
   * Method to list simple member info.
   */

  public void listSimple(ArrayList<Member> members) {
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + " name: " + members.get(i).getName()
          + " email: " + members.get(i).getEmail() + " phone number: "
          + members.get(i).getPhoneNumber() + "\n");
    }
  }

  /**
   * View contract info.
   */

  public void viewContract(ArrayList<Contract> contracts) {
    for (int i = 0; i < contracts.size(); i++) {
      System.out.println(" Contract: " + " owner: " + contracts.get(i).getMember().getName() + " item: "
          + contracts.get(i).getItem().getName() + " item owner: " + contracts.get(i).getItem().getOwner().getName()
          + " start day: " + contracts.get(i).getStartDays() + " end day: " + contracts.get(i).getEndDays() + "\n");
    }
  }

  /**
   * Error message.
   */

  public void errorMsg() {
    System.out.println("wrong input, try again buddy");
  }

  /**
   * Member created correctly message.
   */

  public void memberSuccessMsg() {
    System.out.println("member created succesfully!");
  }

  /**
   * Failed member registration message.
   */

  public void memberNoSuccessMsg() {
    System.out.println("failed member registration!");
  }

  /**
   * Member deleted message.
   */

  public void memberDeletedMsg() {
    System.out.println("member deleted!");
  }

  /**
   * Member failed deletion message.
   */

  public void memberDeletedFailMsg() {
    System.out.println("member not deleted!");
  }

  /**
   * Name updated message.
   */

  public void nameUpdatingMsg() {
    System.out.println("the name of the member was updated succesfully!");
  }

  /**
   * Updated email message.
   */

  public void updateEmailMsg() {
    System.out.println("the email was updated succesfully!");
  }

  /**
   * Phone updated message.
   */

  public void phoneEmailMsg() {
    System.out.println("the phone number was updated succesfully!");
  }

  /**
   * Member not found message.
   */

  public void memberNoFoundMsg() {
    System.out.println("member does not exist!");
  }

  /**
   * Member info updated message.
   */

  public void infoUpdateMsg() {
    System.out.println("member information was updated succesfully!");
  }

  /**
   * Member info update failed message.
   */

  public void infoFail() {
    System.out.println("member info was not updated!");
  }

  /**
   * Item listing success message.
   */

  public void itemListSuccMsg() {
    System.out.println("item listed!");
  }

  /**
   * item listing fail message.
   */

  public void itemListFail() {
    System.out.println("item failed to list!");
  }

  /**
   * Item updated success message.
   */

  public void itemUpMsg() {
    System.out.println("item listing updated succesfully!");
  }

  public void itemIncorrectMsg() {
    System.out.println("incorrect index for item!");
  }

  public void itemIndexMsg() {
    System.out.println("enter index of item to update!: ");
  }

  public void contractFailMsg() {
    System.out.println("contract failed due to either lack of funds or conflicting time!");
  }

  public String memberName() {
    System.out.println("provide the name of the member: ");
    return getInputTwo();
  }

  public String memberEmail() {
    System.out.println("provide the email of the member: ");
    return getInputTwo();
  }

  public int memberPhoneNumber() {
    System.out.println("provide the phone number of the member: ");
    return getInput();
  }

  public Member loaneerAsk(ArrayList<Member> members) {
    System.out.println("pick the name of the loanee: ");
    return memberThis(members);
  }

  public int startDayAsk() {
    System.out.println("provide the start day: ");
    return getInput();
  }

  public int endDayAsk() {
    System.out.println("provide the end day: ");
    return getInput();
  }

  public void contractSuccMsg() {
    System.out.println("contract created succesfully!");
  }

  public String itemName() {
    System.out.println("proive the name of the item: ");
    return getInputTwo();
  }

  public String itemDescription() {
    System.out.println("provide the description of the item: ");
    return getInputTwo();
  }

  /**
   * Message to request what category to add to item.
   */

  public String itemCategory() {
    System.out.println("provide the category of the item: ");
    String[] choice = { "toy", "electronics", "tool", "vehicle", "game", "other" };

    for (int i = 0; i < choice.length; i++) {
      System.out.println((i + 1) + " - " + choice[i]);
    }

    int option = scan.nextInt();
    while (option < 1 || option > choice.length) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      option = newchoice;
    }

    String categorys;
    switch (choice[option - 1]) {
      case "toy":
        categorys = "toy";
        return categorys;
      case "electronics":
        categorys = "electronics";
        return categorys;
      case "tool":
        categorys = "tool";
        return categorys;
      case "vehicle":
        categorys = "vehicle";
        return categorys;
      case "game":
        categorys = "game";
        return categorys;
      case "other":
        categorys = "other";
        return categorys;
      default:
        return null;
    }
  }

  public int itemDailyCost() {
    System.out.println("enter the daily cost of the item: ");
    return getInput();
  }

  public Member memberGets(ArrayList<Member> members) {
    System.out.println("provide member by number: ");
    return memberThis(members);
  }

  private Member memberThis(ArrayList<Member> members) {
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + "  " + members.get(i).getName());
    }

    int choice = scan.nextInt();
    while (choice < 1 || choice > members.size()) {
      System.out.println("incorrect input");
      int newChoice = scan.nextInt();
      choice = newChoice;
    }
    return members.get(choice - 1);
  }

  /**
   * Message to ask what item to use.
   */

  public Item itemAsk(ArrayList<Item> items) {
    System.out.println("select item: ");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + "  " + items.get(i).getName());
    }

    int choice = scan.nextInt();
    while (choice < 1 || choice > items.size()) {
      System.out.println("incorrect try again");
      int newchoice = scan.nextInt();
      choice = newchoice;
    }
    return items.get(choice - 1);
  }

  /**
   * Method to ask for int input.
   */

  public Integer getInput() {
    if (scan.hasNextInt()) {
      Integer input = scan.nextInt();
      scan.nextLine();
      return input;
    } else {
      scan.nextLine();
      return null;
    }
  }

  /**
   * Method to ask for string input.
   */

  public String getInputTwo() {
    if (scan.hasNextLine()) {
      String input = scan.next();
      scan.nextLine();
      return input;
    } else {
      scan.next();
      return null;
    }
  }
}
