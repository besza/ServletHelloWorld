import java.time.LocalDate;
import java.util.List;

/**
 * Created by ui2016 on 6/28/2016.
 */
public class UserProfile {
    private String name;
    private LocalDate birthday;
    private int age;
    private double height;
    private List<String> emails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", height=" + height +
                ", emails=" + emails +
                '}';
    }
}
