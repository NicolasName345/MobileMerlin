package teammerlin.mobilemerlin;

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
    private int timer;
    ArrayList<Integer> dealerdeck;
    ArrayList<Integer> userdeck;
    int userhand = 0;
    int dealerhand = 0;



    public Blackjack()
    {

        super();
        timer = 0;
        chips = 5;
        displaychips = true;
        newhand = false;
        dealerdeck = new ArrayList<Integer>();
        userdeck = new ArrayList<Integer>();
    }

    @Override
    public void update(Panel panel)
    {
        /*if(panel.getButton(1))
        {
            panel.setLight(1, 1);
        }*/

        buttonPresses(panel);
        ShuffleDecks();

        if(displaychips)
        {
            panel.setLight(chips, 1);
            if(timer == 23)
            {
                newhand = true;
                displaychips = false;
            }
        }

        if (newhand)
        {
            panel.clearLights();
            for(int i =0; i<=1; i++)
            {

                panel.setLight(userdeck.get(0), 2);
                panel.setLight(dealerdeck.get(0), 1);
                userhand = userhand + userdeck.get(0);
                dealerhand = dealerhand + dealerdeck.get(0);
            }

            newhand = false;
            secondhand = true;

        }

        if(secondhand && hit)
        {
            for(int i =0; i<=1; i++) {

                panel.setLight(userdeck.get(1), 2);
                panel.setLight(dealerdeck.get(1), 1);
                userhand = userhand + userdeck.get(1);
                dealerhand = dealerhand + dealerdeck.get(1);
            }
            hit = false;
        }












        timer++;
        if(timer == 24)
        {
            timer = 0;
        }
    }


    public void ShuffleDecks()
    {
        for (int i = 1; i <= 10; i++) {
            dealerdeck.add(i);
        }
        for (int i = 1; i <= 10; i++) {
            userdeck.add(i);
        }
        Collections.shuffle(dealerdeck);
        Collections.shuffle(userdeck);

        /*Random rn = new Random();
        int randomnumber = rn.nextInt(10)+1;
        return randomnumber;*/
    }





    private void buttonPresses(Panel panel)
    {
        //button 13 = hit me
        //button 14 = comp turn
        if(panel.getButton(13))
        {
            hit = true;
        }

        if(panel.getButton(14))
        {
            stand = true;
        }

    }

}
