package com.orleanstech.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.io.Serializable;

public class FileCopyProcessor implements Serializable {

    public void runPipeline(String[] args) {
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
        System.out.println("Pipeline created");
        pipeline.apply(TextIO.read()
                .from("/home/alexandre/java/src/beam-orleans-tech/data/*.csv"))
                .apply(ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void procesElement(ProcessContext processContext) {
                        if (!processContext.element().contains("Résident")) {
                            processContext.output(processContext.element());
                        }
                    }
                }))
                .apply(TextIO.write()
                        .to("/home/alexandre/java/src/beam-orleans-tech/target/orleanstech"));
        pipeline.run().waitUntilFinish();
    }
}
