package com.orleanstech.apachebeam;

import org.apache.beam.sdk.transforms.SimpleFunction;

public class CBFunction extends SimpleFunction<String, String> {
    @Override
    public String apply(String input) {
        return input.replace("CB", "Carte_Bancaire");
    }
}
