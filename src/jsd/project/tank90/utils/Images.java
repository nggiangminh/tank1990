package jsd.project.tank90.utils;

import javax.swing.*;
import java.awt.*;

public class Images {

    private static final String folderPath = "src/jsd/project/tank90/resources/images/";

    //Environment images
    public static final Image BASE = new ImageIcon(folderPath + "base.png").getImage();
    public static final Image BRICK = new ImageIcon(folderPath + "brick.png").getImage();
    public static final Image ICE = new ImageIcon(folderPath + "ice.png").getImage();
    public static final Image STEEL = new ImageIcon(folderPath + "steel.png").getImage();
    public static final Image TREE = new ImageIcon(folderPath + "tree.png").getImage();
    public static final Image WATER = new ImageIcon(folderPath + "water.png").getImage();

    //Powerup Images
    public static final Image GRENADE_PU = new ImageIcon(folderPath + "powerup_grenade.png").getImage();
    public static final Image SHIELD_PU = new ImageIcon(folderPath + "powerup_helmet.png").getImage();
    public static final Image SHOVEL_PU = new ImageIcon(folderPath + "powerup_shovel.png").getImage();
    public static final Image STAR_PU = new ImageIcon(folderPath + "powerup_star.png").getImage();
    public static final Image TANK_PU = new ImageIcon(folderPath + "powerup_tank.png").getImage();
    public static final Image TIMER_PU = new ImageIcon(folderPath + "powerup_timer.png").getImage();
    public static final Image TANK_DEAD = new ImageIcon(folderPath + "tank_dead.png").getImage();

    //Shield images
    public static final Image SHIELD_1 = new ImageIcon(folderPath + "shield_1.png").getImage();
    public static final Image SHIELD_2 = new ImageIcon(folderPath + "shield_2.png").getImage();

    //Player Tank Images
    //0 or 1st star
    public static final Image PLAYER_UP_1_S1 = new ImageIcon(folderPath + "tank_player_up_1_s1.png").getImage();
    public static final Image PLAYER_UP_2_S1 = new ImageIcon(folderPath + "tank_player_up_2_s1.png").getImage();
    public static final Image PLAYER_DOWN_1_S1 = new ImageIcon(folderPath + "tank_player_down_1_s1.png").getImage();
    public static final Image PLAYER_DOWN_2_S1 = new ImageIcon(folderPath + "tank_player_down_2_s1.png").getImage();
    public static final Image PLAYER_LEFT_1_S1 = new ImageIcon(folderPath + "tank_player_left_1_s1.png").getImage();
    public static final Image PLAYER_LEFT_2_S1 = new ImageIcon(folderPath + "tank_player_left_2_s1.png").getImage();
    public static final Image PLAYER_RIGHT_1_S1 = new ImageIcon(folderPath + "tank_player_right_1_s1.png").getImage();
    public static final Image PLAYER_RIGHT_2_S1 = new ImageIcon(folderPath + "tank_player_right_2_s1.png").getImage();
    //2nd star
    public static final Image PLAYER_UP_1_S2 = new ImageIcon(folderPath + "tank_player_up_1_s2.png").getImage();
    public static final Image PLAYER_UP_2_S2 = new ImageIcon(folderPath + "tank_player_up_2_s2.png").getImage();
    public static final Image PLAYER_DOWN_1_S2 = new ImageIcon(folderPath + "tank_player_down_1_s2.png").getImage();
    public static final Image PLAYER_DOWN_2_S2 = new ImageIcon(folderPath + "tank_player_down_2_s2.png").getImage();
    public static final Image PLAYER_LEFT_1_S2 = new ImageIcon(folderPath + "tank_player_left_1_s2.png").getImage();
    public static final Image PLAYER_LEFT_2_S2 = new ImageIcon(folderPath + "tank_player_left_2_s2.png").getImage();
    public static final Image PLAYER_RIGHT_1_S2 = new ImageIcon(folderPath + "tank_player_right_1_s2.png").getImage();
    public static final Image PLAYER_RIGHT_2_S2 = new ImageIcon(folderPath + "tank_player_right_2_s2.png").getImage();
    //3rd star
    public static final Image PLAYER_UP_1_S3 = new ImageIcon(folderPath + "tank_player_up_1_s3.png").getImage();
    public static final Image PLAYER_UP_2_S3 = new ImageIcon(folderPath + "tank_player_up_2_s3.png").getImage();
    public static final Image PLAYER_DOWN_1_S3 = new ImageIcon(folderPath + "tank_player_down_1_s3.png").getImage();
    public static final Image PLAYER_DOWN_2_S3 = new ImageIcon(folderPath + "tank_player_down_2_s3.png").getImage();
    public static final Image PLAYER_LEFT_1_S3 = new ImageIcon(folderPath + "tank_player_left_1_s3.png").getImage();
    public static final Image PLAYER_LEFT_2_S3 = new ImageIcon(folderPath + "tank_player_left_2_s3.png").getImage();
    public static final Image PLAYER_RIGHT_1_S3 = new ImageIcon(folderPath + "tank_player_right_1_s3.png").getImage();
    public static final Image PLAYER_RIGHT_2_S3 = new ImageIcon(folderPath + "tank_player_right_2_s3.png").getImage();


