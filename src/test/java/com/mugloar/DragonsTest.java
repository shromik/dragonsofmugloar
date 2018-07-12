package com.mugloar;

import com.mugloar.core.StartGame;
import com.mugloar.dragons.Dragon;
import com.mugloar.dragons.Dragon_;
import com.mugloar.game.Game;
import com.mugloar.game.Knight;
import org.junit.Assert;
import org.junit.Test;

public class DragonsTest {
    @Test
    public void calcucalteNormalWeatherStatsTest(){
        StartGame startGame = new StartGame();

        Knight knight = new Knight();
        Dragon dragon;
        Game game = new Game();

        knight.setAgility(5);
        knight.setArmor(7);
        knight.setAttack(6);
        knight.setEndurance(2);

        game.setKnight(knight);
        startGame.game = game;
        dragon = startGame.createDragon();

        //Assert.assertEquals(knight.getAgility(), dragon.getDragon().getWingStrength());
        Assert.assertTrue(dragon.getDragon().getWingStrength() == knight.getAgility()-1);
        Assert.assertTrue(dragon.getDragon().getFireBreath() == knight.getEndurance());
        Assert.assertTrue(dragon.getDragon().getClawSharpness() == knight.getArmor()+2);
        Assert.assertTrue(dragon.getDragon().getScaleThickness() == knight.getAttack()-1);
    }

    @Test
    public void assertSkillSumTest(){
        StartGame startGame = new StartGame();

        Knight knight = new Knight();
        Dragon dragon;
        Game game = new Game();

        knight.setAgility(4);
        knight.setArmor(7);
        knight.setAttack(6);
        knight.setEndurance(3);

        game.setKnight(knight);
        startGame.game = game;
        dragon = startGame.createDragon();
        Dragon_ skills = dragon.getDragon();

        Assert.assertTrue((skills.getScaleThickness()+skills.getClawSharpness()+skills.getFireBreath()+skills.getWingStrength()) == 20);

    }

}
