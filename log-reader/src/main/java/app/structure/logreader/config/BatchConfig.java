package app.structure.logreader.config;

import app.structure.logreader.task.ReadLogTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step readFileStep() {
        return steps
                .get("readFileStep")
                .tasklet(new ReadLogTask())
                .build();
    }

    @Bean
    public Job firstJob() {
        return jobs
                .get("read-file-and-saved")
                .start(readFileStep())
                .build();
    }

}
