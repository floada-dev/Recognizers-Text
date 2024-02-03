// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.utilities;

import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.regex.Pattern;

public class SwedishDatetimeUtilityConfiguration implements IDateTimeUtilityConfiguration {

    public static final Pattern AgoRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AgoRegex);
    public static final Pattern LaterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LaterRegex);
    public static final Pattern InConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.InConnectorRegex);
    public static final Pattern WithinNextPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.WithinNextPrefixRegex);
    public static final Pattern AmDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmDescRegex);
    public static final Pattern PmDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PmDescRegex);
    public static final Pattern AmPmDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmPmDescRegex);
    public static final Pattern RangeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangeUnitRegex);
    public static final Pattern TimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern DateUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DateUnitRegex);
    public static final Pattern CommonDatePrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.CommonDatePrefixRegex);

    @Override
    public Pattern getAgoRegex() {
        return AgoRegex;
    }

    @Override
    public Pattern getLaterRegex() {
        return LaterRegex;
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
    public Pattern getRangeUnitRegex() {
        return RangeUnitRegex;
    }

    @Override
    public Pattern getTimeUnitRegex() {
        return TimeUnitRegex;
    }

    @Override
    public Pattern getDateUnitRegex() {
        return DateUnitRegex;
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
    public Pattern getAmPmDescRegex() {
        return AmPmDescRegex;
    }

    @Override
    public Pattern getCommonDatePrefixRegex() {
        return CommonDatePrefixRegex;
    }
}
