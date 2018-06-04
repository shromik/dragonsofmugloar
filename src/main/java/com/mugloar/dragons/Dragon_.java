
package com.mugloar.dragons;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dragon_ {
    private Integer scaleThickness;
    private Integer clawSharpness;
    private Integer wingStrength;
    private Integer fireBreath;

    private final static long serialVersionUID = 3749806695589119583L;

    public Integer getScaleThickness() {
        return scaleThickness;
    }

    public Dragon_ setScaleThickness(Integer scaleThickness) {
        this.scaleThickness = scaleThickness;
        return this;
    }

    public Integer getClawSharpness() {
        return clawSharpness;
    }

    public Dragon_ setClawSharpness(Integer clawSharpness) {
        this.clawSharpness = clawSharpness;
        return this;
    }

    public Integer getWingStrength() {
        return wingStrength;
    }

    public Dragon_ setWingStrength(Integer wingStrength) {
        this.wingStrength = wingStrength;
        return this;
    }

    public Integer getFireBreath() {
        return fireBreath;
    }

    public Dragon_ setFireBreath(Integer fireBreath) {
        this.fireBreath = fireBreath;
        return this;
    }

}
