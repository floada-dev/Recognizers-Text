// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.IParser;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.config.IOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.BaseDateTime;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.swedish.utilities.SwedishDatetimeUtilityConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.number.parsers.BaseNumberParser;
import com.microsoft.recognizers.text.number.swedish.extractors.IntegerExtractor;
import com.microsoft.recognizers.text.number.swedish.extractors.OrdinalExtractor;
import com.microsoft.recognizers.text.number.swedish.parsers.SwedishNumberParserConfiguration;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SwedishDateExtractorConfiguration extends BaseOptionsConfiguration implements IDateExtractorConfiguration {

    public static final Pattern MonthRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthRegex);
    public static final Pattern DayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ImplicitDayRegex);
    public static final Pattern MonthNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthNumRegex);
    public static final Pattern YearRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.YearRegex);
    public static final Pattern WeekDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekDayRegex);
    public static final Pattern SingleWeekDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SingleWeekDayRegex);
    public static final Pattern OnRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.OnRegex);
    public static final Pattern RelaxedOnRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelaxedOnRegex);
    public static final Pattern ThisRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ThisRegex);
    public static final Pattern LastDateRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LastDateRegex);
    public static final Pattern NextDateRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NextDateRegex);
    public static final Pattern DateUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DateUnitRegex);
    public static final Pattern SpecialDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecialDayRegex);
    public static final Pattern WeekDayOfMonthRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekDayOfMonthRegex);
    public static final Pattern RelativeWeekDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelativeWeekDayRegex);
    public static final Pattern SpecialDate = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecialDate);
    public static final Pattern SpecialDayWithNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecialDayWithNumRegex);
    public static final Pattern ForTheRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ForTheRegex);
    public static final Pattern WeekDayAndDayOfMonthRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekDayAndDayOfMonthRegex);
    public static final Pattern RelativeMonthRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelativeMonthRegex);
    public static final Pattern StrictRelativeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.StrictRelativeRegex);
    public static final Pattern PrefixArticleRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PrefixArticleRegex);
    public static final Pattern InConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.InConnectorRegex);
    public static final Pattern RangeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangeUnitRegex);
    public static final Pattern RangeConnectorSymbolRegex = RegExpUtility.getSafeRegExp(BaseDateTime.RangeConnectorSymbolRegex);

    public static final List<Pattern> DateRegexList = new ArrayList<Pattern>() {
        {
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor1));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor2));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor3));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor4));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor5));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor6));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor7L));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor7S));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor8));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor9L));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractor9S));
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.DateExtractorA));
        }
    };

    public static final List<Pattern> ImplicitDateList = new ArrayList<Pattern>() {
        {
            add(OnRegex);
            add(RelaxedOnRegex);
            add(SpecialDayRegex);
            add(ThisRegex);
            add(LastDateRegex);
            add(NextDateRegex);
            add(SingleWeekDayRegex);
            add(WeekDayOfMonthRegex);
            add(SpecialDate);
            add(SpecialDayWithNumRegex);
            add(RelativeWeekDayRegex);
        }
    };

    public static final Pattern OfMonth = RegExpUtility.getSafeRegExp(SwedishDateTime.OfMonth);
    public static final Pattern MonthEnd = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthEnd);
    public static final Pattern WeekDayEnd = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekDayEnd);
    public static final Pattern YearSuffix = RegExpUtility.getSafeRegExp(SwedishDateTime.YearSuffix);
    public static final Pattern LessThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LessThanRegex);
    public static final Pattern MoreThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MoreThanRegex);

    public static final ImmutableMap<String, Integer> DayOfWeek = SwedishDateTime.DayOfWeek;
    public static final ImmutableMap<String, Integer> MonthOfYear = SwedishDateTime.MonthOfYear;

    private final IExtractor integerExtractor;
    private final IExtractor ordinalExtractor;
    private final IParser numberParser;
    private final IDateTimeExtractor durationExtractor;
    private final IDateTimeUtilityConfiguration utilityConfiguration;
    private final List<Pattern> implicitDateList;

    public SwedishDateExtractorConfiguration(IOptionsConfiguration config) {
        super(config.getOptions());
        integerExtractor = IntegerExtractor.getInstance();
        ordinalExtractor = OrdinalExtractor.getInstance();
        numberParser = new BaseNumberParser(new SwedishNumberParserConfiguration());
        durationExtractor = new BaseDurationExtractor(new SwedishDurationExtractorConfiguration());
        utilityConfiguration = new SwedishDatetimeUtilityConfiguration();

        implicitDateList = new ArrayList<>(ImplicitDateList);
        if (this.getOptions().match(DateTimeOptions.CalendarMode)) {
            implicitDateList.add(DayRegex);
        }
    }

    @Override
    public Iterable<Pattern> getDateRegexList() {
        return DateRegexList;
    }

    @Override
    public Iterable<Pattern> getImplicitDateList() {
        return implicitDateList;
    }

    @Override
    public Pattern getOfMonth() {
        return OfMonth;
    }

    @Override
    public Pattern getMonthEnd() {
        return MonthEnd;
    }

    @Override
    public Pattern getWeekDayEnd() {
        return WeekDayEnd;
    }

    @Override
    public Pattern getDateUnitRegex() {
        return DateUnitRegex;
    }

    @Override
    public Pattern getForTheRegex() {
        return ForTheRegex;
    }

    @Override
    public Pattern getWeekDayAndDayOfMonthRegex() {
        return WeekDayAndDayOfMonthRegex;
    }

    @Override
    public Pattern getRelativeMonthRegex() {
        return RelativeMonthRegex;
    }

    @Override
    public Pattern getStrictRelativeRegex() {
        return StrictRelativeRegex;
    }

    @Override
    public Pattern getWeekDayRegex() {
        return WeekDayRegex;
    }

    @Override
    public Pattern getPrefixArticleRegex() {
        return PrefixArticleRegex;
    }

    @Override
    public Pattern getYearSuffix() {
        return YearSuffix;
    }

    @Override
    public Pattern getMoreThanRegex() {
        return MoreThanRegex;
    }

    @Override
    public Pattern getLessThanRegex() {
        return LessThanRegex;
    }

    @Override
    public Pattern getInConnectorRegex() {
        return InConnectorRegex;
    }

    @Override
    public Pattern getRangeUnitRegex() {
        return RangeUnitRegex;
    }

    @Override
    public Pattern getRangeConnectorSymbolRegex() {
        return RangeConnectorSymbolRegex;
    }

    @Override
    public IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    @Override
    public IExtractor getOrdinalExtractor() {
        return ordinalExtractor;
    }

    @Override
    public IParser getNumberParser() {
        return numberParser;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    @Override
    public ImmutableMap<String, Integer> getDayOfWeek() {
        return DayOfWeek;
    }

    @Override
    public ImmutableMap<String, Integer> getMonthOfYear() {
        return MonthOfYear;
    }
}
