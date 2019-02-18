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

        if(userhand>13)
        {
            bust = true;
            userhand = 0;
        }

        if(dealerhand>13)
        {
            merlinbust = true;
            dealerhand = 0;
        }

        if(bust)
        {
            panel.playSound("lose");


                bust = false;

        }

        if(merlinbust)
        {
            panel.playSound("win");
            merlinbust = false;
        }

        buttonPresses(panel);



        if(displaychips)
        {
            panel.setLight(chips, 1);
            if(panel.timerReady())
            {
                newhand = true;
                displaychips = false;
            }
        }

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

        if(hit)
        {
            panel.setLight(deck.get(i), 2);
            userhand = userhand + deck.get(i);

            panel.setLight(deck.get(i+1), 1);
            dealerhand = dealerhand + deck.get(i+1);

            i++;


            hit = false;
            //ShuffleDecks();
        }

        if(stand)
        {
            
            if(dealerhand>userhand)
            {

                    panel.playSound("lose");
                    //panel.clearLights();
                    dealerhand = 0;
                    userhand = 0;


            }
            else if(dealerhand<userhand)
            {

                if(panel.timerReady())
                {
                    panel.setLight(deck.get(i), 1);
                    dealerhand = dealerhand + i;
                    i++;

                }
            }

        }












    }


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

        for(i=0; i <=10; i++)
        {
            deck.add(i);
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
