package main;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Vote {
    private int id;
    private int candidateId;
    private int voterId;
    private voteEnum vote_type;
}