    //Armor Tank Images
    //white(1 health)
    public static final Image ARMOR_UP_1_H1 = new ImageIcon(folderPath + "tank_armor_up_1_h1.png").getImage();
    public static final Image ARMOR_UP_2_H1 = new ImageIcon(folderPath + "tank_armor_up_2_h1.png").getImage();
    public static final Image ARMOR_DOWN_1_H1 = new ImageIcon(folderPath + "tank_armor_down_1_h1.png").getImage();
    public static final Image ARMOR_DOWN_2_H1 = new ImageIcon(folderPath + "tank_armor_down_2_h1.png").getImage();
    public static final Image ARMOR_LEFT_1_H1 = new ImageIcon(folderPath + "tank_armor_left_1_h1.png").getImage();
    public static final Image ARMOR_LEFT_2_H1 = new ImageIcon(folderPath + "tank_armor_left_2_h1.png").getImage();
    public static final Image ARMOR_RIGHT_1_H1 = new ImageIcon(folderPath + "tank_armor_right_1_h1.png").getImage();
    public static final Image ARMOR_RIGHT_2_H1 = new ImageIcon(folderPath + "tank_armor_right_2_h1.png").getImage();

    //white(1 health)
    public static final Image ARMOR_UP_1_H2 = new ImageIcon(folderPath + "tank_armor_up_1_h2.png").getImage();
    public static final Image ARMOR_UP_2_H2 = new ImageIcon(folderPath + "tank_armor_up_2_h2.png").getImage();
    public static final Image ARMOR_DOWN_1_H2 = new ImageIcon(folderPath + "tank_armor_down_1_h2.png").getImage();
    public static final Image ARMOR_DOWN_2_H2 = new ImageIcon(folderPath + "tank_armor_down_2_h2.png").getImage();
    public static final Image ARMOR_LEFT_1_H2 = new ImageIcon(folderPath + "tank_armor_left_1_h2.png").getImage();
    public static final Image ARMOR_LEFT_2_H2 = new ImageIcon(folderPath + "tank_armor_left_2_h2.png").getImage();
    public static final Image ARMOR_RIGHT_1_H2 = new ImageIcon(folderPath + "tank_armor_right_1_h2.png").getImage();
    public static final Image ARMOR_RIGHT_2_H2 = new ImageIcon(folderPath + "tank_armor_right_2_h2.png").getImage();

