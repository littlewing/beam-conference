package com.orleanstech.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;

import java.io.Serializable;
import java.util.stream.StreamSupport;

/**
 * Copy processor for beam
 */
public class FileCopyProcessor implements Serializable {

    public void runPipeline(String[] args) {
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
        System.out.println("Pipeline created");
        pipeline.apply(TextIO.read()
                .from("/home/alexandre/java/src/beam-orleans-tech/data/*.csv"))
                .apply(ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext processContext) {
                        if (!processContext.element().contains("Résident")) {
                            processContext.output(processContext.element());
                        }
                    }
                }))
                // move CB to Carte_Bancaire
                .apply(MapElements.via(new CBFunction()))
                // Creates a Key Value based on the first item of each line
                .apply(MapElements.via(new AggregationFunction()))
                // Groups by key
                .apply(GroupByKey.create())
                // Aggregates by Key and the number of lines which no contains Carte_bancaire
                .apply(ParDo.of(new DoFn<KV<String, Iterable<String>>, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext processContext) {
                        KV<String, Iterable<String>> element = processContext.element();
                        long count = StreamSupport.stream(element.getValue().spliterator(), false)
                                .filter(s -> s.contains("Carte_Bancaire"))
                                .count();
                        processContext.output(element.getKey() + " : " + count);
                    }
                }))
                .apply(TextIO.write()
                        .to("/home/alexandre/java/src/beam-orleans-tech/target/orleanstech"));
        pipeline.run().waitUntilFinish();
    }
}
