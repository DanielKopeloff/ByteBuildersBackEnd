package net.revature.binarybuiltbyte.project2.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class ReviewDTO {

    String username ;
    int rating ;
    String comment;
    String picture;
    Timestamp dateCreated;
}
