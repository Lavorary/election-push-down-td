package main;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CandidateVoteCount {
    private String candidateName;
    private int ValidVoteCount;
}
