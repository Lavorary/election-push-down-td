package main;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class VoteTypeCount {
    private voteEnum voteType;
    private int count;
}
