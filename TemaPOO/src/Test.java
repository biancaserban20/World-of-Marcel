public class Test {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        try {
            game.run();
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }
}

