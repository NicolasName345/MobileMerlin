package teammerlin.mobilemerlin;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import teammerlin.game.GameState;

public class Blackjack extends Minigame {

    int chips;
    boolean newhand;
    boolean displaychips;
    boolean secondhand;
    boolean hit;
    boolean stand;
    boolean bust;
    boolean merlinbust;
    private int timer;
    ArrayList<Integer> deck;

    int userhand = 0;
    int dealerhand = 0;
    int i = 0;



    public Blackjack()
    {

        super();

        chips = 5;
        displaychips = true;
        newhand = false;
        deck = new ArrayList<Integer>();

        bust = false;
        merlinbust = false;
    }

    @Override
    public void update(Panel panel)
    {


        //player busts
        if(userhand>13)
        {
            panel.playSound("lose");
            stand = false;
            userhand = 0;

        }

        //dealer busts
        if(dealerhand>13)
        {

            panel.playSound("win");
            //panel.clearLights();
            chips++;
            stand = false;
            dealerhand = 0;

        }


        //player hits blackjack
        if(userhand==13)
        {
            panel.playSound("win");
            stand = false;
            userhand=0;

            chips++;
        }


        //dealer hits blackjack
        if (dealerhand ==13)
        {
            panel.playSound("lose");
            stand = false;
            dealerhand = 0;

        }







        buttonPresses(panel);


        //displays chips at start of game
        if(displaychips)
        {
            panel.setLight(chips, 1);
            if(panel.timerReady())
            {
                newhand = true;
                displaychips = false;
            }
        }


        //deal first hand
        if (newhand)
        {
            panel.clearLights();
            ShuffleDecks();
            panel.setLight(deck.get(i), 2);
            userhand = userhand + deck.get(i);

            panel.setLight(deck.get(i+1), 1);
            dealerhand = dealerhand + deck.get(i+1);

            i++;

            newhand = false;
        }


        //player hits
        if(hit)
        {


            panel.setLight(deck.get(i), 2);
            userhand = userhand + deck.get(i);
            i++;
            hit = false;

        }


        //player stands, if merlin <10 he hits, if merlin is >=10 he stands and then compares his hand to player
        if(stand)
        {
            if(dealerhand<10)
            {
                if(panel.timerReady())
                {
                    panel.setLight(deck.get(i), 1);
                    dealerhand = dealerhand + i;
                    i++;

                }
            }
            else if (dealerhand>=10)
            {
                if(dealerhand<userhand)
                {
                    panel.playSound("win");
                    //dealerhand=0;
                    //userhand=0;
                    stand = false;
                    chips++;
                }

                if(dealerhand>userhand)
                {
                    panel.playSound("lose");
                    //dealerhand = 0;
                    //userhand = 0;
                    stand = false;

                }
            }






        }












    }

    //"shuffels deck" ensuring random numbers drawn each time
    public void ShuffleDecks()
    {
        /*deck.add(1);
        deck.add(2);
        deck.add(3);
        deck.add(4);
        deck.add(5);
        deck.add(6);
        deck.add(7);
        deck.add(8);
        deck.add(9);
        deck.add(10);*/

        for(int j=0; j<=10; j++)
        {
            deck.add(j);
        }

        Collections.shuffle(deck);


    }







    private void buttonPresses(Panel panel)
    {
        //button 13 = hit me
        //button 14 = comp turn
        if(panel.getButton(13))
        {
            hit = true;
            i++;
        }

        if(panel.getButton(14))
        {
            stand = true;
        }

    }

}
