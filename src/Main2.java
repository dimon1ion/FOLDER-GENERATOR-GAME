import Models.Game;

import java.io.File;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Start? 1/0");
        Scanner sc = new Scanner(System.in);
        if(sc.nextInt() == 1){
            File startFolder = new File(Game.startFolderName);
            if (!startFolder.exists()) {
                System.out.println("Run the folder generator first");
                return;
            }
            if (startFolder.isDirectory()) {
                File result = startFinder(startFolder);
                if (result != null){
                    System.out.println("Winning file in " + result.getAbsolutePath());
                }
                else{
                    System.out.println("Winning file missing");
                }
            }
        }
    }

    public static File startFinder(File folder){
        File[] contents = folder.listFiles();
        if (contents != null) {
            for (File item : contents) {
                if (item.isFile()) {
                    if (item.getName().equals(Game.winnerFileName)) {
                        return folder;
                    }
                }
                if (item.isDirectory()) {
                    File result = startFinder(item);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
