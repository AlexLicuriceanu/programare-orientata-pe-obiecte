package lab5.task4;

import java.util.ArrayList;

public abstract class Album {
    public ArrayList<Song> songList = new ArrayList<>();

    abstract void addSong(Song song);
    public void removeSong(Song song) {
        songList.remove(song);
    }

    @Override
    public String toString() {
        StringBuilder songListString = new StringBuilder("Album{songs=[");
        for(Song song : songList) {
            songListString.append(song.toString());
            if (!song.equals(songList.get(songList.size() - 1))) {
                songListString.append(", ");
            }
        }
        songListString.append("]}");
        return songListString.toString();
    }
}
