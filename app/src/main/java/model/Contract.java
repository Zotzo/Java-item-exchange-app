package model;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Contract class.
 */

public class Contract implements Observers {
  private Member member;
  private Item item;
  private int startD;
  private int endD;
  private boolean inProgress;

  /**
   * Contructor for contract.
   */
  
  public Contract(Member member, Item item, int startDays, int endDays) {
    setMember(member);
    setItem(item);
    setStartDays(startDays);
    setendDays(endDays);
    item.setContracts(this);
    setProgress(false);
  }
  
  void setendDays(int endDays) {
    this.endD = endDays;
  }
  
  void setStartDays(int days) {
    this.startD = days;
  }
  
  public int getStartDays() {
    return startD;
  }
  
  public int getEndDays() {
    return endD;
  }
  
  void setItem(Item item) {
    this.item = item;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "item needed for contract.")
  public Item getItem() {
    return item;
  }
  
  void setMember(Member member) {
    this.member = member;
  }
  
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "member needed for owner.")
  public Member getMember() {
    return member;
  }
  
  void setProgress(boolean ongoing) {
    this.inProgress = ongoing;
  }
  
  public boolean getProgress() {
    return inProgress;
  }
  
  @Override
  public void updateDay(int day) {
    if (day == startD) {
      setProgress(true);
      item.setLoaner(member);
      int cost = item.getCostPerDay() * (endD - startD + 1);
      member.restCredits(cost);
      
    } else if (day == endD) {
      setProgress(false);
      item.setLoaner(null);
    }
  }  
}
