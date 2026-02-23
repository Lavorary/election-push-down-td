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
                voteTypeCount.setVoteCount(resultSet.getInt("total_votes"));
                voteTypeCounts.add(voteTypeCount);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return voteTypeCounts;
    }
}
