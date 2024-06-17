package org.feefo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Normaliser implements JobTitleNormaliser {

    private final List<String> normalizedJobTitles = Arrays.asList("Architect", "Software engineer", "Quantity surveyor", "Accountant");

    public String normalise(String jobTitle) {
        // Convert the input job title to lowercase
        String lowerCaseJobTitle = jobTitle.toLowerCase();

        // Find the normalized job title with the smallest Levenshtein distance to the input job title
        Optional<String> bestMatch = normalizedJobTitles.stream()
                .min((a, b) -> Integer.compare(getLevenshteinDistance(a.toLowerCase(), lowerCaseJobTitle), getLevenshteinDistance(b.toLowerCase(), lowerCaseJobTitle)));

        // Return the best match, or null if there are no matches
        return bestMatch.orElse(null);
    }

    // Calculate the Levenshtein distance between two strings
    private int getLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;  // If the first string is empty, all insertions are needed
                } else if (j == 0) {
                    dp[i][j] = i;  // If the second string is empty, all deletions are needed
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // If the characters are the same, no new operation is needed
                } else {
                    // Choose the minimum cost from insert, delete, and replace operations
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[a.length()][b.length()];  // The Levenshtein distance is found in the bottom-right cell of the matrix
    }
}
