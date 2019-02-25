package teammerlin.mobilemerlin;

import teammerlin.game.GameState;

public class Echo extends Minigame {
    private enum State {
        UserInput(),
        ChooseLength(),
        CheckWinner(),
        Playback();
    }

    private State state;
    private int length;
    public Echo()
    {
        super();
        state = State.ChooseLength;
        length = 1;
    }

    @Override
    public void update(Panel panel)
    {
        int button = gridButtons(panel);

        if(state==State.ChooseLength){
            if(button!= -1) { //if button pressed
                length = button;
                panel.playSound("tttCross");
                panel.clearLights();
                panel.setLight(10,2);
                state=State.UserInput;
            }
        }
        
    }

    private int gridButtons(Panel panel)
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

}
