package kz.yandex.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import kz.yandex.exchange.model.Currency;
import kz.yandex.exchange.service.ExchangeRateService;
import kz.yandex.dto.account.CurrencyDto;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExchangeRatesController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/api/rates")
    public Flux<CurrencyDto> getCurrencies() {
        return exchangeRateService.getAllCurrentRates()
                .map(this::mapToDto);
    }

    private CurrencyDto mapToDto(Currency currency) {
        return CurrencyDto.builder()
                .name(currency.getName())
                .title(currency.getTitle())
                .value(currency.getValue())
                .build();
    }

}
