package com.emp.service;

import com.emp.model.Emp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class EmpReportsImpl implements EmpReports {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpReportsImpl.class);

    private static void writeToFile(File file, List<String> results) {
        try (FileWriter writer = new FileWriter(file)) {
            for (String result : results) {
                writer.write(result + System.lineSeparator());
            }
        } catch (IOException e) {
            LOGGER.error("Serious error while generating employee reports", e);
        }
    }

    @Override
    public File generateReport(List<Emp> empList, String fileName) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        File outputFile = new File(fileName);
        List<Future<String>> futures = new ArrayList<>();

        try {
            for (Emp emp : empList) {
                Callable<String> callable = () -> {
                    return
                            emp.getEmpBasic().getFirstName() + "," + emp.getEmpBasic().getLastName();
                };
                futures.add(executor.submit(callable));
            }

            List<String> results = new ArrayList<>();
            for (Future<String> future : futures) {
                try {
                    results.add(future.get());
                } catch (InterruptedException | ExecutionException e) {

                    LOGGER.error("Serious error while generating employee reports", e);

                    for (Future<String> f : futures) {
                        f.cancel(true);
                    }
                    executor.shutdownNow();
                    return outputFile;
                }


            }

            writeToFile(outputFile, results);
        } catch (Exception e) {
            LOGGER.error("Serious error while generating employee reports", e);

        } finally {
            executor.shutdown(); // Ensure executor shutdown
        }
        return outputFile;
    }
}
