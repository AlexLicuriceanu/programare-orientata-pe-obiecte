package lab5.task4;

public class ThrillerAlbum extends Album {
    @Override
    public void addSong(Song song) {
        if (song.getId() % 2 == 0 && song.getComposer().equals("Michael Jackson")) {
            songList.add(song);
        }
    }
}
