package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDateTime;

public class Utils {

  public static PrintWriter createPrintWriter(String filePath) {
    var fout = new File(filePath);
    fout.getParentFile().mkdirs(); // Java does not create folder for us

    try {
      if (fout.createNewFile()) {
        System.out.println(filePath + " is created!");
      } else {
        System.out.println(filePath + " already exists.");
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    try {
      return new PrintWriter(new FileWriter(fout));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static class Logger {

    public static String dateTime() {
      // yyyy-mm-ddTHH:mm:ss
      return LocalDateTime.now().toString().split("[.]")[0];
    }

    public static void logi(String s) {
      System.out.println(dateTime() + " INFO: " + s);
    }

    public static void logit(String s) {
      System.out.println(dateTime() + " INFO: " + Thread.currentThread().getName() + ": " + s);
    }

    public static void loge(String s) {
      System.err.println(dateTime() + " ERROR: " + s);
    }

    public static void loget(String s) {
      System.err.println(dateTime() + " ERROR: " + Thread.currentThread().getName() + ": " + s);
    }

  }

}