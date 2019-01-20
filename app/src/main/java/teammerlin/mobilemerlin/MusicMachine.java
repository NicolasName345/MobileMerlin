package teammerlin.mobilemerlin;

import java.util.LinkedList;

public class MusicMachine extends Minigame {

    private LinkedList<String> memory;
    private boolean playback;
    private int playbackPlace;
    private int timer;

    public MusicMachine()
    {
        super();
        memory = new LinkedList<String>();
        playback = false;
        playbackPlace = 0;
        timer = 0;
    }

    @Override
    public void update(Panel panel)
    {
        buttonPresses(panel);

        if(panel.getButton(14))
        {
            playback = true;
        }

        if(playback && timer == 23)
        {
            if(!memory.isEmpty())
            {
                if(memory.get(playbackPlace) != "pause")
                {
                    panel.playSound(memory.get(playbackPlace));
                }
                panel.clearLights();
                panel.setLight(buttonToLight(memory.get(playbackPlace)), 1);

                playbackPlace++;

                if(playbackPlace == memory.size())
                {
                    playbackPlace = 0;
                    playback = false;
                    panel.clearLights();
                    panel.setLight(10, 2);
                }
            }
        }

        timer++;
        if(timer == 24)
        {
            timer = 0;
        }
    }

    private void buttonPresses(Panel panel)
    {
        if(panel.getButton(0))
        {
            panel.playSound("mm9");
            memory.add("pause");
        }
        if(panel.getButton(1))
        {
            panel.playSound("mm0");
            memory.add("mm0");
        }
        if(panel.getButton(2))
        {
            panel.playSound("mm1");
            memory.add("mm1");
        }
        if(panel.getButton(3))
        {
            panel.playSound("mm2");
            memory.add("mm2");
        }
        if(panel.getButton(4))
        {
            panel.playSound("mm3");
            memory.add("mm3");
        }
        if(panel.getButton(5))
        {
            panel.playSound("mm4");
            memory.add("mm4");
        }
        if(panel.getButton(6))
        {
            panel.playSound("mm5");
            memory.add("mm5");
        }
        if(panel.getButton(7))
        {
            panel.playSound("mm6");
            memory.add("mm6");
        }
        if(panel.getButton(8))
        {
            panel.playSound("mm7");
            memory.add("mm7");
        }
        if(panel.getButton(9))
        {
            panel.playSound("mm8");
            memory.add("mm8");
        }
        if(panel.getButton(10))
        {
            panel.playSound("mm9");
            memory.add("mm9");
        }
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
