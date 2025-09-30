package com.tonkar.volleyballreferee.engine.api.model;

import com.google.gson.annotations.SerializedName;
import com.tonkar.volleyballreferee.engine.game.sanction.SanctionType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Backward-compatible SanctionDto:
 * - Keeps 5-arg constructor for existing call sites.
 * - Adds boolean "improperRequest" (serialized as "ir") defaulting to false when using 5-arg ctor.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SanctionDto {

    @SerializedName("card")
    private SanctionType card;

    @SerializedName("num")
    private int num;

    @SerializedName("set")
    private int set;

    @SerializedName("hp")
    private int homePoints;

    @SerializedName("gp")
    private int guestPoints;

    // New optional flag: true if this delay sanction originates from an Improper Request (IR)
    @SerializedName("ir")
    private boolean improperRequest;

    /** Backward-compatible 5-arg constructor (defaults improperRequest=false). */
    public SanctionDto(SanctionType card, int num, int set, int homePoints, int guestPoints) {
        this(card, num, set, homePoints, guestPoints, false);
    }

    public static boolean isCoach(int num) {
        return num == COACH;
    }

    public static boolean isTeam(int num) {
        return num == TEAM;
    }

    public static final int COACH = 100;
    public static final int TEAM  = 200;
}
