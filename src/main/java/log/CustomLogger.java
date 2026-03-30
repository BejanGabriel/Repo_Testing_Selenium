package log;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger implements ConcurrentEventListener {

    protected final String dinamicUserPath;

    public CustomLogger() {
        //Questo costruttore viene automaticamente usato dal test runner, avendolo dichiarato come plugin.
        this.dinamicUserPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "customLog";
        // Creazione della directory e successivo controllo della sua esistenza, nel caso non esista la si crea con .mkdirs()
        File directory = new File(dinamicUserPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepFinished.class, stepHandler);
    }

    private EventHandler<TestStepFinished> stepHandler = event -> {


        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            String stepKey = testStep.getStep().getKeyword();
            String stepText = testStep.getStep().getText();

            String stepReport = "[SUCCESS] " + stepKey + " " + stepText;

            if (event.getResult().getStatus().is(Status.FAILED)) {
                Throwable error = event.getResult().getError();
                stepReport += "\n[FAILED]: " + stepKey + " " + stepText + "\n" + error.getMessage();
            }
            writeToFile(stepReport);
        }

    };

    private void writeToFile(String output) {
        // parte del nome del file
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        // creare la directory dinamica
        String dinamicDirectoryPath = dinamicUserPath;
        // creare il file dinamico
        File file = new File(dinamicDirectoryPath, "log_" + timeStamp + ".txt");

    // scrivo nel file
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            pw.println(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}