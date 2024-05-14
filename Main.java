import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();

        List<String> statements = List.of(
            "The cat sat on the mat.",
            "The clever robot moved the blue mat to the tall stand.",
            "Two students went to the bar late last night.",
            "Many students are downloading large tickets before the exams."
        );

        for (String statement : statements) {
            ParseTree parseTree = parser.parse(statement);
            // System.out.println("Parse Tree: " + parseTree);

			System.out.println("##################### TOKENS #####################");
            List<Token> tokens = parser.tokenize(statement);
            System.out.println("GENERATED TOKENS: " + tokens);

            System.out.println();

			System.out.println("##################### SYMBOL TABLE #####################");
            SymbolTable symbolTable = parser.createSymbolTable(tokens);
            System.out.println("SYMBOL TABLE: ");
			System.out.println(symbolTable);

            System.out.println();
        }
    }
}
