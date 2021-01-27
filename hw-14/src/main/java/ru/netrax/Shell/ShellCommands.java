package ru.netrax.Shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final JobLauncher jobLauncher;

    private final Job migrateH2ToMongoDb;

    @SneakyThrows
    @ShellMethod(value = "StartMigrationFromH2ToMongoDb", key = "s")
    public void startMigrationFromMongoToMySql() {
        jobLauncher.run(migrateH2ToMongoDb, new JobParameters());
    }
}
