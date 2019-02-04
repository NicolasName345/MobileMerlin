package teammerlin.mobilemerlin;

import java.util.Random;

public class MagicSquare extends Minigame {
    boolean showSquare;
    int timer;

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

            }

        }
    }

    @Override
    public void sameGame(Panel panel)
    {


    }

}
