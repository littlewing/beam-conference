package com.orleanstech.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

import java.io.Serializable;

public class MyPipeline implements Serializable {
    public MyPipeline() {
    }

    public void run(String[] args) {
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
        pipeline.apply(TextIO.read().from("data/*.csv"))
                .apply(TextIO.write().to("target/orleanstech"));
        pipeline.run();
    }
}
