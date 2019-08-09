package com.orleanstech.apachebeam;

public class Main {
    public static void main(String[] args) {
        FileCopyProcessor fileCopyProcessor = new FileCopyProcessor();
        System.out.println("Starting pipeline ...");
        fileCopyProcessor.runPipeline(args);
    }
}
