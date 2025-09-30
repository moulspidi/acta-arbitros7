package com.tonkar.volleyballreferee.engine.api.model;

import com.google.gson.annotations.SerializedName;
import com.tonkar.volleyballreferee.engine.game.sanction.SanctionType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sanction DTO compatible hacia atrás:
 * - Mantiene utilidades estáticas e instanciadas isPlayer/isCoach/isTeam que usa el resto del código.
 * - Añade flag opcional "improperRequest" (JSON: "ir") para marcar solicitudes improcedentes (IR).
 * - Conserva ctor de 5 parámetros y de 6 parámetros.
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

    // Nuevo: marca si esta sanción (de retraso) proviene de Improper Request (IR)
    @SerializedName("ir")
    private boolean improperRequest;

    /** Ctor legacy (5 args) para no romper llamadas existentes. */
    public SanctionDto(SanctionType card, int num, int set, int homePoints, int guestPoints) {
        this(card, num, set, homePoints, guestPoints, false);
    }

    // --- Utilidades estáticas usadas por el motor/UI existentes ---

    /** ¿El número corresponde a un entrenador? */
    public static boolean isCoach(int num) {
        return num == COACH;
    }

    /** ¿El número corresponde al equipo (sanción al equipo)? */
    public static boolean isTeam(int num) {
        return num == TEAM;
    }

    /** ¿El número corresponde a un jugador? (ni coach ni team) */
    public static boolean isPlayer(int num) {
        return !isCoach(num) && !isTeam(num);
    }

    // --- Atajos de instancia (sin parámetros) usados en varios adapters/dialogs ---

    public boolean isCoach() {
        return isCoach(this.num);
    }

    public boolean isTeam() {
        return isTeam(this.num);
    }

    public boolean isPlayer() {
        return isPlayer(this.num);
    }

    // Constantes de marcado especial
    public static final int COACH = 100;
    public static final int TEAM  = 200;
}
