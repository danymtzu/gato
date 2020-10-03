/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itch2.ad20.ia.gatoa;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Search {

    public static void main(String[] args) {
        Node ini = new Node();
        /*System.out.println(ini);
        ini.generateChildren();
        for (int i = 0; i < ini.getChildrenSize(); i++) {
            System.out.println(ini.getChild(i));
        }*/
        Node fin = depthFS(ini);
        System.out.println(fin);
    }

    //TAREA: Busqueda primero por profundidad
    public static Node depthFS(Node n) {
        //Regresa el primer nodo final que encuentre.   
        ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < children.size(); i++) {
            visited.set(i, true);
            if (n.getChild(i).isFinal()) {
                n.getChild(i);
                visited.set(i + 1, false);
            }
            n.generateChildren();
            children.add(n.getChild(i));
        }

        return n;
    }
}

/**
 * Clase nodo para el juego de gato.
 *
 * @author Carlos
 */
class Node {

    public static final int BOARD_SIZE = 9;

    private byte[] board;
    private boolean turn;
    private ArrayList<Node> children;

    /**
     * Crear nodo inicial
     */
    public Node() {
        this.board = new byte[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board[i] = 0;
        }
        this.turn = false;
        this.children = new ArrayList<>();
    }

    /**
     * Crear copia de nodo
     *
     * @param n Nodo a copiar
     */
    private Node(Node n) {
        this.board = new byte[BOARD_SIZE];
        System.arraycopy(n.board, 0, this.board, 0, BOARD_SIZE);
        this.turn = n.turn;
        this.children = new ArrayList<>();
    }

    /**
     * Obtener cantidad de nodos hijos
     *
     * @return Cantidad de nodos hijos
     */
    public int getChildrenSize() {
        return this.children.size();
    }

    /**
     * Obtener un nodo hijo.
     *
     * @param index Índice del nodo que se desea obtener
     * @return Nodo requerido. Regresa null si el índice proporcionado es
     * inválido.
     */
    public Node getChild(int index) {
        if (index < 0 || index >= this.children.size()) {
            return null;
        }

        return this.children.get(index);
    }

    public void generateChildren() {
        Node tmp;

        this.children.clear();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (this.board[i] == 0) {
                tmp = new Node(this);
                tmp.board[i] = (byte) (this.turn ? 2 : 1);
                tmp.turn = !this.turn;
                this.children.add(tmp);
            }
        }
    }

    public boolean isFinal() {
        for (int i = 0; i < 3; i++) {
            //Checar filas
            if (this.board[i * 3] == this.board[i * 3 + 1]
                    && this.board[i * 3] == this.board[i * 3 + 2]
                    && this.board[i * 3] != 0) {
                return true;
            }

            //Checar columnas
            if (this.board[i] == this.board[i + 3]
                    && this.board[i] == this.board[i + 6]
                    && this.board[i] != 0) {
                return true;
            }
        }
        //Diagonal principal
        if (this.board[0] == this.board[4]
                && this.board[0] == this.board[8]
                && this.board[0] != 0) {
            return true;
        }
        //Diagonal invertida
        return this.board[2] == this.board[4]
                && this.board[2] == this.board[6]
                && this.board[2] != 0;
    }

    @Override
    public String toString() {
        // [X,O,O,X, , ,X, , ] Turno: O
        String s = "[";
        for (int i = 0; i < BOARD_SIZE; i++) {
            s += this.board[i] == 0 ? " "
                    : this.board[i] == 1 ? "X" : "O";
            s += i < (BOARD_SIZE - 1) ? "," : "";
        }
        s += "] Turno: ";
        s += this.turn ? "O" : "X";
        return s;
    }

}
