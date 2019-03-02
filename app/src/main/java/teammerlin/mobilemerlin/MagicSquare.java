package teammerlin.mobilemerlin;

import java.util.Random;

public class MagicSquare extends Minigame {
    boolean showSquare,randomLights,userInput,finish;
    int timer;
    int[] random = new int[4];
    int[] magicSquare = {1,2,3,4,6,7,8,9};
    int[] buttonOne = {1,2,4,5};
    int[] buttonTwo = {1,2,3};
    int[] buttonThree = {2,3,5,6};
    int[] buttonFour = {1,4,7};
    int[] buttonFive = {2,4,5,6,8};
    int[] buttonSix = {3,6,9};
    int[] buttonSeven = {4,5,7,8};
    int[] buttonEight = {7,8,9};
    int[] buttonNine = {5,6,8,9};

    public MagicSquare()
    {
        super();
        showSquare = true;
        timer = 0;
    }

    @Override
    public void update(Panel panel)
    {
        if (showSquare)
        {
            panel.clearLights();
            panel.setLight(1,2);
            panel.setLight(2,2);
            panel.setLight(3,2);
            panel.setLight(4,2);
            panel.setLight(6,2);
            panel.setLight(7,2);
            panel.setLight(8,2);
            panel.setLight(9,2);

            timer++;
            if (timer == 30)
            {
                showSquare = false;
                randomLights = true;
            }

        }

        if (randomLights)
        {
            panel.clearLights();
            for (int i=0; i<random.length;i++)
            {
                random[i] = getRandomNum();
                panel.setLight(random[i],2);
            }
            randomLights = false;
            userInput = true;
        }

        if (userInput)
        {
            panel.setLight(0,0);
            panel.setLight(10,0);
            if (panel.getButton(1))
            {
                for (int i =0; i<buttonOne.length;i++)
                    panel.switchLight(buttonOne[i]);
            }

            if (panel.getButton(2))
            {
                for (int i =0; i<buttonTwo.length;i++)
                    panel.switchLight(buttonTwo[i]);
            }

            if (panel.getButton(3))
            {
                for (int i =0; i<buttonThree.length;i++)
                    panel.switchLight(buttonThree[i]);
            }

            if (panel.getButton(4))
            {
                for (int i =0; i<buttonFour.length;i++)
                    panel.switchLight(buttonFour[i]);
            }

            if (panel.getButton(5))
            {
                for (int i =0; i<buttonFive.length;i++)
                    panel.switchLight(buttonFive[i]);
            }

            if (panel.getButton(6))
            {
                for (int i =0; i<buttonSix.length;i++)
                    panel.switchLight(buttonSix[i]);
            }

            if (panel.getButton(7))
            {
                for (int i =0; i<buttonSeven.length;i++)
                    panel.switchLight(buttonSeven[i]);
            }

            if (panel.getButton(8))
            {
                for (int i =0; i<buttonEight.length;i++)
                    panel.switchLight(buttonEight[i]);
            }


            if (panel.getButton(9))
            {
                for (int i =0; i<buttonNine.length;i++)
                    panel.switchLight(buttonNine[i]);
            }

            if (panel.getButton(0))
            {
                for (int i=0; i<magicSquare.length; i++)
                {
                    panel.setLight(magicSquare[i],2);
                }
            }

            if (panel.isMagicSquare())
            {
                userInput = false;
                finish = true;
            }
            }

            if (finish)
            {
                for (int i = 0; i<magicSquare.length; i++)
                {
                    panel.setLight(magicSquare[i],1);
                }
            }
        }


    public int getRandomNum()
    {
        return (new Random().nextInt(9));
    }

    @Override
    public void sameGame(Panel panel)
    {


    }

}
