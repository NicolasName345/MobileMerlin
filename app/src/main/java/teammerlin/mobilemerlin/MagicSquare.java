package teammerlin.mobilemerlin;

import java.util.Random;

public class MagicSquare extends Minigame {

    private enum State {
        ShowSquare(),
        RandomLights(),
        UserInput(),
        CheckLight(),
        Finish();
    }

    State state;
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
        state = State.ShowSquare;
        timer = 0;
    }

    @Override
    public void update(Panel panel)
    {
        if (state == State.ShowSquare)
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
                state = State.RandomLights;
            }

        }

        if (state == State.RandomLights)
        {
            panel.clearLights();
            for (int i=0; i<random.length;i++)
            {
                random[i] = getRandomNum();
                panel.setLight(random[i],2);
            }
            state = State.UserInput;
        }

        if (state == State.UserInput)
        {
            panel.setLight(0,0);
            panel.setLight(10,0);
            if (panel.getButton(1))
            {
                for (int i =0; i<buttonOne.length;i++)
                    panel.switchLight(buttonOne[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(2))
            {
                for (int i =0; i<buttonTwo.length;i++)
                    panel.switchLight(buttonTwo[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(3))
            {
                for (int i =0; i<buttonThree.length;i++)
                    panel.switchLight(buttonThree[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(4))
            {
                for (int i =0; i<buttonFour.length;i++)
                    panel.switchLight(buttonFour[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(5))
            {
                for (int i =0; i<buttonFive.length;i++)
                    panel.switchLight(buttonFive[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(6))
            {
                for (int i =0; i<buttonSix.length;i++)
                    panel.switchLight(buttonSix[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(7))
            {
                for (int i =0; i<buttonSeven.length;i++)
                    panel.switchLight(buttonSeven[i]);

                state = State.CheckLight;
            }

            if (panel.getButton(8))
            {
                for (int i =0; i<buttonEight.length;i++)
                    panel.switchLight(buttonEight[i]);

                state = State.CheckLight;
            }


            if (panel.getButton(9))
            {
                for (int i =0; i<buttonNine.length;i++)
                    panel.switchLight(buttonNine[i]);

                state = State.CheckLight;
            }

            }

            if (state == State.CheckLight)
            {
                if (panel.isMagicSquare())
                {
                    state = State.Finish;
                }
                else
                    state = State.UserInput;

            }



            if(state == State.Finish)
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
