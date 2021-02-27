package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.lotto.LottoTicket.LOTTO_TICKET_SIZE;

public class LottoNumbersFactory {
    private static final int START_INDEX = 0;

    private LottoNumbersFactory() {
    }

    public static List<LottoNumber> generateAutoLottoNumbers() {
        List<Integer> numbersKeys = new ArrayList<>(LottoNumber.getKeys());
        Collections.shuffle(numbersKeys);
        return IntStream.range(START_INDEX, LOTTO_TICKET_SIZE)
                .mapToObj(i -> LottoNumber.valueOf(numbersKeys.get(i)))
                .collect(Collectors.toList());
    }
}