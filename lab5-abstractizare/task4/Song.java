package lab5.task4;

public class Song {
    private String name;
    private int id;
    private String composer;

    public Song(String name, int id, String composer) {
        this.name = new String(name);
        this.id = id;
        this.composer = new String(composer);
    }

    @Override
    public String toString() {
        return "Song{name=" + this.name + ", id=" + this.id + ", composer=" + this.composer + "}";
    }

    public void setName(String name) {
        this.name = new String(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComposer(String composer) {
        this.composer = new String(composer);
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public String getComposer() {
        return this.composer;
    }
}