    //white(1 health)
    public static final Image ARMOR_UP_1_H3 = new ImageIcon(folderPath + "tank_armor_up_1_h3.png").getImage();
    public static final Image ARMOR_UP_2_H3 = new ImageIcon(folderPath + "tank_armor_up_2_h3.png").getImage();
    public static final Image ARMOR_DOWN_1_H3 = new ImageIcon(folderPath + "tank_armor_down_1_h3.png").getImage();
    public static final Image ARMOR_DOWN_2_H3 = new ImageIcon(folderPath + "tank_armor_down_2_h3.png").getImage();
    public static final Image ARMOR_LEFT_1_H3 = new ImageIcon(folderPath + "tank_armor_left_1_h3.png").getImage();
    public static final Image ARMOR_LEFT_2_H3 = new ImageIcon(folderPath + "tank_armor_left_2_h3.png").getImage();
    public static final Image ARMOR_RIGHT_1_H3 = new ImageIcon(folderPath + "tank_armor_right_1_h3.png").getImage();
    public static final Image ARMOR_RIGHT_2_H3 = new ImageIcon(folderPath + "tank_armor_right_2_h3.png").getImage();

    public static final Image ARMOR_UP_1_F= new ImageIcon(folderPath + "tank_armor_up_1_f.png").getImage();
    public static final Image ARMOR_UP_2_F = new ImageIcon(folderPath + "tank_armor_up_2_f.png").getImage();
    public static final Image ARMOR_DOWN_1_F = new ImageIcon(folderPath + "tank_armor_down_1_f.png").getImage();
    public static final Image ARMOR_DOWN_2_F = new ImageIcon(folderPath + "tank_armor_down_2_f.png").getImage();
    public static final Image ARMOR_LEFT_1_F = new ImageIcon(folderPath + "tank_armor_left_1_f.png").getImage();
    public static final Image ARMOR_LEFT_2_F = new ImageIcon(folderPath + "tank_armor_left_2_f.png").getImage();
    public static final Image ARMOR_RIGHT_1_F = new ImageIcon(folderPath + "tank_armor_right_1_f.png").getImage();
    public static final Image ARMOR_RIGHT_2_F = new ImageIcon(folderPath + "tank_armor_right_2_f.png").getImage();

    // BASIC Tank Images
    public static final Image BASIC_UP_1 = new ImageIcon(folderPath + "tank_basic_up.png").getImage();
    public static final Image BASIC_UP_2 = new ImageIcon(folderPath + "tank_basic_up_2.png").getImage();
    public static final Image BASIC_DOWN_1 = new ImageIcon(folderPath + "tank_basic_down.png").getImage();
    public static final Image BASIC_DOWN_2 = new ImageIcon(folderPath + "tank_basic_down_2.png").getImage();
    public static final Image BASIC_LEFT_1 = new ImageIcon(folderPath + "tank_basic_left.png").getImage();
    public static final Image BASIC_LEFT_2 = new ImageIcon(folderPath + "tank_basic_left_2.png").getImage();
    public static final Image BASIC_RIGHT_1 = new ImageIcon(folderPath + "tank_basic_right.png").getImage();
    public static final Image BASIC_RIGHT_2 = new ImageIcon(folderPath + "tank_basic_right_2.png").getImage();

    public static final Image BASIC_UP_1_F = new ImageIcon(folderPath + "tank_basic_up_f.png").getImage();
    public static final Image BASIC_UP_2_F = new ImageIcon(folderPath + "tank_basic_up_2_f.png").getImage();
    public static final Image BASIC_DOWN_1_F = new ImageIcon(folderPath + "tank_basic_down_f.png").getImage();
    public static final Image BASIC_DOWN_2_F = new ImageIcon(folderPath + "tank_basic_down_2_f.png").getImage();
    public static final Image BASIC_LEFT_1_F = new ImageIcon(folderPath + "tank_basic_left_f.png").getImage();
    public static final Image BASIC_LEFT_2_F = new ImageIcon(folderPath + "tank_basic_left_2_f.png").getImage();
    public static final Image BASIC_RIGHT_1_F = new ImageIcon(folderPath + "tank_basic_right_f.png").getImage();
    public static final Image BASIC_RIGHT_2_F = new ImageIcon(folderPath + "tank_basic_right_2_f.png").getImage();

