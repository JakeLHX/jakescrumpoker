package uk.co.jake.scrumpoker.domain;

import java.util.Arrays;
import java.util.Date;

public class Ticket {
    private String Name;
    private String Owner;
    private String Type;
    private String Priority;
    private Date Deadline;
    private Integer StoryPoints;

    public Ticket(String n, String o, String t, String p) {
        Name = n;
        Owner = o;
        Type = t;
        Priority = p;

        if (validate(this)) {
            System.out.println("Ticket is valid");
        }
    }

    public Ticket(String n, String o, String t, String p, Date d) {
        Name = n;
        Owner = o;
        Type = t;
        Priority = p;
        Deadline = d;

        if (validate(this)) {
            System.out.println("Ticket is valid");
        }
    }

    private boolean validate(Ticket t) {
        try {
            if (t.getName() == null) throw new Exception("Check one is bad");
            if (t.getOwner() == null) throw new Exception("Check two is bad");
            if (!Arrays.asList("Bug","Feature","Task").contains(t.getType())) throw new Exception("Check three is bad");
            if (!Arrays.asList("Minor","Major","Critical").contains(t.getPriority())) throw new Exception("Check four is bad");
            if (Arrays.asList("Bug","Feature").contains(t.getType()) & t.getDeadline() == null) throw new Exception("Check five is bad");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }

    public String getName() {
        return Name;
    }
    public String getOwner() {
        return Owner;
    }
    public String getType() {
        return Type;
    }
    public String getPriority() {
        return Priority;
    }
    public Date getDeadline() {
        return Deadline;
    }
    public Integer getStoryPoints() {
        return StoryPoints;
    }

    public void setStoryPoints(Integer p) {
        this.StoryPoints = p;
    }
}
