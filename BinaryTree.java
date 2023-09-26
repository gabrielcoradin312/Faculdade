import java.util.Scanner;

public class BinaryTree {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRec(current.left, value);
        } else if (value > current.value) {
            current.right = insertRec(current.right, value);
        }

        return current;
    }

    public void preOrder() {
        preOrderRec(root);
    }

    private void preOrderRec(Node current) {
        if (current != null) {
            System.out.println(current.value);
            preOrderRec(current.left);
            preOrderRec(current.right);
        }
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node current) {
        if (current != null) {
            inOrderRec(current.left);
            System.out.println(current.value);
            inOrderRec(current.right);
        }
    }

    public void postOrder() {
        postOrderRec(root);
    }

    private void postOrderRec(Node current) {
        if (current != null) {
            postOrderRec(current.left);
            postOrderRec(current.right);
            System.out.println(current.value);
        }
    }

    public void removeMax() {
        root = removeMaxRec(root);
    }

    private Node removeMaxRec(Node current) {
        if (current == null) return null;
        if (current.right == null) return current.left;
        current.right = removeMaxRec(current.right);
        return current;
    }

    public void removeMin() {
        root = removeMinRec(root);
    }

    private Node removeMinRec(Node current) {
        if (current == null) return null;
        if (current.left == null) return current.right;
        current.left = removeMinRec(current.left);
        return current;
    }

    public void remove(int value) {
        root = removeRec(root, value);
    }

    private Node removeRec(Node current, int value) {
        if (current == null) return null;

        if (value < current.value) {
            current.left = removeRec(current.left, value);
        } else if (value > current.value) {
            current.right = removeRec(current.right, value);
        } else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            current.value = minValue(current.right);
            current.right = removeMinRec(current.right);
        }
        return current;
    }

    private int minValue(Node current) {
        int minv = current.value;
        while (current.left != null) {
            minv = current.left.value;
            current = current.left;
        }
        return minv;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner sc = new Scanner(System.in);

        System.out.println("Arvore binaria");

        while (true) {
            System.out.println("\nSelecione uma acao:");
            System.out.println("1: Inserir um numero");
            System.out.println("2: Mostrar inOrdem");
            System.out.println("3: Mostrar preOrdem");
            System.out.println("4: Mostrar posOrdem");
            System.out.println("5: Remover um numero");
            System.out.println("6: Remover o maior numero");
            System.out.println("7: Remover o menor numero");
            System.out.println("8: Sair");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Informe o numero:");
                    tree.insert(sc.nextInt());
                    break;
                case 2:
                    tree.inOrder();
                    break;
                case 3:
                    tree.preOrder();
                    break;
                case 4:
                    tree.postOrder();
                    break;
                case 5:
                    System.out.println("Informe o numero a remover:");
                    tree.remove(sc.nextInt());
                    break;
                case 6:
                    tree.removeMax();
                    break;
                case 7:
                    tree.removeMin();
                    break;
                case 8:
                    sc.close();
                    return;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}
