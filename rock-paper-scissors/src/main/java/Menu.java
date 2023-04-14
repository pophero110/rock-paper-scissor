public interface Menu {

    void handleOption(String key);

    void showOption();

    boolean getShouldGoBackMenu();

    void setShouldGoBackMenu(boolean shouldGoBackMenu);
}
