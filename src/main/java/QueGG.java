
import evalution.Evalution;
import evalution.QALD;
import java.io.File;
import java.util.List;
import java.util.Set;
import lombok.NoArgsConstructor;
import parser.Grammar;
import parser.GrammarFactory;
import parser.GrammarRule;
import utils.CsvFile;
import utils.TripleProcess;

@NoArgsConstructor
public class QueGG {

    private static String grammarFileName = "grammarFiles/grammar_FULL_DATASET_EN.json";
    private static String inputDir = "/home/elahi/A-Grammar/grammar-rules/resources/";
    private static String qaldFileName = "";
    private static Boolean entityRetriveOnline = true;
    private static Integer numberOfEntities = -1;

    public static void main(String[] args) throws Exception {
        System.out.println("Grammar Parser!!!");
        args = new String[]{"en", inputDir};

        if (args.length < 2) {
            System.err.printf("Too few parameters (%s/%s)", args.length);
            throw new IllegalArgumentException(String.format("Too few parameters (%s/%s)", args.length));
        } else if (args.length == 2) {
            String language = args[0];
            Grammar grammar = new GrammarFactory(new File(grammarFileName), entityRetriveOnline, numberOfEntities, language).getGrammar();
            //String sentence = args[1];
            File[] files = new File(inputDir).listFiles();
            for (File file : files) {
                if (file.getName().contains("input-")) {
                    String outputFileName = inputDir + file.getName().replace("input-", "output-");
                    CsvFile csvFile = new CsvFile(file);
                    CsvFile csvOutputFile = new CsvFile(new File(outputFileName));
                    List<String[]> inputRows = csvFile.getRows(file);
                    List<String[]> outputRows = csvFile.getRows(file);
                    String sparql = null;
                    Integer index = 0;
                    for (String[] row : inputRows) {
                        try {
                            String id = row[0];

                            String sentence = row[1];
                            //sentence="What is the highest mountain in Australia?";
                            /*if (!sentence.contains("How ")) {
                                continue;
                            }*/
                            System.out.println(id + ": " + sentence );
                            sparql = grammar.parser(sentence);
                            System.out.println(" sparql:" + sparql);
                            //outputRows.add(new String[]{id, sentence});
                            if (index >= 51) {
                                break;
                            }
                            index = index + 1;
                           
                        } catch (Exception e) {
                            System.err.printf("%s: %s%n", e.getClass().getSimpleName(), e.getMessage());
                        }
                    }
                    csvOutputFile.writeToCSV(outputRows);
                }

            }

        }

    }

}
