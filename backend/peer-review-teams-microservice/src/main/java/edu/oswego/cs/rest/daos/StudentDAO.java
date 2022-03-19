package edu.oswego.cs.rest.daos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;


@Entity
@Data
public class StudentDAO {
    @Id public @JsonbProperty("studentID") @NonNull String studentID; // email
    /* for team*/
    public @JsonbProperty("TeamID") String teamID;
    public @JsonbProperty("TeamLead") boolean isLead;
    public @JsonbProperty("Finalized") boolean confirmFinalized;
    /* course */
    public @JsonbProperty("CourseID") @NonNull ArrayList<String> courses;

    public StudentDAO(
        @JsonbProperty("Email") String studentID
    ) {
        this.studentID = studentID;
        this.teamID = "";
        this.isLead = false;
        this.confirmFinalized = false;

    }

}
