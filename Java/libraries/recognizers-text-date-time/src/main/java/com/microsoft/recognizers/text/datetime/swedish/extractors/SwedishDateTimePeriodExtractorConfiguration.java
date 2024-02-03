// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimePeriodExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeZoneExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateTimePeriodExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.config.ResultIndex;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.utilities.RegexExtension;
import com.microsoft.recognizers.text.number.swedish.extractors.CardinalExtractor;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishDateTimePeriodExtractorConfiguration extends BaseOptionsConfiguration implements IDateTimePeriodExtractorConfiguration {

    public static final Iterable<Pattern> SimpleCases = new ArrayList<Pattern>() {
        {
            add(SwedishTimePeriodExtractorConfiguration.PureNumFromTo);
            add(SwedishTimePeriodExtractorConfiguration.PureNumBetweenAnd);
        }
    };

    public static final Pattern PeriodTimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PeriodTimeOfDayRegex);
    public static final Pattern PeriodSpecificTimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PeriodSpecificTimeOfDayRegex);
    public static final Pattern TimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern TimeFollowedUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeFollowedUnit);
    public static final Pattern TimeNumberCombinedWithUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeNumberCombinedWithUnit);
    public static final Pattern PeriodTimeOfDayWithDateRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PeriodTimeOfDayWithDateRegex);
    public static final Pattern RelativeTimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelativeTimeUnitRegex);
    public static final Pattern RestOfDateTimeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RestOfDateTimeRegex);
    public static final Pattern GeneralEndingRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.GeneralEndingRegex);
    public static final Pattern MiddlePauseRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MiddlePauseRegex);
    public static final Pattern AmDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmDescRegex);
    public static final Pattern PmDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PmDescRegex);
    public static final Pattern WithinNextPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WithinNextPrefixRegex);
    public static final Pattern DateUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DateUnitRegex);
    public static final Pattern PrefixDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PrefixDayRegex);
    public static final Pattern SuffixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SuffixRegex);
    public static final Pattern BeforeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.BeforeRegex);
    public static final Pattern AfterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AfterRegex);

    private final String tokenBeforeDate;

    private final IExtractor cardinalExtractor;
    private final IDateTimeExtractor singleDateExtractor;
    private final IDateTimeExtractor singleTimeExtractor;
    private final IDateTimeExtractor singleDateTimeExtractor;
    private final IDateTimeExtractor durationExtractor;
    private final IDateTimeExtractor timePeriodExtractor;
    private final IDateTimeExtractor timeZoneExtractor;

    private final Pattern weekDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekDayRegex);
    private final Pattern rangeConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangeConnectorRegex);

    public SwedishDateTimePeriodExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    public SwedishDateTimePeriodExtractorConfiguration(DateTimeOptions options) {

        super(options);

        tokenBeforeDate = SwedishDateTime.TokenBeforeDate;

        cardinalExtractor = CardinalExtractor.getInstance();
        singleDateExtractor = new BaseDateExtractor(new SwedishDateExtractorConfiguration(this));
        singleTimeExtractor = new BaseTimeExtractor(new SwedishTimeExtractorConfiguration(options));
        singleDateTimeExtractor = new BaseDateTimeExtractor(new SwedishDateTimeExtractorConfiguration(options));
        durationExtractor = new BaseDurationExtractor(new SwedishDurationExtractorConfiguration(options));
        timePeriodExtractor = new BaseTimePeriodExtractor(new SwedishTimePeriodExtractorConfiguration(options));
        timeZoneExtractor = new BaseTimeZoneExtractor(new SwedishTimeZoneExtractorConfiguration(options));
    }

    @Override
    public String getTokenBeforeDate() {
        return tokenBeforeDate;
    }

    @Override
    public Iterable<Pattern> getSimpleCasesRegex() {
        return SimpleCases;
    }

    @Override
    public Pattern getPrepositionRegex() {
        return SwedishTimePeriodExtractorConfiguration.PrepositionRegex;
    }

    @Override
    public Pattern getTillRegex() {
        return SwedishTimePeriodExtractorConfiguration.TillRegex;
    }

    @Override
    public Pattern getSpecificTimeOfDayRegex() {
        return PeriodSpecificTimeOfDayRegex;
    }

    @Override
    public Pattern getTimeOfDayRegex() {
        return PeriodTimeOfDayRegex;
    }

    @Override
    public Pattern getFollowedUnit() {
        return TimeFollowedUnit;
    }

    @Override
    public Pattern getNumberCombinedWithUnit() {
        return TimeNumberCombinedWithUnit;
    }

    @Override
    public Pattern getTimeUnitRegex() {
        return TimeUnitRegex;
    }

    @Override
    public Pattern getPastPrefixRegex() {
        return SwedishDatePeriodExtractorConfiguration.PreviousPrefixRegex;
    }

    @Override
    public Pattern getNextPrefixRegex() {
        return SwedishDatePeriodExtractorConfiguration.NextPrefixRegex;
    }

    @Override
    public Pattern getFutureSuffixRegex() {
        return SwedishDatePeriodExtractorConfiguration.FutureSuffixRegex;
    }

    @Override
    public Pattern getWeekDayRegex() {
        return weekDayRegex;
    }

    @Override
    public Pattern getPeriodTimeOfDayWithDateRegex() {
        return PeriodTimeOfDayWithDateRegex;
    }

    @Override
    public Pattern getRelativeTimeUnitRegex() {
        return RelativeTimeUnitRegex;
    }

    @Override
    public Pattern getRestOfDateTimeRegex() {
        return RestOfDateTimeRegex;
    }

    @Override
    public Pattern getGeneralEndingRegex() {
        return GeneralEndingRegex;
    }

    @Override
    public Pattern getMiddlePauseRegex() {
        return MiddlePauseRegex;
    }

    @Override
    public Pattern getAmDescRegex() {
        return AmDescRegex;
    }

    @Override
    public Pattern getPmDescRegex() {
        return PmDescRegex;
    }

    @Override
    public Pattern getWithinNextPrefixRegex() {
        return WithinNextPrefixRegex;
    }

    @Override
    public Pattern getDateUnitRegex() {
        return DateUnitRegex;
    }

    @Override
    public Pattern getPrefixDayRegex() {
        return PrefixDayRegex;
    }

    @Override
    public Pattern getSuffixRegex() {
        return SuffixRegex;
    }

    @Override
    public Pattern getBeforeRegex() {
        return BeforeRegex;
    }

    @Override
    public Pattern getAfterRegex() {
        return AfterRegex;
    }

    @Override
    public IExtractor getCardinalExtractor() {
        return cardinalExtractor;
    }

    @Override
    public IDateTimeExtractor getSingleDateExtractor() {
        return singleDateExtractor;
    }

    @Override
    public IDateTimeExtractor getSingleTimeExtractor() {
        return singleTimeExtractor;
    }

    @Override
    public IDateTimeExtractor getSingleDateTimeExtractor() {
        return singleDateTimeExtractor;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IDateTimeExtractor getTimePeriodExtractor() {
        return timePeriodExtractor;
    }

    @Override
    public IDateTimeExtractor getTimeZoneExtractor() {
        return timeZoneExtractor;
    }

    // TODO: these three methods are the same in DatePeriod, should be abstracted
    @Override
    public ResultIndex getFromTokenIndex(String text) {
        int index = -1;
        boolean result = false;
        if (text.endsWith("from")) {
            result = true;
            index = text.lastIndexOf("from");
        }

        return new ResultIndex(result, index);
    }

    @Override
    public ResultIndex getBetweenTokenIndex(String text) {
        int index = -1;
        boolean result = false;
        if (text.endsWith("between")) {
            result = true;
            index = text.lastIndexOf("between");
        }

        return new ResultIndex(result, index);
    }

    @Override
    public boolean hasConnectorToken(String text) {
        return RegexExtension.isExactMatch(rangeConnectorRegex, text, true);
    }
}
