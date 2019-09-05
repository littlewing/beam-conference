package com.orleanstech.apachebeam;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;

public class AggregationFunction extends SimpleFunction<String, KV<String, String>> {
    @Override
    public KV<String, String> apply(String input) {
        return KV.of(input.substring(0, input.indexOf(";")), input);
    }
}
