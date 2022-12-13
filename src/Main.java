import Models.Game;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // Поисковик запускать через Main2
    public static void main(String[] args) {

        System.out.println("Generate? 1/0");
        Scanner sc = new Scanner(System.in);
        if(sc.nextInt() == 1){
            try {
                while(true) {
                    File startFolder = new File(Game.startFolderName);
                    if (startFolder.exists()) {
                        removeFolder(startFolder);
                    }
                    startFolder.mkdir();
                    if (startFolder.isDirectory()) {
                        if (generateDir(startFolder, 3, 5, true)) {
                            System.out.println("file created!");
                            break;
                        } else {
                            System.out.println("Error :( try again..");
                        }
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void removeFolder(File folder){
        File[] contents = folder.listFiles();
        if (contents != null) {
            for (File f : contents) {
                removeFolder(f);
            }
        }
        folder.delete();
    }

    public static boolean generateDir(File dir, int countDir, int percentForWin, boolean firstStart) throws IOException {
        Random random = new Random();

        int percent = random.nextInt(101);
        if (percent <= percentForWin){
            File winnerFile = new File(dir.getAbsolutePath() + "/" + Game.winnerFileName);
            if (!winnerFile.exists()){
                winnerFile.createNewFile();
            }
            return true;
        }

        int createDirCount = random.nextInt(countDir + 1);
        if (firstStart && createDirCount == 0){
            createDirCount = 1;
        }
        boolean result = false;
        for(int i = 0; i < createDirCount; i++){
            File newDir = new File(dir.getAbsolutePath() + "/" + (random.nextInt(1000)));
            if (!newDir.exists()){
                newDir.mkdir();
            }
            if (!result){
                if(generateDir(newDir, countDir, percentForWin, false)){
                    result = true;
                }
            }
        }
        return result;
    }
}