package ie.tudublin;

public abstract class MyVisual {
    protected MainWindow window;
    private boolean toggleRender = true;
    private boolean singleMode = true;
    private String name;


    public MyVisual(MainWindow window, String name)
    {
        this.window = window;
        this.name = name;
    }
    
    public abstract void update();
    
    public abstract void render();
    
    public abstract void keyPressed();
    
    public boolean shouldRender() {
        return toggleRender;
    }

    public void toggleRender() {
        this.toggleRender = !this.toggleRender;
    }

    public boolean isSingleMode() {
        return singleMode;
    }

    public void setSingleMode(boolean singleMode) {
        this.singleMode = singleMode;
    }

    public String getName() {
        return name;
    }
}
