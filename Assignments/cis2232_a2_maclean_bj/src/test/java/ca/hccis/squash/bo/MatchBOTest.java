package ca.hccis.squash.bo;

import ca.hccis.squash.bo.MatchBO;
import ca.hccis.squash.entity.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchBOTest {

    /**
     * Test 1 created by BJ following TDD
     * @since 20260921
     * @author BJM
     */
    @Test
    void testDetermineWinnerPlayer1_11_0s() {

        Match match = new Match();

        match.setPlayer1Game1Score(11);
        match.setPlayer2Game1Score(0);
        match.setPlayer1Game2Score(11);
        match.setPlayer2Game2Score(0);
        match.setPlayer1Game3Score(11);
        match.setPlayer2Game3Score(0);

        int actual = MatchBO.determineWinner(match);

        assertEquals(1, actual);
    }

    /**
     * Test 2 created by BJ following TDD
     * @since 20260921
     * @author BJM
     */
    @Test
    void testDetermineWinnerPlayer1_13_11s() {

        Match match = new Match();

        match.setPlayer1Game1Score(13);
        match.setPlayer2Game1Score(11);
        match.setPlayer1Game2Score(13);
        match.setPlayer2Game2Score(11);
        match.setPlayer1Game3Score(13);
        match.setPlayer2Game3Score(11);

        int actual = MatchBO.determineWinner(match);

        assertEquals(1, actual);
    }

    /**
     * Test 3 created by BJ following TDD
     * @since 20260921
     * @author BJM
     */

    @Test
    void testDetermineWinnerPlayer2_13_11s() {

        Match match = new Match();

        match.setPlayer1Game1Score(11);
        match.setPlayer2Game1Score(13);
        match.setPlayer1Game2Score(11);
        match.setPlayer2Game2Score(13);
        match.setPlayer1Game3Score(11);
        match.setPlayer2Game3Score(13);

        int actual = MatchBO.determineWinner(match);

        assertEquals(2, actual);
    }


    //****************************************************************************
    //The following were the unit tests that were created by Claude Code AI
    //****************************************************************************

    private Match buildMatch(int[][] scores) {
        Match match = new Match();
        match.setPlayer1Name("Alice");
        match.setPlayer2Name("Bob");

        match.setPlayer1Game1Score(scores[0][0]);
        match.setPlayer2Game1Score(scores[0][1]);
        match.setPlayer1Game2Score(scores[1][0]);
        match.setPlayer2Game2Score(scores[1][1]);
        match.setPlayer1Game3Score(scores[2][0]);
        match.setPlayer2Game3Score(scores[2][1]);
        match.setPlayer1Game4Score(scores[3][0]);
        match.setPlayer2Game4Score(scores[3][1]);
        match.setPlayer1Game5Score(scores[4][0]);
        match.setPlayer2Game5Score(scores[4][1]);
        return match;
    }

    // Happy path: player 1 wins 3-0, games 4 and 5 not played
    @Test
    void determineWinner_player1WinsInStraightGames() {
        Match match = buildMatch(new int[][] {
                {11, 5}, {11, 8}, {11, 9}, {0, 0}, {0, 0}
        });

        assertEquals(1, MatchBO.determineWinner(match));
    }

    // Edge case: player 2 wins 3-2, with the fifth game decided by the win-by-two rule
    @Test
    void determineWinner_player2WinsFiveGamesWithExtendedFinalGame() {
        Match match = buildMatch(new int[][] {
                {11, 7}, {6, 11}, {11, 9}, {8, 11}, {12, 14}
        });

        assertEquals(2, MatchBO.determineWinner(match));
    }

    // Edge case: incomplete match (2-2 in games, game 5 not played) has no winner
    @Test
    void determineWinner_incompleteMatchReturnsNoWinner() {
        Match match = buildMatch(new int[][] {
                {11, 7}, {6, 11}, {11, 9}, {8, 11}, {0, 0}
        });

        //TODO Note that my code only uses assertEquals and the A2 requirements ask to have > 1 type of assert methods used.
        assertEquals(0, MatchBO.determineWinner(match));
    }

    @Test
    public void testPlayer1Wins() {

        Match match = new Match();

        match.setPlayer1Game1Score(11);
        match.setPlayer2Game1Score(5);

        match.setPlayer1Game2Score(11);
        match.setPlayer2Game2Score(7);

        match.setPlayer1Game3Score(11);
        match.setPlayer2Game3Score(9);

        assertEquals(1, MatchBO.determineWinner(match));
    }

    @Test
    public void testPlayer2Wins() {

        Match match = new Match();

        match.setPlayer1Game1Score(5);
        match.setPlayer2Game1Score(11);

        match.setPlayer1Game2Score(7);
        match.setPlayer2Game2Score(11);

        match.setPlayer1Game3Score(9);
        match.setPlayer2Game3Score(11);

        assertEquals(2, MatchBO.determineWinner(match));
    }

    @Test
    public void testNoValidWinner() {

        Match match = new Match();

        match.setPlayer1Game1Score(11);
        match.setPlayer2Game1Score(5);

        match.setPlayer1Game2Score(8);
        match.setPlayer2Game2Score(11);

        match.setPlayer1Game3Score(10);
        match.setPlayer2Game3Score(10);

        match.setPlayer1Game4Score(9);
        match.setPlayer2Game4Score(9);

        match.setPlayer1Game5Score(10);
        match.setPlayer2Game5Score(8);

        assertEquals(0, MatchBO.determineWinner(match));
    }

}