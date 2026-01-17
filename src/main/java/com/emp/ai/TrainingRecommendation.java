package com.emp.ai;


import com.emp.dto.LearningResource;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainingRecommendation implements Serializable {
    // Short displayable bullets (up to 3, as your prompt requests)
    private List<String> bullets;
    private String shortSummary;
    // Skills to focus on (extracted from input / AI suggestion)
    private List<String> skillsToFocus;

    // Suggested course or training titles (could be provider names)
    private List<String> recommendedCourses;

    // Links or resources with title + url + type
    private List<LearningResource> resources;

    // Skill level target (Beginner | Intermediate | Advanced)
    private String level;

    // Preferred training format (online | in-person | mentorship | self-study)
    private String trainingType;

    // Estimated time commitment (e.g. "4 weeks", "10 hours")
    private String estimatedDuration;

    // Priority or urgency (1-5)
    private Integer priority;

    // Confidence score from the AI (0.0 - 1.0)
    private Double confidenceScore;

    // Raw AI output (plain text) for auditing or re-parsing
    private String rawText;

    // Timestamp when recommendation was produced
    private Instant createdAt;
}
