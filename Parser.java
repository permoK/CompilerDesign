import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {
	private static final List<String> ARTICLES = List.of("The", "the");
	private static final List<String> ADJECTIVES = List.of("clever", "blue", "Many", "Two", "late", "last", "large", "tall");
	private static final List<String> NOUNS = List.of("cat", "mat", "robot", "students", "bar", "night", "tickets", "exams", "stand");
	private static final List<String> VERBS = List.of("sat", "moved", "went", "are", "downloading");
	private static final List<String> PREPOSITIONS = List.of("on", "to", "before");



    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, " ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals(" ")) {
                continue;
            }

            TokenType type = getTokenType(token);
            tokens.add(new Token(token, type));
        }

        return tokens;
    }

    private TokenType getTokenType(String token) {
        if (NOUNS.contains(token)) {
            return TokenType.NOUN;
        } else if (VERBS.contains(token)) {
            return TokenType.VERB;
        } else if (ADJECTIVES.contains(token)) {
            return TokenType.ADJECTIVE;
        }
		else if (PREPOSITIONS.contains(token)) {
			return TokenType.PREPOSITIONS;
		}
		else if (ARTICLES.contains(token)) {
			return TokenType.ARTICLE; }

        return TokenType.UNKNOWN;
    }

    private boolean isNoun(String token) {
        return NOUNS.contains(token);
    }

    private boolean isVerb(String token) {
        return VERBS.contains(token);
    }

    private boolean isAdjective(String token) {
        return ADJECTIVES.contains(token);
    }

	private boolean isPreposition(String token) {
		return PREPOSITIONS.contains(token);
	}

	private boolean isArticle(String token) {
		return ARTICLES.contains(token);
	}

    public ParseTree parse(String input) {
        List<Token> tokens = tokenize(input);
        ParseTree parseTree = new ParseTree();
        parseTree.buildTree(tokens);
        return parseTree;
    }

    public SymbolTable createSymbolTable(List<Token> tokens) {
        SymbolTable symbolTable = new SymbolTable();
        for (Token token : tokens) {
            SymbolType type = getSymbolType(token.getType());
            symbolTable.addSymbol(token.getValue(), new Symbol(token.getValue(), type));
        }
        return symbolTable;
    }

    private SymbolType getSymbolType(TokenType tokenType) {
        switch (tokenType) {
            case NOUN:
                return SymbolType.NOUN;
            case VERB:
                return SymbolType.VERB;
            case ADJECTIVE:
                return SymbolType.ADJECTIVE;
			case PREPOSITIONS:
				return SymbolType.PREPOSITIONS;
			case ARTICLE:
				return SymbolType.ARTICLE;
            default:
                return SymbolType.UNKNOWN;
        }
    }
}
