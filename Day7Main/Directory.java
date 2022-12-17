package Day7Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Directory {

    public Map<String, Directory> dirs;

    ArrayList<Integer> files;

    Directory parent;

    String name;

    int size;

    //Constructors
    public Directory(String n) {
        dirs = new HashMap<String, Directory>();
        files = new ArrayList<Integer>();
        this.name = n;
    }

    public Directory(String n, Directory p) {
        this.dirs = new HashMap<String, Directory>();

        this.files = new ArrayList<Integer>();
        this.name = n;
        this.parent = p;
    }

    public void addDir(String name) {
        this.dirs.put(name, new Directory(name, this));
    }

    public void addFile(int fileSize) {
        this.files.add(fileSize);
    }

    public String getName() {
        return this.name;
    }

    public Directory getDir(String name) {
        return this.dirs.get(name);
    }

    public Directory getParent() {
        return this.parent;
    }

    public int getSize() {
        int size = 0;

        for (int i : this.files) {
            size += i;
        }

        if (this.dirs.size() > 0) {
            for (Directory d : this.dirs.values()) {
                size += d.getSize();
            }
        }

        this.size = size;
        return size;
    }

}
