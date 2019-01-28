package teammerlin.mobilemerlin;

import java.util.ArrayList;
import java.util.Random;

import teammerlin.game.GameState;

public class Blackjack extends Minigame {

    int chips;
    boolean newhand;
    boolean displaychips;
    private int timer;
    ArrayList<Integer> dealerhand;
    ArrayList<Integer> userhand;


    public Blackjack()
    {

        super();
        timer = 0;
        chips = 5;
        displaychips = true;
        newhand = false;
        dealerhand = new ArrayList<Integer>();
        userhand = new ArrayList<Integer>();
    }

    @Override
    public void update(Panel panel)
    {
        /*if(panel.getButton(1))
        {
            panel.setLight(1, 1);
        }*/
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
            dealerhand.add(Randomnumber());
            userhand.add(Randomnumber());
            panel.clearLights();
            for(int i : dealerhand)
            {
                panel.setLight(i, 1);
            }
            for(int i : userhand)
            {
                panel.setLight(i, 2);
            }
            newhand = false; 
        }










        timer++;
        if(timer == 24)
        {
            timer = 0;
        }
    }


    public int Randomnumber()
    {
        Random rn = new Random();
        int randomnumber = rn.nextInt(10)+1;
        return randomnumber;
    }

}
