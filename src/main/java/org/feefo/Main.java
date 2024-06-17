package org.feefo;

public class Main {
    public static void main(String[] args) {
        Normaliser normaliser = new Normaliser();

        String[] jobTitles = {"Java engineer", "C# engineer", "Accountant", "Chief Accountant"};
        for (String jt : jobTitles) {
            System.out.println("Input: " + jt + " -> Normalized: " + normaliser.normalise(jt));
        }
    }
}