package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import util.ShuffleNumberGenerator;

public class LottoTicketTest {

    @Test
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount, Collections.emptyList(), new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    public void createLottoTicketWithPassiveLotto() {
        List<List<LottoNumber>> passiveLottoNumbers = List.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        LottoTicket lottoTicket = new LottoTicket(14, passiveLottoNumbers, new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    public void checkPassiveLottoCount() {
        List<List<LottoNumber>> passiveLottoNumbers = List.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        LottoTicket lottoTicket = new LottoTicket(14, passiveLottoNumbers, new ShuffleNumberGenerator());
        assertThat(lottoTicket.getLottos().size() - lottoTicket.getAutoLottoCount()).isEqualTo(1);
    }

    @Test
    public void checkAutoLottoCount() {
        List<List<LottoNumber>> passiveLottoNumbers = List.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        LottoTicket lottoTicket = new LottoTicket(14, passiveLottoNumbers, new ShuffleNumberGenerator());
        assertThat(lottoTicket.getAutoLottoCount()).isEqualTo(14);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> list) {
        return list.stream()
                .map(LottoNumber :: of)
                .collect(Collectors.toList());
    }
}
