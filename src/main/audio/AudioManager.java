package main.audio;

import java.util.ArrayList;

public class AudioManager {
	
	private ArrayList<AudioPlayer> playingClips = new ArrayList<>();
	private ArrayList<AudioPlayer> removePlayingClipsQueue = new ArrayList<>();
	
	public void playSound(String filePath){
		try {
			AudioPlayer audioPlayer = new AudioPlayer(filePath);
			addPlayingClip(audioPlayer);
			audioPlayer.play();
		}

		catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public void update(){
		for (AudioPlayer ap : playingClips){
			if (!ap.clip.isActive() && !ap.status.equals("paused")) addClipToDeletionQueue(ap);
		}
		
		for (AudioPlayer ap : removePlayingClipsQueue){
			removePlayingClip(ap);
		}
		removePlayingClipsQueue = new ArrayList<>();
	}

	public ArrayList<AudioPlayer> getPlayingClips() {
		return playingClips;
	}
	
	public void addPlayingClip(AudioPlayer ap){
		playingClips.add(ap);
	}
	
	public void removePlayingClip(AudioPlayer ap){
		playingClips.remove(ap);
	}
	
	public void addClipToDeletionQueue(AudioPlayer ap){
		removePlayingClipsQueue.add(ap);
	}

//	public void setPlayingClips(ArrayList<AudioPlayer> playingClips) {
//		this.playingClips = playingClips;
//	}
}
