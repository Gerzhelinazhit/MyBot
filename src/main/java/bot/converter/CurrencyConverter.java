package bot.converter;

import bot.dao.CurrencyDao;
import bot.entity.CurrencyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {
    @Autowired
    private CurrencyDao currencyDao;


    public String getCurrency() {

        CurrencyEntity EURO;
        CurrencyEntity USD;
        CurrencyEntity RUB;
        StringBuilder sb = new StringBuilder();
        EURO = currencyDao.getByKey(292);
        sb.append(EURO.getCurName() + " = ");
        sb.append(EURO.getCurOfficialRate() + " BYN за ");
        sb.append(EURO.getCurScale() + " единиц валюты.\n");
        USD = currencyDao.getByKey(145);
        sb.append(USD.getCurName() + " = ");
        sb.append(USD.getCurOfficialRate() + " BYN за ");
        sb.append(USD.getCurScale() + " единиц валюты.\n");
        RUB = currencyDao.getByKey(298);
        sb.append(RUB.getCurName() + " = ");
        sb.append(RUB.getCurOfficialRate() + " BYN за ");
        sb.append(RUB.getCurScale() + " единиц валюты.\n");

        return sb.toString();
    }
}
