package pages;

import java.util.ArrayList;

public interface Page {
    ArrayList<String> getChangePageCommands();
    ArrayList<String> getOnPageCommands();
    String getPageName();
}
