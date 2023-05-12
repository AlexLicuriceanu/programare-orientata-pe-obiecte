package lab5.task4;

public class BadAlbum extends Album {
    public static boolean isPalindrome(int number) {
        int reversed = 0, temp = 0, numberCopy = number;

        while(numberCopy > 0) {
            reversed = reversed * 10 + numberCopy % 10;
            numberCopy /= 10;
        }

        if (reversed == number)
            return true;
        return false;
    }

    @Override
    public void addSong(Song song) {
        if (song.getName().length() == 3 && isPalindrome(song.getId())) {
            songList.add(song);
        }
    }
}
