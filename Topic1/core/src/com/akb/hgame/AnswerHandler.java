package com.akb.hgame;


public class AnswerHandler {

    private int[] inputOrder;
    private int score = 0;


    public AnswerHandler() {
        inputOrder = new int[5];

    }

    public int[] Mark(int[] clickOrder,Happen[] ev,int[] posOfEach) {
        System.out.println("Mark is GO!");

        for (int s = 0; s < 5; s++) 	{

            inputOrder[s] = ev[clickOrder[s]].corpos;



            System.out.println("finord:" + inputOrder[s]);
        }
        return inputOrder;
    }

    public boolean[] GenerateRings(int[] finord) {
        boolean[] rings = new boolean[4];
        for (int i = 1; i < 5; i ++) 	{
            if ((finord[i] - finord[(i-1)]) == 1) 	{
                score++;
                rings[i-1] = true;
                System.out.println("Rings" + i + " is true");

            }
        }
        return rings;

    }

    public int getScore(int[] finord) {


        return score;

    }



}


