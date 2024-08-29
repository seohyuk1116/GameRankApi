package com.example.GameRankApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/game_data";
        String user = "root";
        String password = "1234";

        try {
            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Database connected!");

                // 쿼리문 작성
                String query =
                        "SELECT " +
                                "    ug.num AS userNum, " +
                                "    ug.uid, " +
                                "    ug.userName AS userName, " +
                                "    ug.userPassword AS userPassword, " +
                                "    ug.userComment AS userComment, " +
                                "    us.memory_game, " +
                                "    us.snake_game, " +
                                "    us.jump_game, " +
                                "    us.bird_game " +
                                "FROM " +
                                "    userGameData ug " +
                                "LEFT JOIN " +
                                "    userScore us " +
                                "ON " +
                                "    ug.uid = us.uid;";

                // Statement 객체 생성
                Statement statement = connection.createStatement();

                // 쿼리 실행
                ResultSet resultSet = statement.executeQuery(query);

                // 결과 출력
                System.out.println(String.format("%-10s %-12s %-16s %-12s %-32s %-16s %-16s %-16s %-16s",
                        "UserNum", "UID", "UserName", "Password", "Comment",
                        "Memory Game", "Snake Game", "Jump Game", "Bird Game"));

                // 결과 처리
                while (resultSet.next()) {
                    int userNum = resultSet.getInt("userNum");
                    String uid = resultSet.getString("uid");
                    String userName = resultSet.getString("userName");
                    String userPassword = resultSet.getString("userPassword");
                    String userComment = resultSet.getString("userComment");
                    String memoryGame = resultSet.getString("memory_game");
                    String snakeGame = resultSet.getString("snake_game");
                    String jumpGame = resultSet.getString("jump_game");
                    String birdGame = resultSet.getString("bird_game");

                    System.out.println(String.format("%-10s %-12s %-16s %-12s %-32s %-16s %-16s %-16s %-16s",
                            userNum, uid, userName, userPassword, userComment,
                            memoryGame, snakeGame, jumpGame, birdGame));
                }

                // 자원 해제
                resultSet.close();
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
