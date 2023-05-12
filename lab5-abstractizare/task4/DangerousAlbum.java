package lab5.task4;

public class DangerousAlbum extends Album {
    public static boolean isPrime(int number) {
        for (int i=2; i<number; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addSong(Song song) {
        if (isPrime(song.getId())) {
            songList.add(song);
        }
    }
}
