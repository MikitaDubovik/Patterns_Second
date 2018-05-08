package sample.Model;

public class Task {
    String projectName;
    String aim;
    String aimId;
    String planedHours;
    String people;
    String factHours;
    String hoursLeft;
    String date;

    public Task() {
    }

    public Task(String projectName, String aim, String aimId,
                String planedHours, String people, String factHours,
                String hoursLeft, String date) {
        this.projectName = projectName;
        this.aim = aim;
        this.aimId = aimId;
        this.planedHours = planedHours;
        this.people = people;
        this.factHours = factHours;
        this.hoursLeft = hoursLeft;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getAimId() {
        return aimId;
    }

    public void setAimId(String aimId) {
        this.aimId = aimId;
    }

    public String getPlanedHours() {
        return planedHours;
    }

    public void setPlanedHours(String planedHours) {
        this.planedHours = planedHours;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getFactHours() {
        return factHours;
    }

    public void setFactHours(String factHours) {
        this.factHours = factHours;
    }

    public String getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(String hoursLeft) {
        this.hoursLeft = hoursLeft;
    }
}
