package teammerlin.mobilemerlin;

import teammerlin.game.GameState;

public class Echo extends Minigame {

    public Echo()
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
