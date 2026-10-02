package ca.hccis.squash.bo;

import ca.hccis.squash.entity.Match;

/**
 * Determine the winner based on the scores of the games.
 *
 * @author BJM / Claude
 * @return 1 if player 1 wins, 2 if player 2 wins, 0 if there is no winner based on the scores
 * @since 20260921
 */
public class MatchBO {

    private static final int POINTS_TO_WIN_GAME = 11;
    private static final int GAMES_TO_WIN_MATCH = 3;

    /**
     * Returns 1 if player 1 wins the match, 2 if player 2 wins,
     * or 0 if the scores don't produce a valid winner.
     */
    public static int determineWinner(Match match) {
        if (match == null) {
            return 0;
        }

        int[][] games = {
                {match.getPlayer1Game1Score(), match.getPlayer2Game1Score()},
                {match.getPlayer1Game2Score(), match.getPlayer2Game2Score()},
                {match.getPlayer1Game3Score(), match.getPlayer2Game3Score()},
                {match.getPlayer1Game4Score(), match.getPlayer2Game4Score()},
                {match.getPlayer1Game5Score(), match.getPlayer2Game5Score()}
        };

        int player1Games = 0;
        int player2Games = 0;

        for (int[] game : games) {
            int p1 = game[0];
            int p2 = game[1];

            boolean matchAlreadyDecided =
                    player1Games == GAMES_TO_WIN_MATCH || player2Games == GAMES_TO_WIN_MATCH;

            if (matchAlreadyDecided) {
                // Games after the match is decided must be left at 0-0
                if (p1 != 0 || p2 != 0) {
                    return 0;
                }
                continue;
            }

            if (!isValidGame(p1, p2)) {
                // Covers unplayed (0-0) games before the match is decided, i.e. incomplete matches
                return 0;
            }

            if (p1 > p2) {
                player1Games++;
            } else {
                player2Games++;
            }
        }

        if (player1Games == GAMES_TO_WIN_MATCH) {
            return 1;
        }
        if (player2Games == GAMES_TO_WIN_MATCH) {
            return 2;
        }
        return 0;
    }

    private static boolean isValidGame(int p1, int p2) {
        if (p1 < 0 || p2 < 0) {
            return false;
        }

        int high = Math.max(p1, p2);
        int low = Math.min(p1, p2);

        if (high < POINTS_TO_WIN_GAME) {
            return false;
        }
        if (high == POINTS_TO_WIN_GAME) {
            return low <= POINTS_TO_WIN_GAME - 2;   // 11-9 or better; 11-10 is not over
        }
        return high - low == 2;                     // past 11, must be exactly 2 clear (12-10, 15-13)
    }
}

