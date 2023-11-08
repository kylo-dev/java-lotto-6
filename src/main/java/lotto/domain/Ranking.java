package lotto.domain;

public enum Ranking {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;
    private static final String ERROR_MESSAGE = "[ERROR]";

    private int countOfMatch;
    private int winningAmount;

    public static Ranking valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT){
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus){
            return SECOND;
        }

        for (Ranking rank : values()) {
            if (rank.matchCount(countOfMatch) && rank != SECOND){
                return rank;
            }
        }
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    Ranking(int countOfMatch, int winningAmount) {
        this.countOfMatch = countOfMatch;
        this.winningAmount = winningAmount;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}