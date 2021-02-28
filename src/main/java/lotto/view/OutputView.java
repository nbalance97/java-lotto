package lotto.view;

import lotto.domain.*;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTO_PURCHASE_FORM = "수동으로 %d장, 자동으로 %d개를 구매했습니다." + System.lineSeparator();
    private static final String RANK_BONUS_FORM = "%s개 일치, 보너스 볼 일치(%s원)- %s개" + System.lineSeparator();
    private static final String RANK_FORM = "%s개 일치 (%s원)- %s개" + System.lineSeparator();
    private static final String LOTTO_DELIMITER = ", ";
    private static final String LOTTO_FORM = "[%s]" + System.lineSeparator();
    private static final String PROFIT_FORM = "총 수익률은 %.2f 입니다." + System.lineSeparator();
    private static final String GAME_RESULT = "당첨 통계";
    private static final String SECTION_DELIMITER = "--------";


    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(String format, Object... message) {
        System.out.printf(format, message);
    }

    public static void printError(String errorMessage) {
        printMessage(System.lineSeparator() + errorMessage + System.lineSeparator());
    }

    public static void printEachLotto(final Lottos manualLottos, final Lottos autoLottos) {
        manualLottos.toList().forEach(OutputView::printOneLotto);
        autoLottos.toList().forEach(OutputView::printOneLotto);
        System.out.println();
    }

    public static void printTotalNumberOfLotto(Lottos manualLottos, Lottos AutoLottos) {
        printMessageByFormat(LOTTO_PURCHASE_FORM, manualLottos.toList().size(), AutoLottos.toList().size());
    }

    private static void printOneLotto(Lotto lotto) {
        String joinLottoNumber = lotto.toList().stream()
                .map(LottoNumber::getStringNumber)
                .collect(Collectors.joining(LOTTO_DELIMITER));

        printMessageByFormat(LOTTO_FORM, joinLottoNumber);
    }

    public static void printLottoGameResult(final LottoGameResult lottoGameResult) {
        printMessage(System.lineSeparator() + GAME_RESULT);
        printMessage(SECTION_DELIMITER);

        printRankResult(lottoGameResult);

        printMessageByFormat(PROFIT_FORM, lottoGameResult.calculateProfit());
    }

    public static void printRankResult(final LottoGameResult lottoGameResult) {
        lottoGameResult.toResultMap()
                .entrySet()
                .forEach(entry -> {
                    if (entry.getKey() != Rank.SECOND && entry.getKey() != Rank.NOTHING) {
                        printByRankForm(RANK_FORM, entry);
                    }
                    if (entry.getKey() == Rank.SECOND) {
                        printByRankForm(RANK_BONUS_FORM, entry);
                    }
                });
    }

    private static void printByRankForm(String rankBonusForm, Map.Entry<Rank, Integer> entry) {
        String rank = String.valueOf(entry.getKey().getCountOfMatch());
        String reward = String.valueOf(entry.getKey().getReward());
        String matchCount = String.valueOf(entry.getValue());

        printMessageByFormat(rankBonusForm, rank, reward, matchCount);
    }
}
