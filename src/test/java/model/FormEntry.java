package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormEntry {

    private static final String DEFAULT_GENDER = "Мужской";
    private static final String EMPTY_CHOICE = "";
    private String email;
    private String name;
    private String gender;
    private String choice2;
    private boolean check11;
    private boolean check12;


    public FormEntry() {
        this.gender = DEFAULT_GENDER;
        this.choice2 = EMPTY_CHOICE;
    }

    public String getChoice1() {
        if (check11 && check12) {
            return "1.1, 1.2";
        }
        if (check11) {
            return "1.1";
        }
        if (check12) {
            return "1.2";
        }
        return "Нет";
    }
}