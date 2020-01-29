package ru.geekbrains.ticktacktoe;

import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char [][] field;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int for_victory = 4; // переменная кол-ва фишек для победы - по условиям задачи N 3

    private static void initField(){
        fieldSizeY = 5; // по условиям задачи N 3
        fieldSizeX = 5;  // по условиям задачи N 3
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++){
            for (int j = 0; j < fieldSizeX; j++){
                field [i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printField(){
        for (int i = 0; i < fieldSizeY; i++){
            System.out.print("|");
            for (int j = 0; j < fieldSizeX; j++){
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }

    private static boolean isCellEmpty (int x, int y){
        return field[y][x] ==DOT_EMPTY;
    }

    private static void humanTurn(){
        int x, y;
        do{
            System.out.print("Enter coordinates (x y) 1 to 5 >> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static void aiTurn(){
        int x, y;
        do{
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    private static boolean isDraw(){
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    // ПРоверка победы по диагоналям:
    private static boolean checkDiag(char c) {
        boolean diag1 = true;
        boolean diag2 = true;
        for (int i = 0; i < for_victory; i++) {
            diag1 = diag1 && field[i][i] == c;
            diag2 = diag2 && field [for_victory - i - 1][i] == c;
        }
        if (diag1){
            return true;
        } else if (diag2) {
            return true;
        } else {
            return false;
        }
    }
    // Проверка победы по вертикалям и горизонталям:
    private static boolean checkLine (char c) {
        boolean line = true;
        boolean column = true;
        for (int i = 0; i < for_victory; i++) {
            for (int j = 0; j < for_victory; j++) {
                column = column && field[i][j] == c;
                line = line && field[j][i] == c;
            }
            if (column){
                return true;
            }
            else if (line) {
                return true;
            } else {
            }
        }
        return false;
    }
    // Проверка победы:
        private static boolean checkWin(char c){
            int x, y;
            for (y = 0; y < for_victory; y++){
                for (x = 0; x < for_victory; x++){
                    if (checkDiag(c)) {
                        return true;
                    }
                    if (checkLine(c)){
                        return true;
                    }
                }
            }
            return false;
        }


    public static void main(String[] args) {
	    initField();
	    printField();
	    while(true){
	        humanTurn();
	        printField();
	        if (checkWin(DOT_HUMAN)){
                System.out.println("Human win!");
                break;
            }
	        if (isDraw()){
                System.out.println("Draw!");
                break;
            }
	        aiTurn();
	        printField();
	        if (checkWin(DOT_AI)){
                System.out.println("Computer win!");
                break;
            }
	        if (isDraw()){
                System.out.println("Draw!");
                break;
            }
        }
    }
}
