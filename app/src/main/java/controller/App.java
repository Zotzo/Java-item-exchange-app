package controller;


import view.Menus;

/**
* Responsible for staring the application.
*/
public class App {
  /**
  * Application starting point.
  *
  * @param args command line arguments.
  */
  public static void main(String[] args) {
    Menus ui = new Menus();
    
    Controllers controller = new Controllers(ui);
    controller.displayMenuHandler();
  }
}
