package com.farm;

import com.farm.model.Vegetable;

import java.util.Scanner;

public class FarmApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gold = 60;
        int weeks = 0;
        int waterLevel = 0;
        Vegetable currentVegetable = null;

        Vegetable[] vegetables = new Vegetable[] {
                new Vegetable("Carrot", 35, 5, 110),
                new Vegetable("Lettuce", 15, 3, 45),
                new Vegetable("Cabbage", 100, 7, 220),
                new Vegetable("Tomato", 155, 8, 355)
        };

        while (gold >= -15 && gold < 1000) {
            System.out.println("Current Gold: " + gold);
            System.out.println("Current water level: " + waterLevel);

            if (currentVegetable != null) {
                if(waterLevel == 0) {
                    System.out.println("No water left! Add more to continue growing " + currentVegetable.getName());
                } else {
                    int weeksLeft = currentVegetable.getWeeksToGrow() - weeks;
                    System.out.println("Current Vegetable: " + currentVegetable.getName() +
                            " (weeks left to grow: " + weeksLeft + ")");
                }
            }

            System.out.println("Commands: sow <vegetable_name>, vegetables, wait, water");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            switch (tokens[0]) {
                case "sow":
                case "s":
                    if (currentVegetable == null) {
                        String vegetableName = tokens[1];
                        Vegetable vegetable = null;
                        for (Vegetable v : vegetables) {
                            if (v.getName().equalsIgnoreCase(vegetableName)) {
                                vegetable = v;
                                break;
                            }
                        }

                        if (vegetable != null && gold >= vegetable.getCost()) {
                            gold -= vegetable.getCost();
                            currentVegetable = vegetable;
                            System.out.println("You have sowed " + vegetableName + ".");
                        } else {
                            System.out.println("Invalid vegetable or insufficient gold.");
                        }
                    } else {
                        System.out.println("Farmland is already occupied.");
                    }
                    break;

                case "vegetables":
                case "v":
                    for (Vegetable vegetable : vegetables) {
                        System.out.println(vegetable.getName() + " (cost: " + vegetable.getCost() +
                                ", growing time: " + vegetable.getWeeksToGrow() +
                                ", selling price: " + vegetable.getSellPrice() + ")");
                    }
                    break;

                case "wait":
                case "":
                    if (currentVegetable != null && waterLevel > 0) {
                        weeks++;
                        waterLevel --;
                        currentVegetable.decreaseWeeksToGrow();
                        if (currentVegetable.isFullyGrown()) {
                            gold += currentVegetable.getSellPrice();
                            System.out.println("You have harvested " + currentVegetable.getName() +
                                    " and earned " + currentVegetable.getSellPrice() + " gold.");
                            currentVegetable = null;
                            weeks = 0;
                        }
                    }

                    gold--;
                    break;
                case "water":
                case "w":
                    waterLevel = 7;
                    gold -= 2;
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }

            System.out.println();
        }

        if (gold >= 1000) {
            System.out.println("Congratulations! You have won the game!");
        } else {
            System.out.println("Game over. You have lost.");
        }
        scanner.close();
    }

}
