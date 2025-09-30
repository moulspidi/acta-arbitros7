package com.tonkar.volleyballreferee.engine.api.model;

import com.google.gson.annotations.SerializedName;
import com.tonkar.volleyballreferee.engine.game.sanction.SanctionType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sanction DTO (retrocompatible)
 * - Campo improperRequest (JSON: "ir")
 * - Constructores de 5 y 6 argumentos
 * - Helpers estáticos e instancia isPlayer/isCoach/isTeam
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

    @SerializedName("ir")
    private boolean improperRequest;

    /** Constructor legacy 5 args: por defecto improperRequest=false */
    public SanctionDto(SanctionType card, int num, int set, int homePoints, int guestPoints) {
        this(card, num, set, homePoints, guestPoints, false);
    }

    public static boolean isCoach(int num) { return num == COACH; }
    public static boolean isTeam(int num)  { return num == TEAM; }
    public static boolean isPlayer(int num){ return !isCoach(num) && !isTeam(num); }

    public boolean isCoach()  { return isCoach(this.num); }
    public boolean isTeam()   { return isTeam(this.num); }
    public boolean isPlayer() { return isPlayer(this.num); }

    public static final int COACH = 100;
    public static final int TEAM  = 200;
}