    // FAST Tank Images
    public static final Image FAST_UP_1 = new ImageIcon(folderPath + "tank_fast_up.png").getImage();
    public static final Image FAST_UP_2 = new ImageIcon(folderPath + "tank_fast_up_2.png").getImage();
    public static final Image FAST_DOWN_1 = new ImageIcon(folderPath + "tank_fast_down.png").getImage();
    public static final Image FAST_DOWN_2 = new ImageIcon(folderPath + "tank_fast_down_2.png").getImage();
    public static final Image FAST_LEFT_1 = new ImageIcon(folderPath + "tank_fast_left.png").getImage();
    public static final Image FAST_LEFT_2 = new ImageIcon(folderPath + "tank_fast_left_2.png").getImage();
    public static final Image FAST_RIGHT_1 = new ImageIcon(folderPath + "tank_fast_right.png").getImage();
    public static final Image FAST_RIGHT_2 = new ImageIcon(folderPath + "tank_fast_right_2.png").getImage();

    public static final Image FAST_UP_1_F = new ImageIcon(folderPath + "tank_fast_up_f.png").getImage();
    public static final Image FAST_UP_2_F = new ImageIcon(folderPath + "tank_fast_up_2_f.png").getImage();
    public static final Image FAST_DOWN_1_F = new ImageIcon(folderPath + "tank_fast_down_f.png").getImage();
    public static final Image FAST_DOWN_2_F = new ImageIcon(folderPath + "tank_fast_down_2_f.png").getImage();
    public static final Image FAST_LEFT_1_F = new ImageIcon(folderPath + "tank_fast_left_f.png").getImage();
    public static final Image FAST_LEFT_2_F = new ImageIcon(folderPath + "tank_fast_left_2_f.png").getImage();
    public static final Image FAST_RIGHT_1_F = new ImageIcon(folderPath + "tank_fast_right_f.png").getImage();
    public static final Image FAST_RIGHT_2_F = new ImageIcon(folderPath + "tank_fast_right_2_f.png").getImage();

    // POWER Tank Images
    public static final Image POWER_UP_1 = new ImageIcon(folderPath + "tank_power_up.png").getImage();
    public static final Image POWER_UP_2 = new ImageIcon(folderPath + "tank_power_up_2.png").getImage();
    public static final Image POWER_DOWN_1 = new ImageIcon(folderPath + "tank_power_down.png").getImage();
    public static final Image POWER_DOWN_2 = new ImageIcon(folderPath + "tank_power_down_2.png").getImage();
    public static final Image POWER_LEFT_1 = new ImageIcon(folderPath + "tank_power_left.png").getImage();
    public static final Image POWER_LEFT_2 = new ImageIcon(folderPath + "tank_power_left_2.png").getImage();
    public static final Image POWER_RIGHT_1 = new ImageIcon(folderPath + "tank_power_right.png").getImage();
    public static final Image POWER_RIGHT_2 = new ImageIcon(folderPath + "tank_power_right_2.png").getImage();

    public static final Image POWER_UP_1_F = new ImageIcon(folderPath + "tank_power_up_f.png").getImage();
    public static final Image POWER_UP_2_F = new ImageIcon(folderPath + "tank_power_up_2_f.png").getImage();
    public static final Image POWER_DOWN_1_F = new ImageIcon(folderPath + "tank_power_down_f.png").getImage();
    public static final Image POWER_DOWN_2_F = new ImageIcon(folderPath + "tank_power_down_2_f.png").getImage();
    public static final Image POWER_LEFT_1_F = new ImageIcon(folderPath + "tank_power_left_f.png").getImage();
    public static final Image POWER_LEFT_2_F = new ImageIcon(folderPath + "tank_power_left_2_f.png").getImage();
    public static final Image POWER_RIGHT_1_F = new ImageIcon(folderPath + "tank_power_right_f.png").getImage();
    public static final Image POWER_RIGHT_2_F = new ImageIcon(folderPath + "tank_power_right_2_f.png").getImage();

}

