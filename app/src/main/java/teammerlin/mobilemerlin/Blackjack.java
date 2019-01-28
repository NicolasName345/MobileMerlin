package teammerlin.mobilemerlin;

import teammerlin.game.GameState;

public class Blackjack extends Minigame {

    public Blackjack()
    {

        super();
    }

    @Override
    public void update(Panel panel)
    {
        if(panel.getButton(1))
        {
            panel.setLight(1, 1);
        }
    }

}
