import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand extends Command {

    public static void process(Catalog catalog, String format) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        try {
            configuration.setDirectoryForTemplateLoading(new File("./templates/"));
        } catch (IOException e) {
            System.err.println("IO Error when setting directory.");
            e.printStackTrace();
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);

        try {
            Template template = configuration.getTemplate("html-template.ftlh");
            Writer writer = new FileWriter("html-report.html");
            template.process(catalog, writer);
            writer.close();
        } catch(Exception e) {
            System.err.println("Error reading template.");
            e.printStackTrace();
        }



    }

}
