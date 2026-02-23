package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    }

    ;
}
