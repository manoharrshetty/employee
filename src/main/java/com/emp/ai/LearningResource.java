// java
package com.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningResource implements Serializable {
    private String title;
    private String url;
    private String type; // e.g., "course", "article", "video", "book"
    private String provider; // e.g., "Coursera", "Pluralsight"
}
