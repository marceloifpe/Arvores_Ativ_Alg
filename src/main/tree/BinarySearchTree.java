package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree {

    private Node root;

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursivamente(root, valor);
    }

    private boolean buscaElementoRecursivamente(Node root, int valor) {
        if (root == null)
            return false;

        if (valor == root.value)
            return true;

        if (valor < root.value)
            return buscaElementoRecursivamente(root.left, valor);
        else
            return buscaElementoRecursivamente(root.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");

        Node currentNode = root;
        while (currentNode.left != null)
            currentNode = currentNode.left;

        return currentNode.value;
    }

    @Override
    public int maximo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");

        Node currentNode = root;
        while (currentNode.right != null)
            currentNode = currentNode.right;

        return currentNode.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = inserirRecursivamente(root, valor);
    }

    private Node inserirRecursivamente(Node root, int valor) {
        if (root == null) {
            return new Node(valor);
        }

        if (valor < root.value) {
            root.left = inserirRecursivamente(root.left, valor);
        } else if (valor > root.value) {
            root.right = inserirRecursivamente(root.right, valor);
        }

        return root;
    }

    @Override
    public void remove(int valor) {
        root = removerRecursivamente(root, valor);
    }

    private Node removerRecursivamente(Node root, int valor) {
        if (root == null)
            return root;

        if (valor < root.value) {
            root.left = removerRecursivamente(root.left, valor);
        } else if (valor > root.value) {
            root.right = removerRecursivamente(root.right, valor);
        } else {
            // Nó com apenas um filho ou sem filhos
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Nó com dois filhos: obter o menor nó da subárvore direita
            root.value = minimoSubArvoreDireita(root.right);

            // Remover o menor nó da subárvore direita
            root.right = removerRecursivamente(root.right, root.value);
        }
        return root;
    }

    private int minimoSubArvoreDireita(Node node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> list = new ArrayList<>();
        preOrdemRecursivamente(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void preOrdemRecursivamente(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.value);
            preOrdemRecursivamente(root.left, list);
            preOrdemRecursivamente(root.right, list);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> list = new ArrayList<>();
        emOrdemRecursivamente(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdemRecursivamente(Node root, List<Integer> list) {
        if (root != null) {
            emOrdemRecursivamente(root.left, list);
            list.add(root.value);
            emOrdemRecursivamente(root.right, list);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> list = new ArrayList<>();
        posOrdemRecursivamente(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdemRecursivamente(Node root, List<Integer> list) {
        if (root != null) {
            posOrdemRecursivamente(root.left, list);
            posOrdemRecursivamente(root.right, list);
            list.add(root.value);
        }
    }
}
