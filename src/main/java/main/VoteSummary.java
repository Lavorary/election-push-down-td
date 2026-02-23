package main;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoteSummary {
    private long validCount;
    private long blankCount;
    private long nullCount;
}
