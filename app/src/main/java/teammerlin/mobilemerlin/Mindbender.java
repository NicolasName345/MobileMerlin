package teammerlin.mobilemerlin;

import java.util.Random;

public class Mindbender extends Minigame {

    private int length, guessCount, pointer, blinking, steady, timer;
    private int[] mystery, guess;

    private boolean chooseLength, chooseMystery, userInput, compareNumbers, sameGame;

    public Mindbender()
    {
        super();
        timer = 0;
        chooseLength = true;
        chooseMystery = false;
        userInput = false;
        compareNumbers = false;
        sameGame = false;
    }

    @Override
    public void update(Panel panel)
    {
        //Temp variables
        int button = buttonPressed(panel);

        if(chooseLength)
        {
            if(button != -1)
            {
                length = button;
                panel.clearLights();
                panel.setLight(10, 2);
                panel.playSound("tttCross");
                chooseLength = false;
                chooseMystery = true;
            }
        }
        else if(chooseMystery)
        {
            mystery = new int[length];
            guess = new int[length];
            for(int i = 0; i < length; i++)
            {
                mystery[i] = getRandomNumber();
            }
            guessCount = 0;
            pointer = 0;
            chooseMystery = false;
            userInput = true;
        }
        else if(userInput)
        {
            if(button != -1)
            {
                panel.playSound("mm"+(button-1));
                guess[pointer] = button;
                pointer++;
            }
            if(pointer == length)
            {
                pointer = 0;
                guessCount++;
                userInput = false;
                compareNumbers = true;
            }
        }
        else if(compareNumbers)
        {
            if(timer == 0)
            {
                blinking = 0;
                steady = 0;
                for (int i = 0; i < length; i++) {
                    //Blinking
                    if (guess[i] == mystery[i]) {
                        blinking++;
                    } else if (mysteryContains(guess[i])) {
                        steady++;
                    }
                }

                panel.clearLights();
                for (int i = 0; i < blinking; i++) {
                    panel.setLight(1 + i, 2);
                }
                for (int i = 0; i < steady; i++) {
                    panel.setLight(1 + i + blinking, 1);
                }

                timer = 1;
            }
            else
            {
                timer++;

                if(timer == 24) {
                    if (blinking == length)//Won
                    {
                        compareNumbers = false;
                        panel.playSound("win");
                    } else {
                        compareNumbers = false;
                        userInput = true;
                        panel.playSound("lose");
                    }
                    timer = 0;
                }
            }

        }

        if(sameGame)
        {
            if(guessCount > 9)
            {
                guessCount = 9;
            }

            panel.clearLights();
            panel.setLight(guessCount, 1);
            timer++;
            if(timer == 30)
            {
                timer = 0;
                chooseLength = false;
                chooseMystery = true;
                userInput = false;
                compareNumbers = false;
                sameGame = false;
                panel.clearLights();
                panel.setLight(10, 2);
            }
        }


    }

    @Override
    public void sameGame(Panel panel)
    {
        panel.playSound("select");
        if(!chooseLength)
        {
            sameGame = true;
        }
    }

    private int buttonPressed(Panel panel)
    {
        for(int i = 1; i < 10; i++)
        {
            if(panel.getButton(i))
            {
                return i;
            }
        }
        return -1;
    }

    private int getRandomNumber()
    {
        return (new Random().nextInt(9) + 1);
    }

    private boolean mysteryContains(int i)
    {
        for(int m : mystery)
        {
            if(m == i)
            {
                return true;
            }
        }
        return false;
    }

}
