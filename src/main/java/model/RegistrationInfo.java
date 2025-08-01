package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegistrationInfo {
    private String fullName;
    private String email;
    private String gender;
    private String course;

    public RegistrationInfo(String fullName, String email, String gender, String course) {
        setFullName(fullName);
        setEmail(email);
        this.gender = gender;
        this.course = course;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isBlank()) {
            this.fullName = fullName;
        }
        else {
            throw new IllegalArgumentException("Name cannot be blank or empty.");
        }
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email cannot be blank or empty");
        }
    }
}
