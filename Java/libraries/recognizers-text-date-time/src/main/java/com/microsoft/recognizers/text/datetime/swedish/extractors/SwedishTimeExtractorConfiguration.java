// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeZoneExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.ITimeExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishTimeExtractorConfiguration extends BaseOptionsConfiguration implements ITimeExtractorConfiguration {

    // part 1: smallest component
    // --------------------------------------
    public static final Pattern DescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DescRegex);
    public static final Pattern HourNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.HourNumRegex);
    public static final Pattern MinuteNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MinuteNumRegex);

    // part 2: middle level component
    // --------------------------------------
    // handle "... o'clock"
    public static final Pattern OclockRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.OclockRegex);

    // handle "... afternoon"
    public static final Pattern PmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PmRegex);

    // handle "... in the morning"
    public static final Pattern AmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmRegex);

    // handle "half past ..." "a quarter to ..."
    // rename 'min' group to 'deltamin'
    public static final Pattern LessThanOneHour = RegExpUtility.getSafeRegExp(SwedishDateTime.LessThanOneHour);

    // handle "six thirty", "six twenty one"
    public static final Pattern BasicTime = RegExpUtility.getSafeRegExp(SwedishDateTime.BasicTime);
    public static final Pattern TimePrefix = RegExpUtility.getSafeRegExp(SwedishDateTime.TimePrefix);
    public static final Pattern TimeSuffix = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeSuffix);
    public static final Pattern WrittenTimeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WrittenTimeRegex);

    // handle special time such as 'at midnight', 'midnight', 'midday'
    public static final Pattern MiddayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MiddayRegex);
    public static final Pattern MidTimeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MidTimeRegex);
    public static final Pattern MidnightRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MidnightRegex);
    public static final Pattern MidmorningRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MidmorningRegex);
    public static final Pattern MidafternoonRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MidafternoonRegex);

    // part 3: regex for time
    // --------------------------------------
    // handle "at four" "at 3"
    public static final Pattern AtRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AtRegex);
    public static final Pattern IshRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.IshRegex);
    public static final Pattern TimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern ConnectNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ConnectNumRegex);
    public static final Pattern TimeBeforeAfterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeBeforeAfterRegex);

    public static final Iterable<Pattern> TimeRegexList = new ArrayList<Pattern>() {
        {
            // (three min past)? seven|7|(senven thirty) pm
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex1));

            // (three min past)? 3:00(:00)? (pm)?
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex2));

            // (three min past)? 3.00 (pm)
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex3));

            // (three min past) (five thirty|seven|7|7:00(:00)?) (pm)? (in the night)
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex4));

            // (three min past) (five thirty|seven|7|7:00(:00)?) (pm)?
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex5));

            // (five thirty|seven|7|7:00(:00)?) (pm)? (in the night)
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex6));

            // (in the night) at (five thirty|seven|7|7:00(:00)?) (pm)?
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex7));

            // (in the night) (five thirty|seven|7|7:00(:00)?) (pm)?
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex8));

            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex9));

            // (three min past)? 3h00 (pm)?
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex10));

            // at 2.30, "at" prefix is required here
            // 3.30pm, "am/pm" suffix is required here
            add(RegExpUtility.getSafeRegExp(SwedishDateTime.TimeRegex11));

            // 340pm
            add(ConnectNumRegex);
        }
    };

    public final Iterable<Pattern> getTimeRegexList() {
        return TimeRegexList;
    }

    public final Pattern getAtRegex() {
        return AtRegex;
    }

    public final Pattern getIshRegex() {
        return IshRegex;
    }

    public final Pattern getTimeBeforeAfterRegex() {
        return TimeBeforeAfterRegex;
    }

    private IDateTimeExtractor durationExtractor;

    public final IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    private IDateTimeExtractor timeZoneExtractor;

    public final IDateTimeExtractor getTimeZoneExtractor() {
        return timeZoneExtractor;
    }


    public SwedishTimeExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    public SwedishTimeExtractorConfiguration(DateTimeOptions options) {
        super(options);
        durationExtractor = new BaseDurationExtractor(new SwedishDurationExtractorConfiguration());
        timeZoneExtractor = new BaseTimeZoneExtractor(new SwedishTimeZoneExtractorConfiguration(options));
    }
}
