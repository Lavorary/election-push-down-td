package main;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ElectionResult {
    private String candidateName;
    private long validVoteCount;
}
