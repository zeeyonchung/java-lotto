package lotto;

import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int getPriceTotal() {
        return Lotto.PRICE * lottos.size();
    }
}