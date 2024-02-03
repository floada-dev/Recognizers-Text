// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.IParser;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.config.IOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDatePeriodExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.config.ResultIndex;
import com.microsoft.recognizers.text.datetime.resources.BaseDateTime;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.utilities.RegexExtension;
import com.microsoft.recognizers.text.number.parsers.BaseNumberParser;
import com.microsoft.recognizers.text.number.swedish.extractors.CardinalExtractor;
import com.microsoft.recognizers.text.number.swedish.extractors.OrdinalExtractor;
import com.microsoft.recognizers.text.number.swedish.parsers.SwedishNumberParserConfiguration;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishDatePeriodExtractorConfiguration extends BaseOptionsConfiguration implements IDatePeriodExtractorConfiguration {

    public static final Pattern YearRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.YearRegex);
    public static final Pattern TillRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TillRegex);
    public static final Pattern DateUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DateUnitRegex);
    public static final Pattern TimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern FollowedDateUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.FollowedDateUnit);
    public static final Pattern NumberCombinedWithDateUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.NumberCombinedWithDateUnit);
    public static final Pattern PreviousPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PreviousPrefixRegex);
    public static final Pattern NextPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NextPrefixRegex);
    public static final Pattern FutureSuffixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.FutureSuffixRegex);
    public static final Pattern WeekOfRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekOfRegex);
    public static final Pattern MonthOfRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthOfRegex);
    public static final Pattern RangeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangeUnitRegex);
    public static final Pattern InConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.InConnectorRegex);
    public static final Pattern WithinNextPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WithinNextPrefixRegex);
    public static final Pattern YearPeriodRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.YearPeriodRegex);
    public static final Pattern RelativeDecadeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelativeDecadeRegex);
    public static final Pattern ComplexDatePeriodRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ComplexDatePeriodRegex);
    public static final Pattern ReferenceDatePeriodRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ReferenceDatePeriodRegex);
    public static final Pattern AgoRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AgoRegex);
    public static final Pattern LaterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LaterRegex);
    public static final Pattern LessThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LessThanRegex);
    public static final Pattern MoreThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MoreThanRegex);
    public static final Pattern CenturySuffixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.CenturySuffixRegex);
    public static final Pattern IllegalYearRegex = RegExpUtility.getSafeRegExp(BaseDateTime.IllegalYearRegex);
    public static final Pattern NowRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NowRegex);

    // composite regexes
    public static final Pattern SimpleCasesRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SimpleCasesRegex);
    public static final Pattern BetweenRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.BetweenRegex);
    public static final Pattern OneWordPeriodRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.OneWordPeriodRegex);
    public static final Pattern MonthWithYear = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthWithYear);
    public static final Pattern MonthNumWithYear = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthNumWithYear);
    public static final Pattern WeekOfMonthRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekOfMonthRegex);
    public static final Pattern WeekOfYearRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekOfYearRegex);
    public static final Pattern MonthFrontBetweenRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthFrontBetweenRegex);
    public static final Pattern MonthFrontSimpleCasesRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MonthFrontSimpleCasesRegex);
    public static final Pattern QuarterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.QuarterRegex);
    public static final Pattern QuarterRegexYearFront = RegExpUtility.getSafeRegExp(SwedishDateTime.QuarterRegexYearFront);
    public static final Pattern AllHalfYearRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AllHalfYearRegex);
    public static final Pattern SeasonRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SeasonRegex);
    public static final Pattern WhichWeekRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WhichWeekRegex);
    public static final Pattern RestOfDateRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RestOfDateRegex);
    public static final Pattern LaterEarlyPeriodRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LaterEarlyPeriodRegex);
    public static final Pattern WeekWithWeekDayRangeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WeekWithWeekDayRangeRegex);
    public static final Pattern YearPlusNumberRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.YearPlusNumberRegex);
    public static final Pattern DecadeWithCenturyRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DecadeWithCenturyRegex);

    public static final Iterable<Pattern> SimpleCasesRegexes = new ArrayList<Pattern>() {
        {
            add(SimpleCasesRegex);
            add(BetweenRegex);
            add(OneWordPeriodRegex);
            add(MonthWithYear);
            add(MonthNumWithYear);
            add(YearRegex);
            add(WeekOfMonthRegex);
            add(WeekOfYearRegex);
            add(MonthFrontBetweenRegex);
            add(MonthFrontSimpleCasesRegex);
            add(QuarterRegex);
            add(QuarterRegexYearFront);
            add(AllHalfYearRegex);
            add(SeasonRegex);
            add(WhichWeekRegex);
            add(RestOfDateRegex);
            add(LaterEarlyPeriodRegex);
            add(WeekWithWeekDayRangeRegex);
            add(YearPlusNumberRegex);
            add(DecadeWithCenturyRegex);
            add(RelativeDecadeRegex);
            add(ReferenceDatePeriodRegex);
        }
    };

    public static final Pattern rangeConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangeConnectorRegex);
    private final String[] durationDateRestrictions = SwedishDateTime.DurationDateRestrictions.toArray(new String[0]);

    private final IDateTimeExtractor datePointExtractor;
    private final IExtractor cardinalExtractor;
    private final IExtractor ordinalExtractor;
    private final IDateTimeExtractor durationExtractor;
    private final IParser numberParser;

    public SwedishDatePeriodExtractorConfiguration(IOptionsConfiguration config) {
        super(config.getOptions());

        datePointExtractor = new BaseDateExtractor(new SwedishDateExtractorConfiguration(this));
        cardinalExtractor = CardinalExtractor.getInstance();
        ordinalExtractor = OrdinalExtractor.getInstance();
        durationExtractor = new BaseDurationExtractor(new SwedishDurationExtractorConfiguration());
        numberParser = new BaseNumberParser(new SwedishNumberParserConfiguration());
    }

    @Override
    public Iterable<Pattern> getSimpleCasesRegexes() {
        return SimpleCasesRegexes;
    }

    @Override
    public Pattern getIllegalYearRegex() {
        return IllegalYearRegex;
    }

    @Override
    public Pattern getYearRegex() {
        return YearRegex;
    }

    @Override
    public Pattern getTillRegex() {
        return TillRegex;
    }

    @Override
    public Pattern getDateUnitRegex() {
        return DateUnitRegex;
    }

    @Override
    public Pattern getTimeUnitRegex() {
        return TimeUnitRegex;
    }

    @Override
    public Pattern getFollowedDateUnit() {
        return FollowedDateUnit;
    }

    @Override
    public Pattern getNumberCombinedWithDateUnit() {
        return NumberCombinedWithDateUnit;
    }

    @Override
    public Pattern getPastRegex() {
        return PreviousPrefixRegex;
    }

    @Override
    public Pattern getFutureRegex() {
        return NextPrefixRegex;
    }

    @Override
    public Pattern getFutureSuffixRegex() {
        return FutureSuffixRegex;
    }

    @Override
    public Pattern getWeekOfRegex() {
        return WeekOfRegex;
    }

    @Override
    public Pattern getMonthOfRegex() {
        return MonthOfRegex;
    }

    @Override
    public Pattern getRangeUnitRegex() {
        return RangeUnitRegex;
    }

    @Override
    public Pattern getInConnectorRegex() {
        return InConnectorRegex;
    }

    @Override
    public Pattern getWithinNextPrefixRegex() {
        return WithinNextPrefixRegex;
    }

    @Override
    public Pattern getYearPeriodRegex() {
        return YearPeriodRegex;
    }

    @Override
    public Pattern getRelativeDecadeRegex() {
        return RelativeDecadeRegex;
    }

    @Override
    public Pattern getComplexDatePeriodRegex() {
        return ComplexDatePeriodRegex;
    }

    @Override
    public Pattern getReferenceDatePeriodRegex() {
        return ReferenceDatePeriodRegex;
    }

    @Override
    public Pattern getAgoRegex() {
        return AgoRegex;
    }

    @Override
    public Pattern getLaterRegex() {
        return LaterRegex;
    }

    @Override
    public Pattern getLessThanRegex() {
        return LessThanRegex;
    }

    @Override
    public Pattern getMoreThanRegex() {
        return MoreThanRegex;
    }

    @Override
    public Pattern getCenturySuffixRegex() {
        return CenturySuffixRegex;
    }

    @Override
    public Pattern getNowRegex() {
        return NowRegex;
    }

    @Override
    public IDateTimeExtractor getDatePointExtractor() {
        return datePointExtractor;
    }

    @Override
    public IExtractor getCardinalExtractor() {
        return cardinalExtractor;
    }

    @Override
    public IExtractor getOrdinalExtractor() {
        return ordinalExtractor;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IParser getNumberParser() {
        return numberParser;
    }

    @Override
    public String[] getDurationDateRestrictions() {
        return durationDateRestrictions;
    }

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
