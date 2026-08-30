import html.HtmlRunner;
import python.PythonRunner;

public class Main {

    public static void main(String[] args) {

        try {
            if (args.length < 2) {
                System.out.println("Usage: <language> <file> [output-dir] [templates-dir]");
                System.out.println("  Languages: html, python");
                return;
            }

            String lang = args[0];
            String path = args[1];

            if (lang.equals("html")) {
                HtmlRunner.run(path);
            } else if (lang.equals("python")) {
                String outputDir    = (args.length > 2) ? args[2] : "output";
                String templatesDir = (args.length > 3) ? args[3] : "templates";
                PythonRunner.run(path, outputDir, templatesDir);
            } else {
                System.out.println("Unknown language: " + lang);
                System.out.println("  Supported: html, python");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
