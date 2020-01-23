package bzh.duboutdumonde.devfest.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

import java.io.Serializable;

public class MyPipeline implements Serializable {
    public MyPipeline() {
    }

    public void run(String[] args) {
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
        // read CSV from directory
        pipeline.apply(TextIO.read().from("data/*.csv"))
                // remove 'Résident'

                // transform to key value

                // group by key

                // get Count of each parking meter

                // write to target folder
                .apply(TextIO.write().to("target/orleanstech"));

        pipeline.run();
    }
}
