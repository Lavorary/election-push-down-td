package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    long countAllVotes() {
        DBConnection db = new DBConnection();
        long countVotes = 0;
        try (Connection connection = db.getConnection()) {
            String query = """
                        select count(id) as total_votes
                        from vote;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countVotes = resultSet.getLong("total_votes");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countVotes;
    };

    List<VoteTypeCount> countVotesByTypes() {
        DBConnection db = new DBConnection();
        List<VoteTypeCount> voteTypeCounts = new ArrayList<>();
        try(Connection connection = db.getConnection()) {
            String query = """
                    select vote_type, count(vote_type) as  total_votes
                    from vote
                    group by vote_type
                    order by vote_type;

            """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VoteTypeCount voteTypeCount = new VoteTypeCount();
                voteTypeCount.setVoteType(voteEnum.valueOf(resultSet.getString("vote_type")));
                voteTypeCount.setCount(resultSet.getInt("total_votes"));
                voteTypeCounts.add(voteTypeCount);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return voteTypeCounts;
    }

    List<CandidateVoteCount> countValidVotesByCandidate(){
        DBConnection db = new DBConnection();
        List<CandidateVoteCount> candidateVoteCounts = new ArrayList<>();
        try(Connection connection = db.getConnection()) {
            String query = """
                    select c.name as candidate_name, count(case when v.vote_type = 'VALID' then true end) as valid_vote
                    from candidate c
                    join vote v on c.id = v.candidate_id
                    group by  c.name
                    order by c.name;
            """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CandidateVoteCount candidateVoteCount = new CandidateVoteCount();
                candidateVoteCount.setCandidateName(resultSet.getString("candidate_name"));
                candidateVoteCount.setValidVoteCount(resultSet.getLong("valid_vote"));
                candidateVoteCounts.add(candidateVoteCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidateVoteCounts;
    }

     VoteSummary computeVoteSummary() {
         DBConnection db = new DBConnection();
         VoteSummary voteSummary;
         try (Connection connection = db.getConnection()) {
             String query = """
                    select count(case when v.vote_type = 'VALID' then 1 end) as valid_count,
                           count(case when v.vote_type = 'BLANK' then 1 end) as blank_count,
                           count(case when v.vote_type = 'NULL' then 1 end) as null_count
                    from vote v;
            """;
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
             voteSummary = new VoteSummary();
             while (resultSet.next()) {
                 voteSummary.setValidCount(resultSet.getLong("valid_count"));
                 voteSummary.setBlankCount(resultSet.getLong("blank_count"));
                 voteSummary.setNullCount(resultSet.getLong("null_count"));
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return voteSummary;
     }

}
