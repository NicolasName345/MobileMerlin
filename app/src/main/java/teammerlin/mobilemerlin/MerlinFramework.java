package teammerlin.mobilemerlin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

import java.util.HashMap;

import teammerlin.game.Game;
import teammerlin.game.GameState;
import teammerlin.game.ID;
//klkl
public class MerlinFramework extends Game
{
	private static final long serialVersionUID = 2338590104281194985L;

	//Comment
	GameState nextState, currentState, previousState;
	Bitmap[] images;
	HashMap<String, MediaPlayer> sounds;
	Panel panel;
	Minigame musicmachine, echo, blackjack, mindbender, magicsquare;

	public MerlinFramework(Context context, int baseWidth, int baseHeight)
	{
		super(context, baseWidth, baseHeight);
	}

	@Override
	public void init()
	{
		//Game Initialization

		//GameState
        nextState = GameState.Default;
		currentState = GameState.Default;
        previousState = null;

		//Images
		loadImages();

		//Sounds
		loadSounds();

		//Panel
        panel = new Panel(anchorX, anchorY, scale, images, sounds, ID.Panel);

        //Minigames
        musicmachine = new MusicMachine();
        echo = new Echo();
        blackjack = new Blackjack();
        mindbender = new Mindbender();
        magicsquare = new MagicSquare();
	}

	@Override
	public void update()
	{
		//Game Updating
		panel.update(touchState);

		switch(currentState)
		{
			case Default:
                if(newState())
                {
                    panel.clearLights();
                    panel.setLight(10, 2);
                }

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }
				break;

			case NewGame:
			    if(newState())
                {
                    panel.clearLights();
                    panel.setLight(0, 2);
                }

                if(panel.getButton(11))
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                newGameSelection();
				break;

            case SameGame:
                if(newState())
                {
                    nextState = previousState;
                }
                break;

			case TicTacToe:
				break;

            case MusicMachine:
                if(newState())
                {
                    panel.clearLights();
                    panel.setLight(10, 2);
                    panel.playSound("select");
                    musicmachine = new MusicMachine();
                }

                musicmachine.update(panel);

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                if(panel.getButton(12))//Same Game pressed
                {
                    nextState = GameState.SameGame;
                }
                break;

            case Echo:
                if(newState())
                {
                    panel.clearLights();
                    panel.setLight(0, 2);
                    panel.setLight(10, 2);
                    panel.playSound("select");
                    echo = new Echo();
                }

                echo.update(panel);

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                if(panel.getButton(12))//Same Game pressed
                {
                    nextState = GameState.SameGame;
                }
                break;

            case Blackjack:
                if(newState())
                {
                    panel.clearLights();
                    panel.setLight(5,1);
                    panel.playSound("select");
                    blackjack = new Blackjack();
                }
                blackjack.update(panel);

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                if(panel.getButton(12))//Same Game pressed
                {
                    nextState = GameState.SameGame;
                }
                break;

            case MagicSquare:
                if(newState())
                {


                    panel.playSound("select");
                    magicsquare = new MagicSquare();
                }

                magicsquare.update(panel);

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                if(panel.getButton(12))//Same Game pressed
                {
                    nextState = GameState.SameGame;
                }
                break;

            case Mindbender:
                if(newState())
                {
                    panel.clearLights();
                    panel.setLight(0, 2);
                    panel.setLight(10, 2);
                    panel.playSound("select");
                    mindbender = new Mindbender();
                }
                mindbender.update(panel);

                if(panel.getButton(11))//New Game pressed
                {
                    panel.playSound("newgame");
                    nextState = GameState.NewGame;
                }

                if(panel.getButton(12))//Same Game pressed
                {
                    mindbender.sameGame(panel);
                }
                break;

		}

		previousState = currentState;
        currentState = nextState;
	}

	@Override
	public void draw(Canvas c, Paint p)
	{
		//Game Drawing
		panel.draw(c, p);

/*        p.setARGB(255, 0, 0,0);
        p.setTextSize(80);
        c.drawText(Integer.toString(timer), 1, 100, p);*/
	}

	private void loadImages()
	{
		images = new Bitmap[4];

		images[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.newgamebutton);
		images[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.samegamebutton);
		images[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hitmebutton);
		images[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.compturnbutton);
	}

	private void loadSounds()
	{
		sounds = new HashMap<String, MediaPlayer>();

		sounds.put("mm0", MediaPlayer.create(context, R.raw.music0));
        sounds.put("mm1", MediaPlayer.create(context, R.raw.music1));
        sounds.put("mm2", MediaPlayer.create(context, R.raw.music2));
        sounds.put("mm3", MediaPlayer.create(context, R.raw.music3));
        sounds.put("mm4", MediaPlayer.create(context, R.raw.music4));
        sounds.put("mm5", MediaPlayer.create(context, R.raw.music5));
        sounds.put("mm6", MediaPlayer.create(context, R.raw.music6));
        sounds.put("mm7", MediaPlayer.create(context, R.raw.music7));
        sounds.put("mm8", MediaPlayer.create(context, R.raw.music8));
        sounds.put("mm9", MediaPlayer.create(context, R.raw.music9));
        sounds.put("blackjackComplete", MediaPlayer.create(context, R.raw.blackjackcomp));
        sounds.put("buzz", MediaPlayer.create(context, R.raw.buzz));
        sounds.put("tttCircle", MediaPlayer.create(context, R.raw.circle));
        sounds.put("tttCross", MediaPlayer.create(context, R.raw.cross));
        sounds.put("lose", MediaPlayer.create(context, R.raw.lose));
        sounds.put("newgame", MediaPlayer.create(context, R.raw.newgame));
        sounds.put("select", MediaPlayer.create(context, R.raw.select));
        sounds.put("tie", MediaPlayer.create(context, R.raw.tie));
        sounds.put("win", MediaPlayer.create(context, R.raw.win));
	}

	private boolean newState()
    {
        return (currentState != previousState);
    }

    private void newGameSelection()
    {
        if(panel.getButton(1))
        {
            nextState = GameState.TicTacToe;
        }
        else if(panel.getButton(2))
        {
            nextState = GameState.MusicMachine;
        }
        else if(panel.getButton(3))
        {
            nextState = GameState.Echo;
        }
        else if(panel.getButton(4))
        {
            nextState = GameState.Blackjack;
        }
        else if(panel.getButton(5))
        {
            nextState = GameState.MagicSquare;
        }
        else if(panel.getButton(6))
        {
            nextState = GameState.Mindbender;
        }
        else if(panel.getButton(7) || panel.getButton(8) || panel.getButton(9) || panel.getButton(10))
        {
            panel.playSound("lose");
        }
    }

}
