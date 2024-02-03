// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.parsers;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.datetime.parsers.BaseHolidayParserConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.swedish.extractors.SwedishHolidayExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.DateUtil;
import com.microsoft.recognizers.text.utilities.StringUtility;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.IntFunction;

public class SwedishHolidayParserConfiguration extends BaseHolidayParserConfiguration {

    public SwedishHolidayParserConfiguration() {

        super();

        this.setHolidayRegexList(SwedishHolidayExtractorConfiguration.HolidayRegexList);

        HashMap<String, Iterable<String>> newMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : SwedishDateTime.HolidayNames.entrySet()) {
            if (entry.getValue() instanceof String[]) {
                newMap.put(entry.getKey(), Arrays.asList(entry.getValue()));
            }
        }
        this.setHolidayNames(ImmutableMap.copyOf(newMap));
    }

    @Override
    protected HashMap<String, IntFunction<LocalDateTime>> initHolidayFuncs() {

        HashMap<String, IntFunction<LocalDateTime>> holidays = new HashMap<>(super.initHolidayFuncs());
        holidays.put("mayday", SwedishHolidayParserConfiguration::mayday);
        holidays.put("yuandan", SwedishHolidayParserConfiguration::newYear);
        holidays.put("newyear", SwedishHolidayParserConfiguration::newYear);
        holidays.put("youthday", SwedishHolidayParserConfiguration::youthDay);
        holidays.put("girlsday", SwedishHolidayParserConfiguration::girlsDay);
        holidays.put("xmas", SwedishHolidayParserConfiguration::christmasDay);
        holidays.put("newyearday", SwedishHolidayParserConfiguration::newYear);
        holidays.put("aprilfools", SwedishHolidayParserConfiguration::foolDay);
        holidays.put("easterday", SwedishHolidayParserConfiguration::easterDay);
        holidays.put("newyearsday", SwedishHolidayParserConfiguration::newYear);
        holidays.put("femaleday", SwedishHolidayParserConfiguration::femaleDay);
        holidays.put("singleday", SwedishHolidayParserConfiguration::singlesDay);
        holidays.put("newyeareve", SwedishHolidayParserConfiguration::newYearEve);
        holidays.put("arborday", SwedishHolidayParserConfiguration::treePlantDay);
        holidays.put("loverday", SwedishHolidayParserConfiguration::valentinesDay);
        holidays.put("christmas", SwedishHolidayParserConfiguration::christmasDay);
        holidays.put("teachersday", SwedishHolidayParserConfiguration::teacherDay);
        holidays.put("stgeorgeday", SwedishHolidayParserConfiguration::stGeorgeDay);
        holidays.put("baptisteday", SwedishHolidayParserConfiguration::baptisteDay);
        holidays.put("bastilleday", SwedishHolidayParserConfiguration::bastilleDay);
        holidays.put("allsoulsday", SwedishHolidayParserConfiguration::allSoulsDay);
        holidays.put("veteransday", SwedishHolidayParserConfiguration::veteransDay);
        holidays.put("childrenday", SwedishHolidayParserConfiguration::childrenDay);
        holidays.put("maosbirthday", SwedishHolidayParserConfiguration::maoBirthday);
        holidays.put("allsaintsday", SwedishHolidayParserConfiguration::halloweenDay);
        holidays.put("stpatrickday", SwedishHolidayParserConfiguration::stPatrickDay);
        holidays.put("halloweenday", SwedishHolidayParserConfiguration::halloweenDay);
        holidays.put("allhallowday", SwedishHolidayParserConfiguration::allHallowDay);
        holidays.put("guyfawkesday", SwedishHolidayParserConfiguration::guyFawkesDay);
        holidays.put("christmaseve", SwedishHolidayParserConfiguration::christmasEve);
        holidays.put("groundhougday", SwedishHolidayParserConfiguration::groundhogDay);
        holidays.put("whiteloverday", SwedishHolidayParserConfiguration::whiteLoverDay);
        holidays.put("valentinesday", SwedishHolidayParserConfiguration::valentinesDay);
        holidays.put("treeplantingday", SwedishHolidayParserConfiguration::treePlantDay);
        holidays.put("cincodemayoday", SwedishHolidayParserConfiguration::cincoDeMayoDay);
        holidays.put("inaugurationday", SwedishHolidayParserConfiguration::inaugurationDay);
        holidays.put("independenceday", SwedishHolidayParserConfiguration::usaIndependenceDay);
        holidays.put("usindependenceday", SwedishHolidayParserConfiguration::usaIndependenceDay);
        holidays.put("juneteenth", SwedishHolidayParserConfiguration::juneteenth);

        return holidays;
    }

    private static LocalDateTime easterDay(int year) {
        return DateUtil.minValue();
    }

    private static LocalDateTime mayday(int year) {
        return DateUtil.safeCreateFromMinValue(year, 5, 1);
    }

    private static LocalDateTime newYear(int year) {
        return DateUtil.safeCreateFromMinValue(year, 1, 1);
    }

    private static LocalDateTime foolDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 4, 1);
    }

    private static LocalDateTime girlsDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 3, 7);
    }

    private static LocalDateTime youthDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 5, 4);
    }

    private static LocalDateTime femaleDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 3, 8);
    }

    private static LocalDateTime childrenDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 6, 1);
    }

    private static LocalDateTime teacherDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 9, 10);
    }

    private static LocalDateTime groundhogDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 2, 2);
    }

    private static LocalDateTime stGeorgeDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 4, 23);
    }

    private static LocalDateTime baptisteDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 6, 24);
    }

    private static LocalDateTime bastilleDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 7, 14);
    }

    private static LocalDateTime allSoulsDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 11, 2);
    }

    private static LocalDateTime singlesDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 11, 11);
    }

    private static LocalDateTime newYearEve(int year) {
        return DateUtil.safeCreateFromMinValue(year, 12, 31);
    }

    private static LocalDateTime treePlantDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 3, 12);
    }

    private static LocalDateTime stPatrickDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 3, 17);
    }

    private static LocalDateTime allHallowDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 11, 1);
    }

    private static LocalDateTime guyFawkesDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 11, 5);
    }

    private static LocalDateTime veteransDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 11, 11);
    }

    private static LocalDateTime maoBirthday(int year) {
        return DateUtil.safeCreateFromMinValue(year, 12, 26);
    }

    private static LocalDateTime valentinesDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 2, 14);
    }

    private static LocalDateTime whiteLoverDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 3, 14);
    }

    private static LocalDateTime cincoDeMayoDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 5, 5);
    }

    private static LocalDateTime halloweenDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 10, 31);
    }

    private static LocalDateTime christmasEve(int year) {
        return DateUtil.safeCreateFromMinValue(year, 12, 24);
    }

    private static LocalDateTime christmasDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 12, 25);
    }

    private static LocalDateTime inaugurationDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 1, 20);
    }

    private static LocalDateTime usaIndependenceDay(int year) {
        return DateUtil.safeCreateFromMinValue(year, 7, 4);
    }

    private static LocalDateTime juneteenth(int year) {
        return DateUtil.safeCreateFromMinValue(year, 6, 19);
    }

    @Override
    public int getSwiftYear(String text) {

        String trimmedText = StringUtility.trimStart(StringUtility.trimEnd(text)).toLowerCase(Locale.ROOT);
        int swift = -10;

        if (trimmedText.startsWith("next")) {
            swift = 1;
        } else if (trimmedText.startsWith("last")) {
            swift = -1;
        } else if (trimmedText.startsWith("this")) {
            swift = 0;
        }

        return swift;
    }

    public String sanitizeHolidayToken(String holiday) {
        return holiday.replace(" ", "").replace("'", "");
    }
}
