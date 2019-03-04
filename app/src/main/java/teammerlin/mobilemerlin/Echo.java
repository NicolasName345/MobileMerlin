package teammerlin.mobilemerlin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Echo extends Minigame {
    private enum State {
        UserInput(),
        ChooseLength(),
        CheckWinner(),
        Playback();
    }

    private State state;
    private int length;
    private LinkedList<String> memory;
    private ArrayList<String> checkWin;
    private int playbackPlace;
    private int addNote;
    private Random random = new Random();
    boolean firstPlay = false;
    private int index;

    ArrayList<String> list = new ArrayList<String>();

    public Echo()
    {
        super();
        state = State.ChooseLength;
        length = 1;
        memory = new LinkedList<String>();
        checkWin = new ArrayList<String>();
        playbackPlace = 0;
        addNote = 1;
        list.add("mm0");
        list.add("mm1");
        list.add("mm2");
        list.add("mm3");
        list.add("mm4");
        list.add("mm5");
        list.add("mm6");
        list.add("mm7");
        list.add("mm8");
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
                for(addNote=1; addNote<=length; addNote++) //Create random tune
                {
                    memory.add(getRandomButton(list));
                }
            }
        }

        if(state==State.UserInput){
            //If Comp Turn is pressed
                if (panel.getButton(14)) {
                    panel.setLight(10, 0);//Turn off light 10
                    state = State.Playback;
                    panel.setTimer(24);
                    firstPlay = true;
                }
            if(firstPlay) {
                if(button != -1) {
                    checkWin.add("mm" + (button-1));
                    panel.setLight((button), 1);
                    state = State.CheckWinner;
                }
            }

        }

        if(state==State.CheckWinner){
                state = State.UserInput;
        }

        if(state==State.Playback && panel.timerReady()){
            if(playbackPlace != memory.size())
            {
                //Play sound
                panel.playSound(memory.get(playbackPlace));

                //Set lights
                panel.clearLights();
                panel.setLight(buttonToLight(memory.get(playbackPlace)), 1);

                //Move to next sound
                playbackPlace++;
            }
            else
            {
                playbackPlace = 0;
                panel.clearLights();
                panel.setLight(10, 2);
                state = State.UserInput;
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

    private String getRandomButton(List<String>list){ //choose a random button from the list
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private int buttonToLight(String s)
    {
        switch(s)
        {
            case "pause":
                return 0;
            case "mm0":
                return 1;
            case "mm1":
                return 2;
            case "mm2":
                return 3;
            case "mm3":
                return 4;
            case "mm4":
                return 5;
            case "mm5":
                return 6;
            case "mm6":
                return 7;
            case "mm7":
                return 8;
            case "mm8":
                return 9;
            case "mm9":
                return 10;
        }
        return 0;
    }

}
