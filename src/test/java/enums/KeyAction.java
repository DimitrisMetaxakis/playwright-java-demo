package enums;

public enum KeyAction {
    ENTER("Enter"),
    ESCAPE("Escape"),
    TAB("Tab"),
    BACKSPACE("Backspace"),
    DELETE("Delete"),
    ARROW_UP("ArrowUp"),
    ARROW_DOWN("ArrowDown"),
    ARROW_LEFT("ArrowLeft"),
    ARROW_RIGHT("ArrowRight"),
    HOME("Home"),
    END("End"),
    PAGE_UP("PageUp"),
    PAGE_DOWN("PageDown");

    private final String code;

    KeyAction(String code) { this.code = code; }

    public String code() { return code; }
}
