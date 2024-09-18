// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.numberwithunit.swedish.parsers;

import com.microsoft.recognizers.text.Culture;
import com.microsoft.recognizers.text.CultureInfo;
import com.microsoft.recognizers.text.numberwithunit.swedish.extractors.SpeedExtractorConfiguration;

public class SpeedParserConfiguration extends SwedishNumberWithUnitParserConfiguration {

    public SpeedParserConfiguration() {
        this(new CultureInfo(Culture.Swedish));
    }

    public SpeedParserConfiguration(CultureInfo cultureInfo) {
        super(cultureInfo);

        this.bindDictionary(SpeedExtractorConfiguration.SpeedSuffixList);
    }
}
