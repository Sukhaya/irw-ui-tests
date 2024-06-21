package pages;

public abstract class BaseComponent {

    /** чтобы не описывать в каждом классе снова по 4 строки **/

    BaseComponent() {
        checkPage();
    }

    public abstract void checkPage();
}
