package lotto;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    //입력받은 구입금액에 해당하는 로또 구매 갯수 반환 (1장, 1000원)
    //길이 6인 로또 번호 리스트 구매 갯수 만큼 생성
    //로또번호 정렬 확인
    //입력받은 당첨 번호가 로또 번호 리스트에 있는지 확인
    //로또 하나당 일치하는 갯수 넘겨받아 3~6개 일치 할 경우 총 당첨금 체크
    //구입금액/당첨금 으로 수익률 체크

    LottoSystem lottoSystem;

    @Before
    public void setUp() throws Exception {
        lottoSystem = new LottoSystem();
    }

    @Test
    public void 구입금액_로또구매갯수반환() {
        int a = lottoSystem.calcLottoCount(14000);
        assertThat(a).isEqualTo(14);
    }

    @Test
    public void 로또번호의길이가6인_로또번호리스트_구매갯수만큼생성() {
        List<Lotto> lottos = lottoSystem.makeLottoList(lottoSystem.lottoNumbersSetting(), lottoSystem.calcLottoCount(14000));
        assertThat(lottos.size()).isEqualTo(lottoSystem.calcLottoCount(14000));
        assertThat(lottos.get(0).getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    public void 로또번호정렬확인() {
        Integer[] test = {3, 2, 1};
        List<Integer> lotto = Arrays.asList(test);

        assertThat(lotto.get(0)).isEqualTo(3);
        lottoSystem.sortLottoNumbers(lotto);
        assertThat(lotto.get(0)).isEqualTo(1);
    }

    @Test
    public void 당첨번호포함_갯수확인() {
        Integer[] test = {3, 13, 15, 21, 28, 41};
        Lotto lotto = new Lotto(Arrays.asList(test));
        Integer[] test2 = {3, 13, 17, 18, 33, 34};
        List<Integer> pickLottoNumber = Arrays.asList(test2);

        int sum = 0;
        for(int number : pickLottoNumber) {
            sum += WinningLotto.containsPerOneLotto(lotto, number);
        }
        assertThat(sum).isEqualTo(2);
    }

    @Test
    public void 촏당첨금체크() {
        Integer[] winPrice = {5_000, 50_000, 1_500_000, 2_000_000_000};
        Integer[] winResult = {1, 0, 0, 0};
        assertThat(Profit.totalPrice(Arrays.asList(winResult), Arrays.asList(winPrice))).isEqualTo(5000);
    }
}