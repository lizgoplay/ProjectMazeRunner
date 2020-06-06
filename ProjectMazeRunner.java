import java.util.*;

public class ProjectMazeRunner {
    public static Maze myMap = new Maze();
    public static int userSteps = 0;

    public static void main(String[] args) {
        intro();

        while (myMap.didIWin() == false) {
            String userDir = userMove();
            if (userDir.equals("R") || userDir.equals("r") || userDir.equals("L") || userDir.equals("l") || userDir.equals("U") || userDir.equals("u") || userDir.equals("D") || userDir.equals("d") )
                navigatePit(userDir);
        }
        if (myMap.didIWin() == true) {
            System.out.print("Congratulations, you made it out alive!");
            System.out.println("and you did it in " + ProjectMazeRunner.userSteps + " moves");
        }
    }

    public static void intro(){
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is where you are going to start:");
        myMap.printMap();
    }

    public static void movesMessage(int moves){
        switch (moves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                break;
            case 101:
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                System.exit(0);
                break;
        }
    }

    public static String userMove(){
        Scanner input = new Scanner(System.in);
        String direction = "";
        do{
            if(ProjectMazeRunner.userSteps != 101)
            {
                System.out.print("Where would you like to move? (R, L, U, D)    ");
                direction = input.next();
            }

            if(direction.equals("R") || direction.equals("r") || direction.equals("L") || direction.equals("l") ||direction.equals("U") || direction.equals("u") || direction.equals("D") || direction.equals("d") ) {
                movesMessage(++ProjectMazeRunner.userSteps);

                if (myMap.canIMoveRight() == true && (direction.equals("R") || direction.equals("r")) ) {
                    myMap.moveRight();
                }
                else if (myMap.canIMoveLeft() == true && (direction.equals("L") || direction.equals("l")) ) {
                    myMap.moveLeft();
                }
                else if (myMap.canIMoveUp() == true && (direction.equals("U") || direction.equals("u")) ) {
                    myMap.moveUp();
                }
                else if (myMap.canIMoveDown() == true && (direction.equals("D") || direction.equals("d")) ) {
                    myMap.moveDown();
                }
                else {
                    if (ProjectMazeRunner.userSteps != 101) {
                        System.out.println("Sorry, you've hit a wall.");
                        System.out.print("Where would you like to move? (R, L, U, D)    ");
                        direction = input.next();
                        movesMessage(++ProjectMazeRunner.userSteps);
                    }
                }

                myMap.printMap();
                break;
            }
        } while(direction.matches("[RrLlUuDd]") == false);

        return direction;
    }

    public static void navigatePit(String userDirection) {
        Scanner input = new Scanner(System.in);
        if(myMap.isThereAPit(userDirection) == true)
        {
            System.out.print("Watch out! There's a pit ahead, jump it?  ");
            String jump = input.next();
            if(jump.equalsIgnoreCase("yes") || jump.equalsIgnoreCase("y"))
                {myMap.jumpOverPit(userDirection);
                myMap.printMap();}
            else
            {
                System.out.println("Sorry, but you didn't jump- you lose!");
                System.exit(0);
            }
        }
        else {
            System.out.println("Sorry, you've hit a wall.");
        }
    }
}