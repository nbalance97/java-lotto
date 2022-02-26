package domain;

import util.LottoNumberGenerator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final int SECOND_DECIMAL_DIGIT = 100;

    private final int money;
    private final LottoResult lottoResult;
    private final LottoTicket lottoTicket;

    public LottoMachine(int money, LottoNumberGenerator numberGenerator) {
        this.money = money;
        this.lottoResult = new LottoResult();
        this.lottoTicket = new LottoTicket(money / LOTTO_PRICE, numberGenerator);
    }

    public double calculateProfit() {
        DecimalFormat decimalFormat = decimalFormatSetting();
        double profitRate = lottoResult.sumTotalPrice() / (double) money;
        return Double.parseDouble(decimalFormat.format(profitRate));
    }

    private DecimalFormat decimalFormatSetting() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat;
    }

    public LottoResult getResults(WinningLotto winningLotto) {
        return lottoTicket.getLottoResult(winningLotto, lottoResult);
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
