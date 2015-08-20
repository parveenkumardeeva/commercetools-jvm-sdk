package io.sphere.sdk.search;

import io.sphere.sdk.models.Reference;
import io.sphere.sdk.models.Referenceable;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.function.Function;

import static io.sphere.sdk.search.TypeSerializer.*;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeSerializerTest {

    @Test
    public void serializesText() throws Exception {
        Function<String, String> serializer = ofString().serializer();
        assertThat(serializer.apply("some\"text")).isEqualTo("\"some\\\"text\"");
    }

    @Test
    public void serializesBoolean() throws Exception {
        Function<Boolean, String> serializer = ofBoolean().serializer();
        assertThat(serializer.apply(true)).isEqualTo("true");
        assertThat(serializer.apply(false)).isEqualTo("false");
    }

    @Test
    public void serializesNumber() throws Exception {
        Function<BigDecimal, String> serializer = ofNumber().serializer();
        assertThat(serializer.apply(valueOf(100))).isEqualTo("100");
        assertThat(serializer.apply(valueOf(10.5))).isEqualTo("10.5");
    }

    @Test
    public void serializesDate() throws Exception {
        Function<LocalDate, String> serializer = ofDate().serializer();
        assertThat(serializer.apply(date("2001-09-11"))).isEqualTo("\"2001-09-11\"");
        assertThat(serializer.apply(date("2001-10-01"))).isEqualTo("\"2001-10-01\"");
    }

    @Test
    public void serializesTime() throws Exception {
        Function<LocalTime, String> serializer = ofTime().serializer();
        assertThat(serializer.apply(time("22:05:19.203"))).isEqualTo("\"22:05:19.203\"");
        assertThat(serializer.apply(time("22:15:09.003"))).isEqualTo("\"22:15:09.003\"");
    }

    @Test
    public void serializesDateTime() throws Exception {
        Function<ZonedDateTime, String> serializer = ofDateTime().serializer();
        assertThat(serializer.apply(dateTime("2001-09-11T22:05:09.203+02:00"))).isEqualTo("\"2001-09-11T20:05:09.203Z\"");
    }

    @Test
    public void serializesMoneyAmount() throws Exception {
        Function<Long, String> serializer = ofMoneyCentAmount().serializer();
        assertThat(serializer.apply(3000L)).isEqualTo("3000");
        assertThat(serializer.apply(3050L)).isEqualTo("3050");
    }

    @Test
    public void serializesCurrency() throws Exception {
        Function<CurrencyUnit, String> serializer = ofCurrency().serializer();
        assertThat(serializer.apply(currency("EUR"))).isEqualTo("\"EUR\"");
        assertThat(serializer.apply(currency("USD"))).isEqualTo("\"USD\"");
    }

    @Test
    public void serializesReference() throws Exception {
        Function<Referenceable<Object>, String> serializer = ofReference().serializer();
        assertThat(serializer.apply(reference("some-id"))).isEqualTo("\"some-id\"");
    }

    private LocalDate date(final String date) {
        return LocalDate.parse(date);
    }

    private LocalTime time(final String time) {
        return LocalTime.parse(time);
    }

    private ZonedDateTime dateTime(final String dateTime) {
        return ZonedDateTime.parse(dateTime);
    }

    private CurrencyUnit currency(final String currencyCode) {
        return Monetary.getCurrency(currencyCode);
    }

    private Reference<Object> reference(String id) {
        return Reference.of("object", id);
    }
}