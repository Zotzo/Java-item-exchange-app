package model;

import java.util.ArrayList;

/**
 * Days class.
 */

public class Timer {
  private int days;
  private ArrayList<Observers> observers = new ArrayList<>();
  
  /**
  * Constructor.
  */
  
  public Timer() {
    setDays(0);
  }
  
  /**
  * Setters and getters for days. 
  */
  
  private void setDays(int time) {
    this.days = time;
  }
  
  public int getDays() {
    return days;
  }
  
  /**
  * Method to avance time.
  */
  
  public void incrementDays() {
    setDays(getDays() + 1);
    notifyObservers();
  }
  
  /**
  * Method to add observer and notify them.
  */
  
  public void addObserver(Observers observer) {
    observers.add(observer);
  }
  
  public void removeObserver(Observers observer) {
    observers.remove(observer);
  }
  
  private void notifyObservers() {
    for (Observers observer : observers) {
      observer.updateDay(getDays());
    }
  }
}
