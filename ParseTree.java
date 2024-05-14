import java.util.List;

public class ParseTree {
    private Node root;

    public ParseTree() {
        root = null;
    }

    public void buildTree(List<Token> tokens) {
        root = buildTreeRecursive(tokens, 0);
    }

    private Node buildTreeRecursive(List<Token> tokens, int start) {
        if (start >= tokens.size()) {
            return null;
        }

        Token token = tokens.get(start);
        Node node = new Node(token);

        int end = findEndOfSubtree(tokens, start);

		node.setLeft(buildTreeRecursive(tokens, start + 1));
		node.setRight(buildTreeRecursive(tokens, end + 1));

        return node;
    }

    private int findEndOfSubtree(List<Token> tokens, int start) {
        int end = start;
        int depth = 0;

        while (end < tokens.size()) {
            if (tokens.get(end).getType() == TokenType.VERB) {
                depth++;
            } else if (tokens.get(end).getType() == TokenType.NOUN) {
                depth--;
            }

            if (depth == 0) {
                break;
            }

            end++;
        }

        return end;
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : "";
    }
}

class Node {
    private Token token;
    private Node left;
    private Node right;

    public Node(Token token) {
        this.token = token;
        this.left = null;
        this.right = null;
    }

	public Token getToken() {
        return token;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(token).append(" ");
        if (left != null) {
            sb.append(left.toString());
        }
        if (right != null) {
            sb.append(" ").append(right.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
