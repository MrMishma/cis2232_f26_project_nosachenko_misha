package ca.hccis.squash.entity;

import java.util.Scanner;

public class Match {

    private int id;
    private String matchDate;
    private String createdDateTime;
    private String player1Name;
    private String player2Name;

    private int player1Game1Score;
    private int player2Game1Score;

    private int player1Game2Score;
    private int player2Game2Score;

    private int player1Game3Score;
    private int player2Game3Score;

    private int player1Game4Score;
    private int player2Game4Score;

    private int player1Game5Score;
    private int player2Game5Score;

    private String winnerName;

    public Match() {
    }

    public Match(int id, String matchDate, String createdDateTime,
                 String player1Name, String player2Name,
                 int player1Game1Score, int player2Game1Score,
                 int player1Game2Score, int player2Game2Score,
                 int player1Game3Score, int player2Game3Score,
                 int player1Game4Score, int player2Game4Score,
                 int player1Game5Score, int player2Game5Score,
                 String winnerName) {

        this.id = id;
        this.matchDate = matchDate;
        this.createdDateTime = createdDateTime;
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        this.player1Game1Score = player1Game1Score;
        this.player2Game1Score = player2Game1Score;

        this.player1Game2Score = player1Game2Score;
        this.player2Game2Score = player2Game2Score;

        this.player1Game3Score = player1Game3Score;
        this.player2Game3Score = player2Game3Score;

        this.player1Game4Score = player1Game4Score;
        this.player2Game4Score = player2Game4Score;

        this.player1Game5Score = player1Game5Score;
        this.player2Game5Score = player2Game5Score;

        this.winnerName = winnerName;
    }


    public void getInformation() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Match Date: ");
        matchDate = scanner.nextLine();

        System.out.print("Created Date/Time: ");
        createdDateTime = scanner.nextLine();

        System.out.print("Player 1 Name: ");
        player1Name = scanner.nextLine();

        System.out.print("Player 2 Name: ");
        player2Name = scanner.nextLine();

        System.out.print("Player 1 Game 1 Score: ");
        player1Game1Score = scanner.nextInt();

        System.out.print("Player 2 Game 1 Score: ");
        player2Game1Score = scanner.nextInt();

        System.out.print("Player 1 Game 2 Score: ");
        player1Game2Score = scanner.nextInt();

        System.out.print("Player 2 Game 2 Score: ");
        player2Game2Score = scanner.nextInt();

        System.out.print("Player 1 Game 3 Score: ");
        player1Game3Score = scanner.nextInt();

        System.out.print("Player 2 Game 3 Score: ");
        player2Game3Score = scanner.nextInt();

        System.out.print("Player 1 Game 4 Score: ");
        player1Game4Score = scanner.nextInt();

        System.out.print("Player 2 Game 4 Score: ");
        player2Game4Score = scanner.nextInt();

        System.out.print("Player 1 Game 5 Score: ");
        player1Game5Score = scanner.nextInt();

        System.out.print("Player 2 Game 5 Score: ");
        player2Game5Score = scanner.nextInt();

        scanner.nextLine();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getPlayer1Game1Score() {
        return player1Game1Score;
    }

    public void setPlayer1Game1Score(int player1Game1Score) {
        this.player1Game1Score = player1Game1Score;
    }

    public int getPlayer2Game1Score() {
        return player2Game1Score;
    }

    public void setPlayer2Game1Score(int player2Game1Score) {
        this.player2Game1Score = player2Game1Score;
    }

    public int getPlayer1Game2Score() {
        return player1Game2Score;
    }

    public void setPlayer1Game2Score(int player1Game2Score) {
        this.player1Game2Score = player1Game2Score;
    }

    public int getPlayer2Game2Score() {
        return player2Game2Score;
    }

    public void setPlayer2Game2Score(int player2Game2Score) {
        this.player2Game2Score = player2Game2Score;
    }

    public int getPlayer1Game3Score() {
        return player1Game3Score;
    }

    public void setPlayer1Game3Score(int player1Game3Score) {
        this.player1Game3Score = player1Game3Score;
    }

    public int getPlayer2Game3Score() {
        return player2Game3Score;
    }

    public void setPlayer2Game3Score(int player2Game3Score) {
        this.player2Game3Score = player2Game3Score;
    }

    public int getPlayer1Game4Score() {
        return player1Game4Score;
    }

    public void setPlayer1Game4Score(int player1Game4Score) {
        this.player1Game4Score = player1Game4Score;
    }

    public int getPlayer2Game4Score() {
        return player2Game4Score;
    }

    public void setPlayer2Game4Score(int player2Game4Score) {
        this.player2Game4Score = player2Game4Score;
    }

    public int getPlayer1Game5Score() {
        return player1Game5Score;
    }

    public void setPlayer1Game5Score(int player1Game5Score) {
        this.player1Game5Score = player1Game5Score;
    }

    public int getPlayer2Game5Score() {
        return player2Game5Score;
    }

    public void setPlayer2Game5Score(int player2Game5Score) {
        this.player2Game5Score = player2Game5Score;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @Override
    public String toString() {
        return String.format(
                "Match: id=%d, matchDate='%s', player1Name='%s', player2Name='%s', " +
                        "player1Game1Score=%d, player2Game1Score=%d, " +
                        "player1Game2Score=%d, player2Game2Score=%d, " +
                        "player1Game3Score=%d, player2Game3Score=%d, " +
                        "player1Game4Score=%d, player2Game4Score=%d, " +
                        "player1Game5Score=%d, player2Game5Score=%d, " +
                        "winnerName='%s'",
                id, matchDate, player1Name, player2Name,
                player1Game1Score, player2Game1Score,
                player1Game2Score, player2Game2Score,
                player1Game3Score, player2Game3Score,
                player1Game4Score, player2Game4Score,
                player1Game5Score, player2Game5Score,
                winnerName
        );
    }
}
