# beam-orleans-tech

## Création du projet

```bash
mvn archetype:generate \                                                                                                                                                             alexandre@devbox
      -DarchetypeGroupId=org.apache.beam \
      -DarchetypeArtifactId=beam-sdks-java-maven-archetypes-examples \
      -DarchetypeVersion=2.14.0 \
      -DgroupId=com.orleanstech.apachebeam \
      -DartifactId=apache-beam-sample \
      -Dversion="0.1" \
      -Dpackage=com.orleanstech.apachebeam \
      -DinteractiveMode=false
``` 

## Execution 

### Avec le direct runner 

 ```jshelllanguage
 mvn compile exec:java -Dexec.mainClass=com.orleanstech.apachebeam.Main    -Dexec.args="--runner=DirectRunner" -Pdirect-runner
```
### Avec flink en mode local 
 
 ```jshelllanguage
 mvn compile exec:java -Dexec.mainClass=com.orleanstech.apachebeam.Main    -Dexec.args="--runner=FlinkRunner" -Pflink-runner
```