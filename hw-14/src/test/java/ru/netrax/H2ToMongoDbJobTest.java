package ru.netrax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
public class H2ToMongoDbJobTest {

    public static final String IMPORT_USER_JOB_NAME = "importToMongoDbJob";
    @Autowired
    private JobLauncherTestUtils jobLauncher;
    @Autowired
    private JobRepositoryTestUtils jobRepository;

    @BeforeEach
    void removeJob() {
        jobRepository.removeJobExecutions();
    }

    @Test
    void testH2ToMongoDbJob() throws Exception {
        Job job = jobLauncher.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(IMPORT_USER_JOB_NAME);
        JobExecution jobExecution = jobLauncher.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }
}
